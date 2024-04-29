package br.com.matheus.gestaopessoas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.gestaopessoas.model.Endereco;
import br.com.matheus.gestaopessoas.model.Pessoa;
import br.com.matheus.gestaopessoas.record.DadosAtualizacaoPessoa;
import br.com.matheus.gestaopessoas.record.DadosCadastroPessoa;
import br.com.matheus.gestaopessoas.record.DadosDetalhamentoEnderecoPessoa;
import br.com.matheus.gestaopessoas.record.DadosDetalhamentoPessoa;
import br.com.matheus.gestaopessoas.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository repository;

	@PostMapping("/cadastro")
	@Transactional
	public ResponseEntity criar(@RequestBody @Valid DadosCadastroPessoa dados) {
		var pessoa = new Pessoa(dados);
		repository.save(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso");
	}

	@PutMapping("/altera")
	@Transactional
	public ResponseEntity editar(@RequestBody @Valid DadosAtualizacaoPessoa dados) {
		try {
			var pessoa = repository.getReferenceById(dados.id());
			pessoa.atualizarPessoa(dados);
			repository.save(pessoa);
			return ResponseEntity.ok(new DadosDetalhamentoPessoa(pessoa, getDadosEnderecos(pessoa.getEndereco())));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não cadastrada.");
		}
	}

	@DeleteMapping("/exclui/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var existePessoa = repository.existsById(id);
		if (existePessoa) {
			repository.deleteById(id);
			return ResponseEntity.ok("Pessoa excluída com Sucesso");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não cadastrada.");
	}

	@GetMapping("/consulta/{id}")
	public ResponseEntity consultar(@PathVariable Long id) {
		var existePessoa = repository.existsById(id);
		if (existePessoa) {
			var pessoa = repository.getReferenceById(id);
			return ResponseEntity.ok(new DadosDetalhamentoPessoa(pessoa, getDadosEnderecos(pessoa.getEndereco())));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não cadastrada.");
	}

	@GetMapping("/consulta")
	public ResponseEntity obterTodos() {
		return ResponseEntity.ok(converteDados(repository.findAll()));
	}

	private List<DadosDetalhamentoPessoa> converteDados(List<Pessoa> pessoa) {
		return pessoa.stream().map(s -> new DadosDetalhamentoPessoa(s.getId(), s.getNome(), s.getDataNascimento(),
				getDadosEnderecos(s.getEndereco()))).collect(Collectors.toList());
	}

	private List<DadosDetalhamentoEnderecoPessoa> getDadosEnderecos(List<Endereco> enderecos) {
		return enderecos.stream().map(s -> new DadosDetalhamentoEnderecoPessoa(s)).collect(Collectors.toList());
	}
}
