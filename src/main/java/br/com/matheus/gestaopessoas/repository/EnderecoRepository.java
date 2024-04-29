package br.com.matheus.gestaopessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheus.gestaopessoas.model.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	
}
