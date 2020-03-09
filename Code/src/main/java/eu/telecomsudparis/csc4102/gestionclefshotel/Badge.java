package eu.telecomsudparis.csc4102.gestionclefshotel;


/**
 * Classe représentant un badge d'accès permettant de
 * déverrouiller la serrure d'une chambre qui lui a été
 * associée. D'un point de vue modèle, elle est reliée
 * bi-directionnellement à {@link Chambre} et à {@link
 * Client} et uni-directionnellement à {@link PaireClefs}. 
 * 
 * @see Chambre
 * @see Client
 * @see PaireClefs
 * @see GestionClefsHotel
 * @author Paul Mabileau
 */
public class Badge {
	/**
	 * L'identifiant unique du badge.
	 */
	private final long id;
	/**
	 * La paire de clés permettant de déverrouiller la
	 * serrure correspondante.
	 */
	private PaireClefs paireClefs;
	/**
	 * La {@link Chambre} à laquelle est potentiellement
	 * associée le badge.
	 */
	private Chambre chambre;
	/**
	 * Le {@link Client} auquel est potentiellement associé
	 * le badge.
	 */
	private Client client;
	
	/**
	 * Construit le badge en enregistrant l'identifiant donné.
	 * @param id L'identifiant unique que le badge doit avoir.
	 */
	public Badge(final long id) {
		this.id = id;
		assert this.invariant();
	}
	
	public boolean invariant() {
		return
					((
						client != null 
						&& chambre != null 
						&& paireClefs != null 
						&& chambre.getBadge().equals(this)
						&& client.getBadge().equals(this)
					) || ( 
							client == null 
							&& chambre == null 
							&& paireClefs == null 	
					));
				
	}
	
	/**
	 * @return L'identifiant du badge.
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * @return La {@link PaireClefs} stocké dans le badge.
	 */
	public PaireClefs getClefs() {
		return this.paireClefs;
	}
	
	/**
	 * Enregistre la {@link PaireClefs} fournie dans le badge.
	 * @param paireClefs La paire de clefs à stocker.
	 */
	public void inscrireClefs(final PaireClefs paireClefs) {
		this.paireClefs = paireClefs;
	}
	
	/**
	 * Vide le badge de sa paire de clefs et dissocie la chambre.
	 */
	public void vider() {
		if (this.chambre != null) {
			this.chambre.dissocierBadge();
		}
		if (this.client != null) {
			this.dissocierClient();
		}

		this.paireClefs = null;
	}
	
	/**
	 * @return La chambre à laquelle est potentiellement associée
	 *		le badge.
	 */
	public Chambre getChambre() {
		return this.chambre;
	}
	
	/**
	 * Associe de manière unidirectionnelle la chambre au badge,
	 * c'est-à-dire sans enregistrer le badge dans la chambre.
	 *   
	 * @param chambre La chambre à associer.
	 * @see #associerChambre(Chambre, boolean)
	 */
	public void associerChambre(final Chambre chambre) {
		this.associerChambre(chambre, false);
	}
	
	/**
	 * Associe la chambre donnée au badge et dans l'autre sens
	 * aussi si {@code bidirectionnel} vaut {@code true}.
	 * 
	 * @param chambre La chambre à associer.
	 * @param bidirectionnel S'il faut aussi faire enregistrer le
	 *		badge par la chambre.
	 * @see #associerChambre(Chambre)
	 */
	public void associerChambre(final Chambre chambre, boolean bidirectionnel) {
		this.chambre = chambre;
		
		if (bidirectionnel) {
			this.chambre.associerBadge(this, false);
		}
	}
	
	/**
	 * Rompt le lien d'association du badge vers sa chambre
	 * sans en faire de même pour la direction opposée.
	 * 
	 * @see #dissocierChambre(boolean)
	 */
	public void dissocierChambre() {
		this.dissocierChambre(false);
	}
	
	/**
	 * Rompt le lien d'association du badge vers sa chambre
	 * en en faisant de même pour la direction opposée si
	 * {@code bidirectionnel} vaut {@code true}.
	 * 
	 * @param bidirectionnel S'il faut aussi dissocier dans
	 *		l'autre sens.
	 * @see #dissocierChambre()
	 */
	public void dissocierChambre(boolean bidirectionnel) {
		if (bidirectionnel && this.chambre != null) {
			this.chambre.dissocierBadge(false);
		}
		
		this.chambre = null;
	}
	
	/**
	 * @return Le client auquel est potentiellement associé
	 *		le badge.
	 */
	public Client getClient() {
		return this.client;
	}
	
	/**
	 * Associe de manière unidirectionnelle le client au badge,
	 * c'est-à-dire sans enregistrer le badge dans la chambre.
	 *   
	 * @param client Le client à associer.
	 * @see #associerChambre(Client, boolean)
	 */
	public void associerClient(final Client client) {
		this.associerClient(client, false);
	}
	
	/**
	 * Associe le client donné au badge et dans l'autre sens
	 * aussi si {@code bidirectionnel} vaut {@code true}.
	 * 
	 * @param client Le client à associer.
	 * @param bidirectionnel S'il faut aussi faire enregistrer le
	 *		badge par le client.
	 * @see #associerClient(Client)
	 */
	public void associerClient(final Client client, boolean bidirectionnel) {
		this.client = client;
		
		if (bidirectionnel) {
			this.client.associerBadge(this, false);
		}
	}
	
	/**
	 * Rompt le lien d'association du badge vers son client
	 * sans en faire de même pour la direction opposée.
	 * 
	 * @see #dissocierClient(boolean)
	 */
	public void dissocierClient() {
		this.dissocierClient(false);
	}
	
	/**
	 * Rompt le lien d'association du badge vers son client
	 * en en faisant de même pour la direction opposée si
	 * {@code bidirectionnel} vaut {@code true}.
	 * 
	 * @param bidirectionnel S'il faut aussi dissocier dans
	 *		l'autre sens.
	 * @see #dissocierClient()
	 */
	public void dissocierClient(boolean bidirectionnel) {
		if (bidirectionnel && this.client != null) {
			this.client.dissocierBadge(false);
		}
		
		this.client = null;
	}
	
	/**
	 * Implémentation de hashCode() pour {@link Badge} basée
	 * sur {@link #id}.
	 * <br><br>{@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.id ^ this.id >>> 32);
		return result;
	}
	
	/**
	 * Implémentation de equals() pour {@link Badge} pour
	 * laquelle l'égalité est basée {@link #id}.
	 * <br><br>{@inheritDoc}
	 * @return Si le badge est égal à l'objet donné.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Badge)) {
			return false;
		}
		
		final Badge other = (Badge) obj;
		
		if (this.id != other.id) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Implémentation de toString() pour {@link Badge}.
	 * <br><br>{@inheritDoc}
	 * @return Une représentation textuelle du badge.
	 */
	@Override
	public String toString() {
		return String.format("Badge [id = %s, paireClefs = %s, chambre = %s, client = %s]",
							this.id, this.paireClefs, this.chambre, this.client);
	}
}
