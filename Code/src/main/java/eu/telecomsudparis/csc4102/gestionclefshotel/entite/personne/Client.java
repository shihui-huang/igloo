package eu.telecomsudparis.csc4102.gestionclefshotel.entite.personne;

import java.util.Objects;

import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.salle.Chambre;

/**
 * Classe représentant un client occupant une chambre au travers d'un badge
 * pouvant en déverrouiller la porte. Les associations intermédiaires sont à
 * sens double.
 * 
 * @see    Chambre
 * @see    Badge
 * @see    GestionClefsHotel
 * @author Paul Mabileau
 * @author Shihui HUANG
 */
public class Client {
	/**
	 * L'identifiant unique du client.
	 */
	private final long id;
	/**
	 * Le nom de famille du client.
	 */
	private final String nom;
	/**
	 * Le prénom du client.
	 */
	private final String prenom;
	/**
	 * Le badge potentiellement associé au client.
	 */
	private Badge badge;
	
	/**
	 * Construit le client en enregistrant les paramètres fournis.
	 * 
	 * @param id       L'identifiant unique du client.
	 * @param nom      Le nom de famille du client.
	 * @param prenom   Le prénom du client.
	 */
	public Client(final long id, final String nom, final String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		
		assert this.invariant();
	}

	/**
	 * Invariant boolean.
	 *
	 * @return the boolean
	 */
	public boolean invariant() {
		return this.nom != null && !this.nom.equals("")
				&& this.prenom != null && !this.prenom.equals("");
	}
	
	/**
	 * @return L'identifiant du client.
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * @return Le nom de famille du client.
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * @return Le prénom du client.
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	/**
	 * @return Le badge auquel est potentiellement associé le client.
	 */
	public Badge getBadge() {
		return this.badge;
	}
	
	/**
	 * Associe de manière unidirectionnelle le badge au client, c'est-à-dire
	 * sans enregistrer le client dans le badge.
	 * 
	 * @param badge Le badge à associer.
	 * @see         #associerBadge(Badge, boolean)
	 */
	public void associerBadge(final Badge badge) {
		this.associerBadge(badge, false);
	}
	
	/**
	 * Associe le badge donné au client et dans l'autre sens aussi si
	 * {@code bidirectionnel} vaut {@code true}.
	 * 
	 * @param badge          Le badge à associer.
	 * @param bidirectionnel S'il faut aussi faire enregistrer le client par le
	 *                       badge.
	 * @see                  #associerBadge(Badge)
	 */
	public void associerBadge(final Badge badge, final boolean bidirectionnel) {
		this.badge = badge;
		
		if (bidirectionnel) {
			this.badge.associerClient(this, false);
		}
	}
	
	/**
	 * Rompt le lien d'association du client vers son badge sans en faire de
	 * même pour la direction opposée.
	 * 
	 * @see #dissocierBadge(boolean)
	 */
	public void dissocierBadge() {
		this.dissocierBadge(false);
	}
	
	/**
	 * Rompt le lien d'association du client vers son badge en en faisant de
	 * même pour la direction opposée si {@code bidirectionnel} vaut
	 * {@code true}.
	 * 
	 * @param bidirectionnel S'il faut aussi dissocier dans l'autre sens.
	 * @see                  #dissocierBadge()
	 */
	public void dissocierBadge(final boolean bidirectionnel) {
		if (bidirectionnel && this.badge != null) {
			this.badge.dissocierClient(false);
		}
		
		this.badge = null;
	}
	
	/**
	 * Implémentation de hashCode() pour {@link Client} basée sur les membres
	 * {@link #id}, {@link #nom} et {@link #prenom}. <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Implémentation de equals() pour {@link Client} pour laquelle l'égalité
	 * est basée sur les membres {@link #id}, {@link #nom} et {@link #prenom}.
	 * <br>
	 * <br>
	 * {@inheritDoc}
	 * 
	 * @return Si le client est égal à l'objet donné.
	 */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Client client = (Client) o;
		return id == client.id;
	}

	/**
	 * Implémentation de toString() pour {@link Client}. <br>
	 * <br>
	 * {@inheritDoc}
	 * 
	 * @return Une représentation textuelle du client.
	 */
	@Override
	public String toString() {
		return String.format("Client [id = %s, nom = %s, prenom = %s]",
							this.id, this.nom, this.prenom);
	}
}
