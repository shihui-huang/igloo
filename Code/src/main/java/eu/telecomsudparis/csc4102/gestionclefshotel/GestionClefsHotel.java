package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Collection;
import java.util.HashMap;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeDejaAssocieChambreOuClient;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeDejaPresent;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeInexistant;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreDejaOccupee;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreDejaPresente;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreInexistante;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreNonOccupee;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientDejaPresent;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientInexistant;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientOccupeDejaChambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientOccupeAutreChambre;

import eu.telecomsudparis.csc4102.util.OperationImpossible;


/**
 * Cette classe définit la façade du système de gestion des clefs de l'hôtel.
 */
public class GestionClefsHotel {
	/**
	 * La collection de chambres de l'hôtel indexée par leurs identifiants
	 * supposés uniques.
	 */
	private final HashMap<Long, Chambre> chambres;
	/**
	 * La collection de badges de l'hôtel indexée par leurs identifiants
	 * supposés uniques.
	 */
	private final HashMap<Long, Badge> badges;
	/**
	 * La collection de clients de l'hôtel indexée par leurs identifiants
	 * supposés uniques.
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
	 * 
	 * @return {@code true} si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return this.chambres != null && this.badges != null && this.clients != null;
	}

	/**
	 * Opération cas d'utilisation "Créer une chambre" : la façade reçoit un
	 * identifiant, une graine et un sel et crée une nouvelle chambre grâce à
	 * ceux-ci, si l' identifiant n'est pas déjà utilisé et que la graine est
	 * non vide.
	 *
	 * @param  id                         L'identifiant de la chambre à créer.
	 * @param  graine                     La graine de génération de clés de la
	 *                                    chambre.
	 * @param  sel                        Le sel de génération de clés de la
	 *                                    chambre.
	 * @throws OperationImpossible        Si la génération est cassée en mille
	 *                                    morceaux.
	 */
	public void creerChambre(final long id, final String graine, final int sel) throws OperationImpossible {
		final Chambre current = this.chercherChambre(id);
		
		if (graine == null || graine.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("Graine null ou vide non autorisée.");
		}
		if (current != null) {
			throw new ChambreDejaPresente("Chambre '" + id + "' déjà présente dans le système.");
		}
		
		final Chambre chambre = new Chambre(id, graine, sel);
		this.chambres.put(id, chambre);
		assert this.invariant();
	}
	
	/**
	 * Cherche et renvoie la chambre associée à l'identifiant donné. Si aucune
	 * chambre n'a cet identifiant, {@code null} est alors renvoyé.
	 * 
	 * @param  id L'identifiant de la chambre à chercher.
	 * @return    La chambre associé à l'id, {@code null} sinon.
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
	 * Opération cas d'utilisation "Enregistrer l'occupation d'une chambre par
	 * un client". Cherche la chambre, le badge et le client associés aux
	 * identifiants fournis, et si ceux-ci réunissent les conditions
	 * nécessaires, le badge va alors être associé bidirectionnellement au
	 * client et de même à la chambre qui obtient une nouvelle paire de clefs
	 * enfin inscrite dans le badge.
	 * 
	 * @param  idChambre                  La chambre à faire occuper par le
	 *                                    client.
	 * @param  idBadge                    Le badge qui va servir à ouvrir la
	 *                                    chambre.
	 * @param  idClient                   Le client payant pour la chambre.
	 * @throws OperationImpossible Si la génération est cassée en mille
	 *                                    morceaux.
	 */
	public void enregistrerOccupationChambre(final long idChambre, final long idBadge, final long idClient)
			throws OperationImpossible {
		final Chambre chambre = this.chercherChambre(idChambre);
		final Badge badge = this.chercherBadge(idBadge);
		final Client client = this.chercherClient(idClient);
		
		if (chambre == null) {
			throw new ChambreInexistante("La chambre n'existe pas, il faut d'abord la créer.");
		}
		if (badge == null) {
			throw new BadgeInexistant("Le badge n'existe pas, il faut d'abord le créer.");
		}
		if (client == null) {
			throw new ClientInexistant("Le client n'existe pas, il faut d'abord le créer.");
		}
		if (client.getBadge() != null) {
			throw new ClientOccupeDejaChambre("Le client occupe déjà une chambre.");
		}
		if (chambre.estOccupee()) {
			throw new ChambreDejaOccupee("La chambre '" + idChambre + "' est déjà occupée.");
		}
		
		if (badge.getChambre() != null) {
			throw new BadgeDejaAssocieChambreOuClient("Le badge '" + idBadge + "' est déjà associé avec la chambre '"
														+ badge.getChambre().getId() + "'.");
		}
		if (badge.getClient() != null) {
			throw new BadgeDejaAssocieChambreOuClient("Le badge '" + idBadge + "' est déjà associé avec le client '"
														+ badge.getClient().getId() + "'.");
		}
		if (badge.getClefs() != null) {
			throw new OperationImpossible("Le badge '" + idBadge + "' a déjà une paire de clefs.");
		}
		
		badge.associerClient(client, true);
		badge.associerChambre(chambre, true);
		final PaireClefs nouvellePaireClefs = chambre.obtenirNouvellePaireClefs();
		badge.inscrireClefs(nouvellePaireClefs);
		chambre.inscrireClefs(nouvellePaireClefs);
		chambre.enregistrerChambre();
		assert this.invariant();
	}
	
	/**
	 * Opération cas d'utilisation "Libérer une chambre". Cherche la chambre et
	 * le badge potentiellement associés aux IDs fournis et s'il respectent
	 * certaines conditions, la chambre est alors libérée avec
	 * {@link Chambre#liberer()} et le badge est vidé de ses clefs avec
	 * {@link Badge#vider()}.
	 * 
	 * @param idChambre La chambre à libérer de son client.
	 * @param idBadge   Le badge du client.
	 * @param idClient  L'id du client.
	 */
	public void libererChambre(final long idChambre, final long idBadge, final long idClient)
			throws OperationImpossible {
		final Chambre chambre = this.chercherChambre(idChambre);
		final Badge badge = this.chercherBadge(idBadge);
		final Client client = this.chercherClient(idClient);
		
		if (chambre == null) {
			throw new ChambreInexistante("La chambre n'existe pas.");
		}
		if (badge == null) {
			throw new BadgeInexistant("Le badge n'existe pas.");
		}
		if (client == null) {
			throw new ClientInexistant("Le client n'existe pas.");
		}
		if (!chambre.estOccupee()) {
			throw new ChambreNonOccupee("La chambre n'est pas occupée, elle ne peut donc pas être libérée.");
		}
		if (client.getBadge().getChambre().getId() != chambre.getId()) {
			throw new ClientOccupeAutreChambre("Le client n'occupe pas cette chambre, il ne peut donc pas la libérer.");
		}
		
		chambre.liberer();
		badge.vider();
		assert this.invariant();
	}

	/**
	 * Creer badge badge.
	 *
	 * @param id the id
	 * @throws OperationImpossible the operation impossible
	 */
	public void creerBadge(final long id) throws OperationImpossible {
		final Badge currentBadge = this.chercherBadge(id);
		
		if (currentBadge != null) {
			throw new BadgeDejaPresent("Le badge '" + id + "' est déjà présent dans le système.");
		}
		
		final Badge badge = new Badge(id);
		this.badges.put(id, badge);
		assert this.invariant();

	}
	
	/**
	 * Cherche et renvoie le badge associée à l'identifiant donné. Si aucun
	 * badge n'a cet identifiant, {@code null} est alors renvoyé.
	 * 
	 * @param  id L'identifiant du badge à chercher.
	 * @return    Le badge associé à l'id, {@code null} sinon.
	 */
	public Badge chercherBadge(final long id) {
		return this.badges.get(id);
	}

	/**
	 * Creer client client.
	 *
	 * @param id     the id
	 * @param nom    the nom
	 * @param prenom the prenom
	 * @throws OperationImpossible the operation impossible
	 */
	public void creerClient(final long id, final String nom, final String prenom) throws OperationImpossible {
		Client currentClient = this.chercherClient(id);
		
		if (currentClient != null) {
			throw new ClientDejaPresent("Le client '" + id + "' est déjà présent dans le système.");
		}
		
		final Client client = new Client(id, nom, prenom);
		this.clients.put(id, client);
		assert this.invariant();

	}
	
	/**
	 * Cherche et renvoie le client associée à l'identifiant donné. Si aucun
	 * client n'a cet identifiant, {@code null} est alors renvoyé.
	 * 
	 * @param  id L'identifiant du client à chercher.
	 * @return    Le client associé à l'id, {@code null} sinon.
	 */
	public Client chercherClient(final long id) {
		return this.clients.get(id);
	}


	/**
	 * Instantiates a new Declarer perdu du chambre.
	 *
	 * @param idBadge the id badge
	 */
	public void declarerPerduDuBadge(final long idBadge) throws OperationImpossible {
		final Badge badge = chercherBadge(idBadge);
		if (badge == null) {
			throw new BadgeInexistant("Le badge n'existe pas.");
		}
		if (badge.getChambre() != null) {
			long idChambre = badge.getChambre().getId();
			long idClient = badge.getClient().getId();
			libererChambre(idChambre, idBadge, idClient);
		}
		this.badges.remove(idBadge);
	}




}
