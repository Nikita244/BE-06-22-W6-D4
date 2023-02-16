package it.giuliafranzosiw2d5.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import it.giuliafranzosiw2d5.entities.Edificio;

@Service
public class EdificioService {
	
	@Autowired
	private EdificioRepository edificioRepo;
	
	public void insert(Edificio e) {
		edificioRepo.save(e);
	}
	
	public List<Edificio> getAll() {
		return edificioRepo.findAll();
	}
	
	
	public Edificio save(Edificio e) {
		return edificioRepo.save(e);
	}
	

	public Edificio getBydId(int id) {
		return edificioRepo.findById(id);
	}
	
	public void delete(Edificio e) {
		edificioRepo.delete(e);
	}
	
	public Page<Edificio> getAll_page(Pageable pageable) {
		return edificioRepo.findAll(pageable);
	}
	

}
