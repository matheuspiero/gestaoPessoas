package br.com.matheus.gestaopessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheus.gestaopessoas.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {	
	

}
