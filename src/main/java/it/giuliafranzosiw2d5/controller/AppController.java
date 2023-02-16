package it.giuliafranzosiw2d5.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("about")
	@ResponseBody
	public String about() {
		return "Benvenuto nella pagina gestione prenotazioni";
	}
	
	@PostMapping("login_success")
	@ResponseBody
	public String login_success() {
		return "Login effettuato correttamente";
	}
	
	@GetMapping("info")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public String info() {
		return "Vedi questa pagina perchè sei utente con permesso ADMIN";
	}
	
	
	
}
