package it.giuliafranzosiw2d5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.giuliafranzosiw2d5.entities.Postazione;

@Service
public class PostazioneService {
	
	@Autowired
	private PostazioneRepository postazioneRepo;
	
	public void insert(Postazione p) {
		postazioneRepo.save(p);
	}


}
