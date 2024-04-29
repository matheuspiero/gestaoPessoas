package br.com.matheus.gestaopessoas.record;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPessoa(
		
		@NotNull
		Long id,
		String nome,
		String dataNascimento) {

}
