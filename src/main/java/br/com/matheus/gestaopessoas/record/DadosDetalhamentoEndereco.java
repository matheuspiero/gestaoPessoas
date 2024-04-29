package br.com.matheus.gestaopessoas.record;

import br.com.matheus.gestaopessoas.model.Endereco;

public record DadosDetalhamentoEndereco(Long id, String nome, String logradouro, String bairro, String cep, 
										String numero, String cidade, String estado, Boolean principal) {
	
	public DadosDetalhamentoEndereco(Endereco endereco){
		
		this(endereco.getId(), endereco.getPessoa().getNome(), endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), 
				endereco.getNumero(), endereco.getCidade(), endereco.getEstado(), endereco.getPrincipal());
		
	}

}
