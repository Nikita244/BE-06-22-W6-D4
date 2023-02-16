package it.giuliafranzosiw2d5.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.giuliafranzosiw2d5.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	List<Booking> findById(int id);
	
	@Query(value = "SELECT p.*, u.id as u_id FROM booking p INNER JOIN utente u ON p.utente_id = u.id WHERE u.username = :elemento", nativeQuery = true)
    List<Booking> findByUser(@Param("elemento") String e);

}
