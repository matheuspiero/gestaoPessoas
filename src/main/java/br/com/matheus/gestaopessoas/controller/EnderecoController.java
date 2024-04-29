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
import br.com.matheus.gestaopessoas.record.DadosAtualizacaoEndereco;
import br.com.matheus.gestaopessoas.record.DadosCadastroEndereco;
import br.com.matheus.gestaopessoas.record.DadosDetalhamentoEndereco;
import br.com.matheus.gestaopessoas.repository.EnderecoRepository;
import br.com.matheus.gestaopessoas.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;

	@PostMapping("/cadastro")
	@Transactional
	public ResponseEntity criar(@RequestBody @Valid DadosCadastroEndereco dados) {
		if (!pessoaRepository.existsById(dados.idPessoa())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não cadastrada.");
		}
		var pessoa = pessoaRepository.getReferenceById(dados.idPessoa());
		var endereco = new Endereco(dados, pessoa);
		repository.save(endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso");

	}

	@PutMapping("/altera")
	@Transactional
	public ResponseEntity editar(@RequestBody @Valid DadosAtualizacaoEndereco dados) {
		try {
			var endereco = repository.getReferenceById(dados.id());
			endereco.atualizarEndereco(dados);
			repository.save(endereco);
			return ResponseEntity.ok(new DadosDetalhamentoEndereco(endereco));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço não cadastrado.");
		}
	}

	@DeleteMapping("/exclui/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var existeEndereco = repository.existsById(id);
		if (existeEndereco) {
			repository.deleteById(id);
			return ResponseEntity.ok("Endereço excluido com Sucesso");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço não cadastrado.");

	}

	@GetMapping("/consulta/{id}")
	public ResponseEntity consultar(@PathVariable Long id) {
		var existeEndereco = repository.existsById(id);
		if (existeEndereco) {
			var endereco = repository.getReferenceById(id);
			return ResponseEntity.ok(new DadosDetalhamentoEndereco(endereco));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço não cadastrado.");

	}

	@GetMapping("/consulta")
	public ResponseEntity obterTodos() {
		return ResponseEntity.ok(converteDados(repository.findAll()));

	}

	private List<DadosDetalhamentoEndereco> converteDados(List<Endereco> endereco) {
		return endereco.stream()
				.map(s -> new DadosDetalhamentoEndereco(s.getId(), s.getPessoa().getNome(), s.getLogradouro(),
						s.getBairro(), s.getCep(), s.getNumero(), s.getCidade(), s.getEstado(), s.getPrincipal()))
				.collect(Collectors.toList());
	}
}
