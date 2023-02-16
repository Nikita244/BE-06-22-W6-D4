package it.giuliafranzosiw2d5.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.giuliafranzosiw2d5.entities.Role;
import it.giuliafranzosiw2d5.entities.Utente;

@Service
public class DaoService {
	
	@Autowired
	private UtenteRepository utenteRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public Optional<Utente> getUtenteById(int id){
		return utenteRepo.findById(id);
	}
	
	public Utente saveUtente(Utente obj) {
		return utenteRepo.save(obj);
	}
	
	public Role saveRole(Role obj) {
		return roleRepo.save(obj);
	}
	
	
}
