package br.com.matheus.gestaopessoas.record;

import java.util.List;

import br.com.matheus.gestaopessoas.model.Pessoa;


public record DadosDetalhamentoPessoa(Long id, String nome, String dataNascimento, List<DadosDetalhamentoEnderecoPessoa> enderecos) {
	
	public DadosDetalhamentoPessoa(Pessoa pessoa, List<DadosDetalhamentoEnderecoPessoa> enderecos) {
		
		this(pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento(), enderecos);
	}

}
