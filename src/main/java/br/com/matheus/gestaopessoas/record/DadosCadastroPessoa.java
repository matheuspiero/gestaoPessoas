package br.com.matheus.gestaopessoas.record;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPessoa(
		@NotBlank
	    String nome,	    
	    @NotNull
	    @JsonFormat(pattern = "dd/MM/yyyy")
	    String dataNascimento
	    ) {

}
