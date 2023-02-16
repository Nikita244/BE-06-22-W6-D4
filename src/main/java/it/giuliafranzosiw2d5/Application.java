package it.giuliafranzosiw2d5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import it.giuliafranzosiw2d5.config.Beans;
import it.giuliafranzosiw2d5.dao.BookingRepository;
import it.giuliafranzosiw2d5.dao.BookingService;
import it.giuliafranzosiw2d5.dao.DaoService;
import it.giuliafranzosiw2d5.dao.EdificioRepository;
import it.giuliafranzosiw2d5.dao.EdificioService;
import it.giuliafranzosiw2d5.dao.PostazioneRepository;
import it.giuliafranzosiw2d5.dao.PostazioneService;
import it.giuliafranzosiw2d5.dao.UtenteRepository;
import it.giuliafranzosiw2d5.dao.UtenteService;
import it.giuliafranzosiw2d5.entities.Booking;
import it.giuliafranzosiw2d5.entities.Edificio;
import it.giuliafranzosiw2d5.entities.Postazione;
import it.giuliafranzosiw2d5.entities.Role;
import it.giuliafranzosiw2d5.entities.RoleType;
import it.giuliafranzosiw2d5.entities.TipoPostazione;
import it.giuliafranzosiw2d5.entities.Utente;


@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	UtenteService us;
	@Autowired
	EdificioService es;
	@Autowired
	PostazioneService ps;
	@Autowired
	BookingService bk;
	@Autowired
	EdificioRepository er;
	@Autowired
	UtenteRepository ur;
	@Autowired
	BookingRepository br;
	@Autowired
	PostazioneRepository rr;
	@Autowired
	private DaoService dao;
	
	
	
	
	//@Value("${populate_db}")
	private int populateDbFlag = 0;
	

	@Override
	public void run(String... args) throws Exception {
		if(populateDbFlag == 1 ) {
			populateDb();
		}
		getRolesFromUserById(1);
	}
		
	private void populateDb() {
	ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		
		Utente u1 = (Utente)ctx.getBean("utente", "marty","Marta Rossi","marta@marta.it" , "test");
		Role r1 = (Role)ctx.getBean("role", RoleType.ROLE_ADMIN);
		Role r2 = (Role)ctx.getBean("role", RoleType.ROLE_USER);
		
		u1.setRoles(new HashSet<>() {{
			
			add(r1);
		}});
		
		dao.saveRole(r1);
		dao.saveRole(r2);
		dao.saveUtente(u1);
		
		((AnnotationConfigApplicationContext)ctx).close();
		
	}
	
	private void getRolesFromUserById(int id) {
		Optional<Utente> authUserObj = dao.getUtenteById(id);
		Utente authUser = authUserObj.get();
		String role = "USER";
		Set<Role> roles = authUser.getRoles();
		
		for( Role r : roles ) {
			if( r.getType().toString().contains("ADMIN") ) {
				role = "ADMIN";
				break;
			}
		}
		
		System.out.println(role);
	}

	
		
		

	//--------------ABILITAZIONE SCANNER---------------------
		
			//inserisciEdificio();	
			//inserisciPostazione();
			//inserisciUtente();
			//creaPrenotazione();

	
	

	//-----------SCANNER AGGIUNGI UTENTE 
	public void inserisciUtente() {
		Utente u = new Utente();
		Scanner scan = new Scanner(System.in);
		System.out.println("***Benvenuto, effettua la registrazione per effettuare la prenotazione*** ");
		System.out.println();
		System.out.println("Inserisci l'username: ");
		String username = scan.nextLine();
		System.out.println("Inserisci il tuo nome completo: ");
		String nome= scan.nextLine();
		System.out.println("Inserisci la tua email: ");
		String mail = scan.nextLine();
		System.out.println("Utente aggiunto correttamente!");

		u.setUsername(username);
		u.setNomeCompleto(nome);
		u.setEmail(mail);
		
		us.insert(u);
		System.out.println("Il codice ID per effettuare la prenotazione è " + u.getId());
		
		scan.close();
	}
	
	//-----------SCANNER AGGIUNGI PTENOTAZIONE
	/*public void creaPrenotazione() {
		Booking b = new Booking();
		Scanner scan = new Scanner(System.in);
		System.out.println("Inserisci il codice ID per effettuare la prenotazione: ");
		String user = scan.nextLine();
		try {
			b.setUtente(trovaUtente(Integer.parseInt((user))));
		} catch (Exception e2) {
			System.out.println("Utente non trovato!");
			creaPrenotazione();
		}
		while(true) {
            System.out.println("Inserisci la data in cui vuoi effettuare la prenotazione in formato 'YYYY-MM-DD': ");
            try {
                b.setData(impostaData(scan.nextLine(), trovaUtente((int)Integer.parseInt(user))));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());   
            }
        }
		getPostazioniDisp();
		while(true) {
            System.out.println("Inserire il codice postazione:");
            try {
                b.setPostazione(impostaPost(Integer.parseInt(scan.nextLine())));
                break;
            } catch(NullPointerException e) {
                System.out.println("Codice postazione errato!");
            } catch(Exception e) {
                System.out.println("Codice postazione errato!");
            }
        }

        bk.insert(b);
   
        System.out.println("Prenotazione di " + b.getUtente().getNomeCompleto() +" del " + b.getData() + " a " + b.getPostazione().getEdificio().getIndirizzo() + " creata con successo!");
      
        scan.close();

	}
	
	
	//-----------SCANNER AGGIUNGI EDIFICIO 
	public void inserisciEdificio() {
	    Edificio e = new Edificio();
	    Scanner scan = new Scanner(System.in);
	    System.out.println("Inserisci nome edificio: ");
	    String nome = scan.nextLine();
	    System.out.println("Inserisci indirizzo: ");
	    String ind= scan.nextLine();
	    System.out.println("Inserisci citta: ");
	    String citta = scan.nextLine();
	    System.out.println("Edificio aggiunto correttamente!");

	    e.setNome(nome);
	    e.setIndirizzo(ind);
	    e.setCitta(citta);
	
	    es.insert(e);
	    
	    scan.close();
	}
	
	//------------SCANNER AGGIUNGI POSTAZIONE A EDIFICIO
	/*public void inserisciPostazione() {
		Postazione p = new Postazione();
		Scanner scan = new Scanner(System.in);
		System.out.println("Inserisci ID edificio in cui creare la postazione: ");
		try {
			p.setEdificio(trovaEdificio(Integer.parseInt((scan.nextLine()))));
		} catch (Exception e1) {
			System.out.println("Edificio non trovato!");
			inserisciPostazione();
		}
		System.out.println("Inserisci descrizione postazione: ");
		String desc = scan.nextLine();
		int tipo;
		do {
		    System.out.println("Inserisci il numero corrispondente al tipo di postazione: [1]Privato [2]Open Space [3]Sala Riunioni ");
		    try {
		        tipo = Integer.parseInt(scan.nextLine());
		        if (tipo < 1 || tipo > 3) {
		            System.out.println("Selezione non valida, inserisci un numero compreso tra 1 e 3");
		        } else {
		            break;
		        }
		    } catch (NumberFormatException e) {
		        System.out.println("Devi inserire un numero. Riprova.");
		    }
		} while (true);
		p.setTipo(stringToTipoPostazione(tipo));
		
		int maxOccupanti;
		String maxOccupantiString;
	    do {
	        System.out.println("Inserisci la capienza massima: ");
	        maxOccupantiString = scan.nextLine();
	        try {
	        	maxOccupanti = Integer.parseInt(maxOccupantiString);
	        	if (maxOccupanti <= 0) {
	        		System.out.println("La capienza massima deve essere un numero");
	        	}
	        } catch (NumberFormatException e) {
	        	System.out.println("Il valore inserito non è valido, inserisci un numero intero");
	        	maxOccupanti = 0;
	        }
	    } while (maxOccupanti <= 0);

	    System.out.println("Postazione aggiunta correttamente all'edificio!");
	    p.setDescrizione(desc);
	    p.setMaxOcc(maxOccupanti);

	    ps.insert(p);
	    scan.close();
	}*/

	
	
	//------------------------METODI
	
	
	public Edificio trovaEdificio(int parseInt) throws Exception {
		Edificio result = er.findById(parseInt);
		if(result != null) {
			return result;
		} else {
			throw new Exception();
		}
		
	}
	
	public Optional<Utente> trovaUtente(int parseInt) throws Exception {
		Optional<Utente> result = ur.findById(parseInt);
		if(result != null) {
			return result;
		} else {
			throw new Exception();
		}
		
	}
	
	
	
	private TipoPostazione stringToTipoPostazione(int tipo) {
	    switch (tipo) {
	        case 1:
	            return TipoPostazione.PRIVATO;
	        case 2:
	            return TipoPostazione.OPENSPACE;
	        case 3:
	            return TipoPostazione.SALA_RIUNIONI;
	        default:
	            throw new IllegalArgumentException("Invalid tipo postazione value: " + tipo);
	    }
	}
	
	
	/*public LocalDate impostaData(String s, Optional<Utente> optional) throws Exception {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate data = LocalDate.parse(s, formatter);
			LocalDate dataOra = LocalDate.now();
			List<Booking> lista = br.findByUser(optional.getUsername());
			if (data.isBefore(dataOra)) {
				throw new Exception("Errore: non è possibile accettare questa data.");
			}
			for(Booking b : lista) {
				if (b.getData().equals(data)) {
					throw new Exception("Hai già una prenotazione in corso!");
				}
			}
			return data;
		} catch (DateTimeParseException e) {
			System.out.println("Errore: La data inserita non è nel formato corretto!");
			throw new Exception("");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("");
		}
	}
	
	public void getPostazioniDisp() {
		List<Postazione> x = rr.findAll();
		if(x.isEmpty()) {
			System.out.println("Nessuna postazione disponibile!");
		} else {
			System.out.println("Lista delle postazioni aziendali disponibili:");
			System.out.println("Inserisci l'ID corrispondente alla postazione di tuo interesse");
			for(Postazione e : x) {
				System.out.println("Postazione Id: ["+ e.getId() +"] *** Tipologia: " + e.getTipo() +" *** Luogo: "+e.getEdificio().getCitta());
			}
		}
	}
	
	public Postazione impostaPost(int p) throws Exception {
        Postazione post = rr.findById(p);
        if(post != null) {
            return post;
        } else {
            throw new Exception("Postazione non valida");
        }
    } */
	
	
	
	
}










