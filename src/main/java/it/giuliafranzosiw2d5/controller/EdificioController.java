package it.giuliafranzosiw2d5.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import it.giuliafranzosiw2d5.dao.EdificioService;
import it.giuliafranzosiw2d5.entities.Edificio;

@RestController
public class EdificioController {

	@Autowired
	private EdificioService es;
	
	//---------GET
		@GetMapping("edifici")
		public ResponseEntity<List<Edificio>> getAll() {
			List<Edificio> edifici = es.getAll();
			
			if( edifici.isEmpty() ) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(edifici, HttpStatus.OK);
		}
		
		
		//---------POST
		@PostMapping("edifici")
		public ResponseEntity<Object> create(@RequestBody Edificio e) {
			Edificio edificio = es.save(e);
			
			return new ResponseEntity<>(edificio, HttpStatus.CREATED);
		}
		
		//---------PUT
		@PutMapping("edifici/{id}")
		public ResponseEntity<Object> update(
				@PathVariable Integer id,
				@RequestBody Edificio _edificio) {
			
			Edificio edificioObj = es.getBydId(id);
			
			edificioObj.setNome( _edificio.getNome());
			edificioObj.setIndirizzo( _edificio.getIndirizzo());
			edificioObj.setCitta( _edificio.getCitta());
			es.save(edificioObj);
			
			return new ResponseEntity<>(edificioObj, HttpStatus.CREATED);
		}
		
		
		//---------DELETE
		@DeleteMapping("edifici/{id}")
		public ResponseEntity<Object> delete(@PathVariable Integer id) {
			Edificio edificioObj = es.getBydId(id);
		
			return new ResponseEntity<>(
					String.format("Edificio con id %d cancellato!", id), HttpStatus.OK);
		}
		
		
		@GetMapping("edificio_page")
	    public ResponseEntity<Object> getAll_page(Pageable pageable) {
	        Page<Edificio> obj = es.getAll_page(pageable);

	        if( obj.isEmpty() ) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(obj, HttpStatus.OK);
	    }

}
		
		
		
		
		
		
		
		
		
		
		
		
		
		


