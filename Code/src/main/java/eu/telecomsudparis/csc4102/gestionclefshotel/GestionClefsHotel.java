package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Collection;
import java.util.HashMap;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;


/**
 * Cette classe définit la façade du système de gestion des clefs de l'hôtel.
 */
public class GestionClefsHotel {								// TODO Documentation.
	private final HashMap<Long, Chambre> chambres;
	private final HashMap<Long, Badge> badges;
	private final HashMap<Long, Client> clients;
	
	/**
	 * Construit la façade.
	 */
	public GestionClefsHotel() {
		this.chambres = new HashMap<Long, Chambre>();
		this.badges = new HashMap<Long, Badge>();
		this.clients = new HashMap<Long, Client>();
		
		assert this.invariant();
	}
	
	/**
	 * Teste si l'invariant est vérifié.
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return true;											// TODO invariant.
	}
	
	public Chambre creerChambre(final long id, final String graine, final int sel)
			throws ProblemeDansGenerationClef {					// TODO Consistent with sequence diagram.
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
		chambre.getBadge().associerClient(client);				// TODO Consistent with sequence diagram + with new system.
	}
	
	public void libererChambre(Chambre chambre) {
		chambre.getBadge().dissocierClient();					// TODO Consistent with sequence diagram + with new system.
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
}
