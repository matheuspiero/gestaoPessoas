package br.com.matheus.gestaopessoas.record;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoEndereco(
		
		
		@NotNull
		Long id,
		String logradouro,
	    String bairro,
	    String cep,
	    String numero,
	    String cidade,
	    String estado,
	    Boolean principal) {

}
