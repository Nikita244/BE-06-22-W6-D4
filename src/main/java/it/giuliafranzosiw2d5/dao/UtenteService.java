package it.giuliafranzosiw2d5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.giuliafranzosiw2d5.entities.Utente;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepo;
	
	public void insert(Utente u) {
		utenteRepo.save(u);
	}

}
