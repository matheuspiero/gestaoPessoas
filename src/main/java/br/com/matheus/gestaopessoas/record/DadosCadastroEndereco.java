package br.com.matheus.gestaopessoas.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
		
		@NotNull
		Long idPessoa,
		String logradouro,
		@NotBlank
		@Pattern(regexp = "\\d{8}")
		String cep,
		@NotBlank
		String bairro,		
		@NotBlank
		String cidade,
		@NotBlank
		String estado,
		String numero,
		Boolean principal) {
}


