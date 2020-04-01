package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.*;
import java.util.stream.Collectors;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.clef.Clef;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.clef.PaireClefs;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.personne.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.salle.Chambre;
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
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientOccupeAucuneChambre;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import java.util.concurrent.SubmissionPublisher;

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
	 * l'ensemble des paires de clef.
	 */
	private List<PaireClefs> pairesClefs;

	/**
	 * l'ensemble des clefs.
	 */
	private List<Clef> clefs;



	private SubmissionPublisher<String> publisher;


	/**
	 * Construit la façade. Initialise les collections.
	 */
	public GestionClefsHotel() {
		this.chambres = new HashMap<Long, Chambre>();
		this.badges = new HashMap<Long, Badge>();
		this.clients = new HashMap<Long, Client>();
		this.clefs = new ArrayList<Clef>();
		this.pairesClefs = new ArrayList<PaireClefs>();
		this.publisher = new SubmissionPublisher<>();
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
	 * @param id     L'identifiant de la chambre à créer.
	 * @param graine La graine de génération de clés de la                                    chambre.
	 * @param sel    Le sel de génération de clés de la                                    chambre.
	 * @throws OperationImpossible Si la génération est cassée en mille                                    morceaux.
	 */
	public void creerChambre(final long id, final String graine, final int sel) throws OperationImpossible, InterruptedException {
		final Optional<Chambre> current = this.chercherChambre(id);

		if (graine == null || graine.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("Graine null ou vide non autorisée.");
		}
		if (current.isPresent()) {
			throw new ChambreDejaPresente("Chambre '" + id + "' déjà présente dans le système.");
		}

		final Chambre chambre = new Chambre(id, graine, sel);
		this.chambres.put(id, chambre);

		PaireClefs paireClefs = chambre.getClefs();
		Clef clef1 = paireClefs.getClef1();
		Clef clef2 = paireClefs.getClef2();

		if (clefs.contains(clef1)) {
			publisher.submit("\"Doublon de clef1 pour la chambre " + id + " lors de la création de chambre.\"");

		} else {
			clefs.add(clef1);
		}

		if (clefs.contains(clef2)) {
			publisher.submit("\"Doublon de clef2 pour la chambre " + id + " lors de la création de chambre.\"");


		} else {
			clefs.add(clef2);
		}

		if (pairesClefs.contains(paireClefs)) {
			publisher.submit("\"Doublon de pairede clef pour la chambre " + id + " lors de la création de chambre.\"");


		} else {
			pairesClefs.add(paireClefs);
		}


		assert this.invariant();
	}

	/**
	 * Cherche et renvoie la chambre associée à l'identifiant donné. Si aucune
	 * chambre n'a cet identifiant, {@code null} est alors renvoyé.
	 *
	 * @param id L'identifiant de la chambre à chercher.
	 * @return La chambre associé à l'id, {@code null} sinon.
	 */
	public Optional<Chambre> chercherChambre(final long id) {
		return chambres.values()
				.stream()
				.filter(chambre -> chambre.getId() == id)
				.findFirst();
	}

	/**
	 * Lister chambres string.
	 *
	 * @return Une collection des chambres de l'hôtel.
	 */
	public String listerChambres() {
		return chambres.values()
				.stream()
				.map(Chambre::toString)
				.collect(Collectors.joining("\n"));
	}

	/**
	 * Opération cas d'utilisation "Enregistrer l'occupation d'une chambre par
	 * un client". Cherche la chambre, le badge et le client associés aux
	 * identifiants fournis, et si ceux-ci réunissent les conditions
	 * nécessaires, le badge va alors être associé bidirectionnellement au
	 * client et de même à la chambre qui obtient une nouvelle paire de clefs
	 * enfin inscrite dans le badge.
	 *
	 * @param idChambre La chambre à faire occuper par le                                    client.
	 * @param idBadge   Le badge qui va servir à ouvrir la                                    chambre.
	 * @param idClient  Le client payant pour la chambre.
	 * @throws OperationImpossible Si la génération est cassée en mille                                    morceaux.
	 */
	public void enregistrerOccupationChambre(final long idChambre, final long idBadge, final long idClient)
			throws OperationImpossible, InterruptedException {
		final Optional<Chambre> chambre = this.chercherChambre(idChambre);
		final Optional<Badge> badge = this.chercherBadge(idBadge);
		final Optional<Client> client = this.chercherClient(idClient);

		if (!chambre.isPresent()) {
			throw new ChambreInexistante("La chambre n'existe pas, il faut d'abord la créer.");
		}
		if (!badge.isPresent()) {
			throw new BadgeInexistant("Le badge n'existe pas, il faut d'abord le créer.");
		}
		if (!client.isPresent()) {
			throw new ClientInexistant("Le client n'existe pas, il faut d'abord le créer.");
		}
		if (client.get().getBadge() != null) {
			throw new ClientOccupeDejaChambre("Le client occupe déjà une chambre.");
		}
		if (chambre.get().estOccupee()) {
			throw new ChambreDejaOccupee("La chambre '" + idChambre + "' est déjà occupée.");
		}

		if (badge.get().getChambre() != null) {
			throw new BadgeDejaAssocieChambreOuClient("Le badge '" + idBadge + "' est déjà associé avec la chambre '"
														+ badge.get().getChambre().getId() + "'.");
		}
		if (badge.get().getClient() != null) {
			throw new BadgeDejaAssocieChambreOuClient("Le badge '" + idBadge + "' est déjà associé avec le client '"
														+ badge.get().getClient().getId() + "'.");
		}
		if (badge.get().getClefs() != null) {
			throw new OperationImpossible("Le badge '" + idBadge + "' a déjà une paire de clefs.");
		}

		badge.get().associerClient(client.get(), true);
		badge.get().associerChambre(chambre.get(), true);

		final PaireClefs nouvellePaireClefs = chambre.get().obtenirNouvellePaireClefs();
		badge.get().inscrireClefs(nouvellePaireClefs);
		chambre.get().inscrireClefs(nouvellePaireClefs);
		chambre.get().enregistrerChambre();

		Clef clef = chambre.get().getClefs().getClef2();
		if (clefs.contains(clef)) {
			publisher.submit("\"Doublon de nouveau clef pour la chambre " +idChambre + " lors de la enregisterOccupationChambre chambre.\"");

		} else {
			clefs.add(clef);
		}
		if (pairesClefs.contains(nouvellePaireClefs)) {
			publisher.submit("\"Doublon de nouveau clef pour la chambre " +idChambre + " lors de la enregisterOccupationChambre chambre.\"");

		} else {
			pairesClefs.add(nouvellePaireClefs);
		}

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
	 * @throws OperationImpossible the operation impossible
	 */
	public void libererChambre(final long idChambre, final long idBadge, final long idClient)
			throws OperationImpossible {
		final Optional<Chambre> chambre = this.chercherChambre(idChambre);
		final Optional<Badge> badge = this.chercherBadge(idBadge);
		final Optional<Client> client = this.chercherClient(idClient);

		if (!chambre.isPresent()) {
			throw new ChambreInexistante("La chambre n'existe pas.");
		}
		if (!badge.isPresent()) {
			throw new BadgeInexistant("Le badge n'existe pas.");
		}
		if (!client.isPresent()) {
			throw new ClientInexistant("Le client n'existe pas.");
		}
		if (!chambre.get().estOccupee()) {
			throw new ChambreNonOccupee("La chambre n'est pas occupée, elle ne peut donc pas être libérée.");
		}
		if (client.get().getBadge() == null) {
			throw new ClientOccupeAucuneChambre("Le client n'occupe aucune chambre, il ne peut donc pas la libérer.");
		}
		if (client.get().getBadge().getChambre().getId() != chambre.get().getId()) {
			throw new ClientOccupeAutreChambre("Le client n'occupe pas cette chambre, il ne peut donc pas la libérer.");
		}

		chambre.get().liberer();
		badge.get().vider();
		assert this.invariant();
	}

	/**
	 * Creer badge badge.
	 *
	 * @param id the id
	 * @throws OperationImpossible the operation impossible
	 */
	public void creerBadge(final long id) throws OperationImpossible {
		final Optional<Badge> currentBadge = this.chercherBadge(id);

		if (currentBadge.isPresent()) {
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
	 * @param id L'identifiant du badge à chercher.
	 * @return Le badge associé à l'id, {@code null} sinon.
	 */
	public Optional<Badge> chercherBadge(final long id) {
		return badges.values()
				.stream()
				.filter(badge -> badge.getId() == id)
				.findFirst();

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
		Optional<Client> currentClient = this.chercherClient(id);

		if (currentClient.isPresent()) {
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
	 * @param id L'identifiant du client à chercher.
	 * @return Le client associé à l'id, {@code null} sinon.
	 */
	public Optional<Client> chercherClient(final long id) {
		return clients.values()
				.stream()
				.filter(client -> client.getId() == id)
				.findFirst();
	}


	/**
	 * Instantiates a new Declarer perdu du Badge sans remplacement.
	 *
	 * @param idBadge the id badge
	 * @throws OperationImpossible the operation impossible
	 */
	public void declarerPerduDuBadgeSansRemplacement(final long idBadge) throws OperationImpossible {
		final Optional<Badge> badge = chercherBadge(idBadge);
		if (!badge.isPresent()) {
			throw new BadgeInexistant("Le badge n'existe pas.");
		}
		if (badge.get().getChambre() != null) {
			long idChambre = badge.get().getChambre().getId();
			long idClient = badge.get().getClient().getId();
			libererChambre(idChambre, idBadge, idClient);
		}
		this.badges.remove(idBadge);
	}

	/**
	 * Declarer perdu du badge avec remplacement.
	 *
	 * @param idBadgePerdu    the id badge perdu
	 * @param idBadgeRemplace the id badge remplace
	 * @throws OperationImpossible  the operation impossible
	 * @throws InterruptedException the interrupted exception
	 */
	public void declarerPerduDuBadgeAvecRemplacement(final long idBadgePerdu,final long idBadgeRemplace) throws OperationImpossible, InterruptedException {
		final Optional<Badge> badgePerdu = chercherBadge(idBadgePerdu);
		final Optional<Badge> badgeRemplace = chercherBadge(idBadgeRemplace);
		if (!badgePerdu.isPresent()) {
			throw new BadgeInexistant("Le badgePerdu n'existe pas.");
		}
		if (!badgeRemplace.isPresent()) {
			throw new BadgeInexistant("Le badgeRemplace n'existe pas.");
		}
		if (badgePerdu.get().getChambre() != null) {
			long idChambre = badgePerdu.get().getChambre().getId();
			long idClient = badgePerdu.get().getClient().getId();
			libererChambre(idChambre, idBadgePerdu, idClient);
			enregistrerOccupationChambre(idChambre,idBadgeRemplace,idClient);
		}
		this.badges.remove(idBadgePerdu);

	}


}

