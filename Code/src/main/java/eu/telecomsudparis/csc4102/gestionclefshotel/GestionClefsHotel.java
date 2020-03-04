package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Collection;
import java.util.HashMap;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;


/**
 * Cette classe définit la façade du système de gestion des
 * clefs de l'hôtel.
 */
public class GestionClefsHotel {								// TODO Documentation.
	/**
	 * La collection de chambres de l'hôtel indexée par leurs
	 * identifiants supposés uniques.
	 */
	private final HashMap<Long, Chambre> chambres;
	/**
	 * La collection de badges de l'hôtel indexée par leurs
	 * identifiants supposés uniques.
	 */
	private final HashMap<Long, Badge> badges;
	/**
	 * La collection de clients de l'hôtel indexée par leurs
	 * identifiants supposés uniques.
	 */
	private final HashMap<Long, Client> clients;

	/**
	 * Construit la façade. Initialise les collections.
	 */
	public GestionClefsHotel() {
		this.chambres = new HashMap<Long, Chambre>();
		this.badges = new HashMap<Long, Badge>();
		this.clients = new HashMap<Long, Client>();

		assert this.invariant();
	}

	/**
	 * Teste si l'invariant est vérifié.
	 * @return {@code true} si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return chambres != null & badges != null & clients != null;
	}

	
	/**
	 * Opération cas d'utilisation "Créer une chambre" : la
	 * façade reçoit un identifiant, une graine et un sel
	 * et crée une nouvelle chambre grâce à ceux-ci, si l'
	 * identifiant n'est pas déjà utilisé et que la graine
	 * est non vide.
	 * 
	 * @param id L'identifiant de la chambre à créer.
	 * @param graine La graine de génération de clés de la
	 *		chambre.
	 * @param sel Le sel de génération de clés de la chambre.
	 * @return La nouvelle chambre si l'identifiant est libre,
	 *		l'ancienne sinon.
	 * @throws ProblemeDansGenerationClef Si la génération
	 *		est cassée en mille morceaux.
	 */

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

	
	/**
	 * Cherche et renvoie la chambre associée à l'identifiant
	 * donné. Si aucune chambre n'a cet identifiant, {@code null}
	 * est alors renvoyé.
	 * 
	 * @param id L'identifiant de la chambre à chercher.
	 * @return La chambre associé à l'id, {@code null} sinon.
	 */
	public Chambre chercherChambre(final long id) {
		return this.chambres.get(id);
	}
	
	/**
	 * @return Une collection des chambres de l'hôtel.
	 */
	public Collection<Chambre> listerChambres() {
		return this.chambres.values();
	}
	
	/**
	 * Opération cas d'utilisation "Enregistrer l'occupation
	 * d'une chambre par un client". Cherche la chambre, le
	 * badge et le client associés aux identifiants fournis, et
	 * si ceux-ci réunissent les conditions nécessaires, le badge
	 * va alors être associé bidirectionnellement au client et
	 * de même à la chambre qui obtient une nouvelle paire de
	 * clefs enfin inscrite dans le badge.
	 * 
	 * @param idChambre La chambre à faire occuper par le client.
	 * @param idBadge Le badge qui va servir à ouvrir la chambre.
	 * @param idClient Le client payant pour la chambre.
	 * @throws ProblemeDansGenerationClef Si la génération
	 *		est cassée en mille morceaux.
	 */

	public void enregistrerOccupationChambre(final long idChambre,
											final long idBadge,
											final long idClient) throws ProblemeDansGenerationClef {
		final Chambre chambre = this.chercherChambre(idChambre);
		final Badge badge = this.chercherBadge(idBadge);
		final Client client = this.chercherClient(idClient);

		
		if (badge != null && badge.getClefs() == null
				&& chambre != null && client != null) {

			badge.associerClient(client, true);
			badge.associerChambre(chambre, true);
			badge.inscrireClefs(chambre.obtenirNouvellePaireClefs());
			assert invariant();
		}
	}

	
	/**
	 * Opération cas d'utilisation "Libérer une chambre". Cherche
	 * la chambre et le badge potentiellement associés aux IDs
	 * fournis et s'il respectent certaines conditions, la chambre
	 * est alors libérée avec {@link Chambre#liberer()} et le badge
	 * est vidé de ses clefs avec {@link Badge#vider()}.
	 * 
	 * @param idChambre La chambre à libérer de son client.
	 * @param idBadge Le badge du client.
	 */

	public void libererChambre(final long idChambre, final long idBadge) {
		final Chambre chambre = this.chercherChambre(idChambre);
		final Badge badge = this.chercherBadge(idBadge);

		if (chambre != null && badge != null && badge.getClefs() == null) {
			chambre.liberer();									// TODO Check condition.
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

	
	/**
	 * Cherche et renvoie le badge associée à l'identifiant
	 * donné. Si aucun badge n'a cet identifiant, {@code null}
	 * est alors renvoyé.
	 * 
	 * @param id L'identifiant du badge à chercher.
	 * @return Le badge associé à l'id, {@code null} sinon.
	 */

	public Badge chercherBadge(final long id) {
		return this.badges.get(id);
	}

	public Client creerClient(final long id, final String nom, final String prenom) {
		final Client client = new Client(id, nom, prenom);		// TODO Check implementation.
		this.clients.put(id, client);
		assert invariant();
		return client;
	}

	
	/**
	 * Cherche et renvoie le client associée à l'identifiant
	 * donné. Si aucun client n'a cet identifiant, {@code null}
	 * est alors renvoyé.
	 * 
	 * @param id L'identifiant du client à chercher.
	 * @return Le client associé à l'id, {@code null} sinon.
	 */

	public Client chercherClient(final long id) {
		return this.clients.get(id);
	}
}
