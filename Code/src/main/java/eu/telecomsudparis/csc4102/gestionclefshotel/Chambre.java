package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;


/**
 * Classe représentant une chambre dans le système de
 * gestion des clefs d'un hôtel. D'un point de vue modèle,
 * elle est reliée bi-directionnellement à {@link Badge}
 * et uni-directionnellement à {@link PaireClefs}.
 * 
 * @see Badge
 * @see Client
 * @see PaireClefs
 * @see GestionClefsHotel
 * @author Paul Mabileau
 */
public class Chambre {
	/**
	 * L'identifiant unique de la chambre.
	 */
	private final long id;
	/**
	 * La graine de génération des clés propre à la chambre.
	 */
	private final String graine;
	/**
	 * Le sel pour la génération de clés. À incrémenter à
	 * chaque renouvellement de clé.
	 */
	private int sel;
	/**
	 * Attribut redondant indiquant si la chambre est occupée
	 * ou non.
	 */
	private boolean occupee;
	/**
	 * Le badge auquel la chambre est potentiellement associé.
	 */
	private Badge badge;
	/**
	 * La paire de clés utilisée pour représenter la serrure.
	 */
	private PaireClefs paireClefs;
	
	/**
	 * Construit une nouvelle chambre et initialise sa paire
	 * de clés à partir des paramètres fournis.
	 * 
	 * @param id L'identifiant que va prendre la chambre.
	 * @param graine La graine servant aux générations de
	 *		paires de clés.
	 * @param sel Le sel servant aux générations de paires
	 *		de clés qui va être incrémentés à chaque fois.
	 * @throws ProblemeDansGenerationClef Proviens de la
	 *		génération de clés réalisée par {@link Util}.
	 */
	public Chambre(final long id, final String graine, final int sel) throws ProblemeDansGenerationClef {
		this.id = id;
		this.graine = graine;
		this.sel = sel;
		this.occupee = false;
		
		final byte[] clef1 = Util.genererUneNouvelleClef(graine, Integer.toString(sel));
		this.sel++;
		final byte[] clef2 = Util.genererUneNouvelleClef(graine, Integer.toString(sel));
		this.sel++;
		this.paireClefs = new PaireClefs(clef1, clef2);
		assert this.invariant();
	}
	
	public boolean invariant() {
		return Long.toString(id) != null 
				&& graine != null && !graine.equals("") 
				&& Integer.toString(sel) != null 
				&& ( occupee == true && badge != null )|| (occupee == false && badge == null)
				&& paireClefs != null;				
	}
	
	/**
	 * @return L'identifiant de la chambre.
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * @return Le badge auquel est potentiellement associé
	 *			la chambre. Peut être {@code null}.
	 */
	public Badge getBadge() {
		return this.badge;
	}
	
	/**
	 * Associe le badge fourni à la chambre sans associer
	 * la chambre au badge, c'est donc unidirectionnel.
	 * 
	 * @param badge Le badge à associer.
	 * @see #associerBadge(Badge, boolean)
	 */
	public void associerBadge(final Badge badge) {
		this.associerBadge(badge, false);
	}
	
	/**
	 * Associe le badge fourni à la chambre donnée et
	 * vice-versa si {@code bidirectionnel} vaut {@code
	 * true}.
	 * 
	 * @param badge Le badge à associer.
	 * @param bidirectionnel S'il faut aussi associer la
	 *		chambre au badge.
	 * @see #associerBadge(Badge)
	 */
	public void associerBadge(final Badge badge, boolean bidirectionnel) {
		this.badge = badge;
		
		if (bidirectionnel) {
			this.badge.associerChambre(this, false);
		}
	}
	
	/**
	 * Rompt le lien d'association de la chambre vers son
	 * badge sans en faire de même pour la direction opposée.
	 * 
	 * @see #dissocierBadge(boolean)
	 */
	public void dissocierBadge() {
		this.dissocierBadge(false);
	}
	
	/**
	 * Rompt le lien d'association de la chambre vers son
	 * badge en en faisant de même pour la direction opposée.
	 * 
	 * @param bidirectionnel S'il faut aussi dissocier dans
	 *		l'autre sens.
	 * @see #dissocierBadge()
	 */
	public void dissocierBadge(boolean bidirectionnel) {
		if (bidirectionnel && this.badge != null) {
			this.badge.dissocierClient(false);
		}
		
		this.badge = null;
	}
	
	/**
	 * @return La graine de génération de clés de la chambre.
	 */
	public String getGraine() {
		return this.graine;
	}
	
	/**
	 * @return La sel de génération de clés de la chambre.
	 */
	public int getSel() {
		return this.sel;
	}
	
	/**
	 * @return Si la chambre est occupée ou non.
	 */
	public boolean estOccupee() {
		return this.occupee;
	}
	
	/**
	 * @return La {@link PaireClefs} de la chambre.
	 */
	public PaireClefs getClefs() {
		return this.paireClefs;
	}
	
	/**
	 * Génère une nouvelle {@link PaireClefs} pour la chambre
	 * en mettant l'ancienne clef n°2 comme nouvelle clef n°1
	 * et en générant une nouvelle clef n°2 à partir des membres
	 * {@link #graine} et {@link #sel} en incrémentant ce dernier.
	 * 
	 * @return La nouvelle paire ainsi générée.
	 * @throws ProblemeDansGenerationClef Si la génération
	 *		s'est mal produite.
	 */
	public PaireClefs obtenirNouvellePaireClefs() throws ProblemeDansGenerationClef {
		this.paireClefs = new PaireClefs(this.paireClefs.getClef1(),
										Util.genererUneNouvelleClef(this.graine,
																	String.format("%010d%n", this.sel)));
		this.sel++;
		assert invariant();
		return this.paireClefs;
	}
	
	/**
	 * Enregistre la paire de clefs données dans la chambre.
	 * @param paireClefs L'objet {@link PaireClefs} à stocker.
	 */
	public void inscrireClefs(final PaireClefs paireClefs) {
		this.paireClefs = paireClefs;
	}
	
	/**
	 * Libère la chambre de son client en le dissociant du
	 * badge associé à la chambre (bidirectionnellement).
	 * Maintient le lien entre la chambre et son badge. Marque
	 * la chambre comme non occupée.
	 */
	public void liberer() {
		this.occupee = false;
		this.badge.dissocierClient(true);
		assert invariant();
	}
	
	/**
	 * Implémentation de hashCode() pour {@link Chambre}
	 * basée sur les membres {@link #id}, {@link #graine}
	 * et {@link #sel}.
	 * <br><br>{@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.graine == null ? 0 : this.graine.hashCode());
		result = prime * result + (int) (this.id ^ this.id >>> 32);
		result = prime * result + this.sel;
		return result;
	}
	
	/**
	 * Implémentation de equals() pour {@link Chambre} pour
	 * laquelle l'égalité est basée sur les membres {@link
	 * #id}, {@link #graine} et {@link #sel}.
	 * <br><br>{@inheritDoc}
	 * @return Si la chambre est égale à l'objet donné.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Chambre)) {
			return false;
		}
		
		final Chambre other = (Chambre) obj;
		
		if (this.graine == null) {
			if (other.graine != null) {
				return false;
			}
		}
		else if (!this.graine.equals(other.graine)) {
			return false;
		}
		
		if (this.id != other.id) {
			return false;
		}
		
		if (this.sel != other.sel) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Implémentation de toString() pour {@link Chambre}.
	 * <br><br>{@inheritDoc}
	 * @return Une représentation textuelle de la chambre.
	 */
	@Override
	public String toString() {
		return String.format("Chambre [id = %s, graine = %s, sel = %s, occupee = %s, paireClefs = %s]",
							this.id, this.graine, this.sel, this.occupee, this.paireClefs);
	}
}
