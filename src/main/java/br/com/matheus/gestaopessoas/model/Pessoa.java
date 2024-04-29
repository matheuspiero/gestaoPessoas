package br.com.matheus.gestaopessoas.model;

import java.util.List;

import br.com.matheus.gestaopessoas.record.DadosAtualizacaoPessoa;
import br.com.matheus.gestaopessoas.record.DadosCadastroPessoa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	private String dataNascimento;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Endereco> endereco;
	
	public Pessoa(DadosCadastroPessoa dados) {
		this.nome = dados.nome();
		this.dataNascimento = dados.dataNascimento();
	}
	
	public void atualizarPessoa(DadosAtualizacaoPessoa dados) {
		if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.dataNascimento() != null) {
            this.dataNascimento = dados.dataNascimento();
        }      
	}	

}
