package it.giuliafranzosiw2d5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.giuliafranzosiw2d5.entities.Postazione;
import it.giuliafranzosiw2d5.entities.Utente;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long>{
	
	Postazione findById(int id);
}
