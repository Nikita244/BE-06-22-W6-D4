package it.giuliafranzosiw2d5.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.giuliafranzosiw2d5.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
	
	Optional<Utente> findById(int id);
}
