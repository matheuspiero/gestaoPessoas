package br.com.matheus.gestaopessoas.record;

import br.com.matheus.gestaopessoas.model.Endereco;

public record DadosDetalhamentoEnderecoPessoa(Long id, String logradouro, String bairro, String cep, 
										String numero, String cidade, String estado, Boolean principal) {
	
	public DadosDetalhamentoEnderecoPessoa(Endereco endereco){
		
		this(endereco.getId(), endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), 
				endereco.getNumero(), endereco.getCidade(), endereco.getEstado(), endereco.getPrincipal());
		
	}

}
