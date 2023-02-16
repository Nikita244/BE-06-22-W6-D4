package it.giuliafranzosiw2d5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.giuliafranzosiw2d5.entities.Role;
import it.giuliafranzosiw2d5.entities.RoleType;
import it.giuliafranzosiw2d5.entities.Utente;

@Configuration
public class Beans {
	
	@Bean
	@Scope("prototype")
	public Utente utente(String username, String nomeCompleto, String email, String password) {
		return Utente.builder()
				.username(username)
				.nomeCompleto(nomeCompleto)
				.email(email)
				.password(password)
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Role role(RoleType rt) {
		return Role.builder()
				.type(rt)
				.build();
	}

}
