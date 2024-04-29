package br.com.matheus.gestaopessoas.model;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.matheus.gestaopessoas.record.DadosAtualizacaoEndereco;
import br.com.matheus.gestaopessoas.record.DadosCadastroEndereco;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String logradouro;
	private String cep;
    private String bairro;    
    private String numero;
    private String cidade;
    private String estado;
    private Boolean principal;
    
    @Autowired
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    
    public Endereco(DadosCadastroEndereco dados, Pessoa idPessoa) {
		this.logradouro = dados.logradouro();
		this.bairro = dados.bairro();
		this.cep = dados.cep();
		this.numero = dados.numero();
		this.cidade = dados.cidade();
		this.estado = dados.estado();
		this.principal = dados.principal();
		this.pessoa = idPessoa;
		
	}


	public void atualizarEndereco(DadosAtualizacaoEndereco dados) {
		if(dados.logradouro() !=null) {
		this.logradouro = dados.logradouro();
		}
		if(dados.bairro() !=null) {
			this.bairro = dados.bairro();
		}
		if(dados.cep() !=null) {
			this.cep = dados.cep();
			}
		if(dados.estado() !=null) {
			this.estado = dados.estado();
		}
		if(dados.cidade() !=null) {
			this.cidade = dados.cidade();
		}
		if(dados.numero() !=null) {
			this.numero = dados.numero();		
		}
		if(dados.principal() !=false) {
			this.principal = dados.principal();
		}		
	}	

}
