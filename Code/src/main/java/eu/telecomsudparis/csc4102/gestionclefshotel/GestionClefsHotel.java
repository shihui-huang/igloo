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
		return chambres != null & badges != null & clients != null;
	}

	public Chambre creerChambre(final long id, final String graine, final int sel)
			throws ProblemeDansGenerationClef {
		final Chambre current = this.chercherChambre(id);

		if (current == null && graine != null && !graine.isEmpty()) {
			final Chambre chambre = new Chambre(id, graine, sel);
			this.chambres.put(id, chambre);
			assert invariant();
			return chambre;
		}
		else {
			return current;
		}
		
	}

	public Chambre chercherChambre(final long id) {
		return this.chambres.get(id);
	}

	public Collection<Chambre> listerChambres() {
		return this.chambres.values();
	}

	public void enregistrerOccupationChambre(final long idChambre,
											final long idBadge,
											final long idClient) throws ProblemeDansGenerationClef {
		final Chambre chambre = this.chercherChambre(idChambre);
		final Badge badge = this.chercherBadge(idBadge);
		final Client client = this.chercherClient(idClient);

		if (chambre != null && client != null && badge != null && badge.getClefs() == null) {
			badge.associerClient(client, true);
			badge.associerChambre(chambre, true);
			badge.inscrireClefs(chambre.obtenirNouvellePaireClefs());
			assert invariant();
		}
	}

	public void libererChambre(final long idChambre, final long idBadge) {
		final Chambre chambre = this.chercherChambre(idChambre);
		final Badge badge = this.chercherBadge(idBadge);

		if (chambre != null && badge != null && badge.getClefs() == null) {
			chambre.liberer();
			badge.vider();
			assert invariant();
		}
	}

	public Badge creerBadge(final long id) {					// TODO Check implementation.
		final Badge badge = new Badge(id);
		this.badges.put(id, badge);
		assert invariant();
		return badge;
	}

	public Badge chercherBadge(final long id) {
		return this.badges.get(id);
	}

	public Client creerClient(final long id, final String nom, final String prenom) {
		final Client client = new Client(id, nom, prenom);		// TODO Check implementation.
		this.clients.put(id, client);
		assert invariant();
		return client;
	}

	public Client chercherClient(final long id) {
		return this.clients.get(id);
	}
}
