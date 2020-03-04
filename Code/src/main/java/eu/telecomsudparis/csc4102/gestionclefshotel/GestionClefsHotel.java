package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Collection;
import java.util.HashMap;

import at.favre.lib.bytes.Bytes;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;
import eu.telecomsudparis.csc4102.gestionserrures.GestionSerrures;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureDejaPresente;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureInexistante;


/**
 * Cette classe définit la façade du système de gestion des clefs de l'hôtel.
 */
public class GestionClefsHotel {				// TODO Documentation.
	public static final int ID_LENGTH = 128;
	private final HashMap<Long, Chambre> chambres;
	private final HashMap<Long, Badge> badges;
	private final HashMap<Long, Client> clients;
	private final GestionSerrures gestionSerrures;
	
	/**
	 * Construit la façade.
	 */
	public GestionClefsHotel() {
		this.chambres = new HashMap<Long, Chambre>();
		this.badges = new HashMap<Long, Badge>();
		this.clients = new HashMap<Long, Client>();
		this.gestionSerrures = new GestionSerrures();
		
		assert this.invariant();
	}
	
	/**
	 * Teste si l'invariant est vérifié.
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return true;							// TODO
	}
	
	public Chambre creerChambre(final long id, final String graine, final int sel)
			throws ProblemeDansGenerationClef {			// TODO Consistent with sequence diagram.
		final Chambre chambre = new Chambre(id, graine, sel);
		this.chambres.put(id, chambre);
		return chambre;
	}
	
	public Chambre chercherChambre(final long id) {
		return this.chambres.get(id);
	}
	
	public Collection<Chambre> listerChambres() {
		return this.chambres.values();
	}
	
	public void enregistrerOccupationChambre(Chambre chambre, Client client) {
		chambre.getBadge().associerClient(client);		// TODO Consistent with sequence diagram + with new system.
	}
	
	public void libererChambre(Chambre chambre) {
		chambre.getBadge().dissocierClient();			// TODO Consistent with sequence diagram + with new system.
	}
	
	public void initialiserSerrure(Chambre chambre) throws ProblemeDansGenerationClef {
		String randomId;								// TODO Delete.
		
		while (true) {
			randomId = GestionClefsHotel.newRandomId();
			
			try {
				this.gestionSerrures.obtenirSelSerrure(randomId);
			}
			catch (SerrureInexistante exc) {
				break;
			}
			catch (ChaineDeCaracteresNullOuVide exc) {}
		}
		
		try {
			this.gestionSerrures.creerSerrure(Long.toString(chambre.getId()), randomId, 0);
		}
		catch (ChaineDeCaracteresNullOuVide | SerrureDejaPresente exc) {}
	}
	
	public void obtenirGraineEtSel(final Chambre chambre) {
		// TODO Delete
	}
	
	public Badge creerBadge(final long id) {					// TODO Consistent with sequence diagram.
		final Badge badge = new Badge(id);
		this.badges.put(id, badge);
		return badge;
	}
	
	public Badge chercherBadge(final long id) {
		return this.badges.get(id);
	}
	
	public Client creerClient(final long id, final String nom, final String prenom) {
		final Client client = new Client(id, nom, prenom);		// TODO Consistent with sequence diagram.
		this.clients.put(id, client);
		return client;
	}
	
	public Client chercherClient(final long id) {
		return this.clients.get(id);
	}
	
	public static String newRandomId() {
		return Bytes.random(ID_LENGTH).encodeBase64();			// TODO Delete.
	}
}
