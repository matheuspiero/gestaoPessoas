package br.com.matheus.gestaopessoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GestaopessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaopessoasApplication.class, args);
	}

}
