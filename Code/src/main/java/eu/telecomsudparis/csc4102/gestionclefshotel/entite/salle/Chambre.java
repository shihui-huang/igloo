package eu.telecomsudparis.csc4102.gestionclefshotel.entite.salle;

import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.Util;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.clef.Clef;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.clef.PaireClefs;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.personne.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;

import java.util.Objects;


/**
 * Classe représentant une chambre dans le système de gestion des clefs d'un
 * hôtel. D'un point de vue modèle, elle est reliée bi-directionnellement à
 * {@link Badge} et uni-directionnellement à {@link PaireClefs}.
 * 
 * @see    Badge
 * @see    Client
 * @see    PaireClefs
 * @see    GestionClefsHotel
 * @author Paul Mabileau
 * @author Shihui HUANG
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
	 * Le sel pour la génération de clés. À incrémenter à chaque renouvellement
	 * de clé.
	 */
	private int sel;
	/**
	 * Attribut redondant indiquant si la chambre est occupée ou non.
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
	 * Construit une nouvelle chambre et initialise sa paire de clés à partir
	 * des paramètres fournis.
	 * 
	 * @param  id                         L'identifiant que va prendre la
	 *                                    chambre.
	 * @param  graine                     La graine servant aux générations de
	 *                                    paires de clés.
	 * @param  sel                        Le sel servant aux générations de
	 *                                    paires de clés qui va être incrémentés
	 *                                    à chaque fois.
	 * @throws ProblemeDansGenerationClef Proviens de la génération de clés
	 *                                    réalisée par {@link Util}.
	 */
	public Chambre(final long id, final String graine, final int sel) throws ProblemeDansGenerationClef {
		this.id = id;
		this.graine = graine;
		this.sel = sel;
		this.occupee = false;
		
		final Clef clef1 = new Clef(Util.genererUneNouvelleClef(graine, Integer.toString(sel)));
		this.sel++;
		final Clef clef2 = new Clef(Util.genererUneNouvelleClef(graine, Integer.toString(sel)));
		this.sel++;
		this.paireClefs = new PaireClefs(clef1, clef2);
		
		assert this.invariant();
	}

	/**
	 * Invariant boolean.
	 *
	 * @return the boolean
	 */
	public boolean invariant() {
		return this.graine != null && !this.graine.equals("")
				&& (this.occupee  && this.badge != null
					|| !this.occupee && this.badge == null)
				&& this.paireClefs != null;
	}
	
	/**
	 * @return L'identifiant de la chambre.
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * @return Le badge auquel est potentiellement associé la chambre. Peut être
	 *         {@code null}.
	 */
	public Badge getBadge() {
		return this.badge;
	}
	
	/**
	 * Associe le badge fourni à la chambre sans associer la chambre au badge,
	 * c'est donc unidirectionnel.
	 * 
	 * @param badge Le badge à associer.
	 * @see         #associerBadge(Badge, boolean)
	 */
	public void associerBadge(final Badge badge) {
		this.associerBadge(badge, false);
	}
	
	/**
	 * Associe le badge fourni à la chambre donnée et vice-versa si
	 * {@code bidirectionnel} vaut {@code
	 * true}.
	 * 
	 * @param badge          Le badge à associer.
	 * @param bidirectionnel S'il faut aussi associer la chambre au badge.
	 * @see                  #associerBadge(Badge)
	 */
	public void associerBadge(final Badge badge, final boolean bidirectionnel) {
		this.badge = badge;
		
		if (bidirectionnel) {
			this.badge.associerChambre(this, false);
		}
	}
	
	/**
	 * Rompt le lien d'association de la chambre vers son badge sans en faire de
	 * même pour la direction opposée.
	 * 
	 * @see #dissocierBadge(boolean)
	 */
	public void dissocierBadge() {
		this.dissocierBadge(false);
	}
	
	/**
	 * Rompt le lien d'association de la chambre vers son badge en en faisant de
	 * même pour la direction opposée.
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
	 * Génère une nouvelle {@link PaireClefs} pour la chambre en mettant
	 * l'ancienne clef n°2 comme nouvelle clef n°1 et en générant une nouvelle
	 * clef n°2 à partir des membres {@link #graine} et {@link #sel} en
	 * incrémentant ce dernier.
	 * 
	 * @return                            La nouvelle paire ainsi générée.
	 * @throws ProblemeDansGenerationClef Si la génération s'est mal produite.
	 */
	public PaireClefs obtenirNouvellePaireClefs() throws ProblemeDansGenerationClef {
		Clef clef1 = new Clef(this.paireClefs.getClef2());
		Clef clef2 = new Clef(Util.genererUneNouvelleClef(this.graine, String.format("%010d%n", this.sel)));
		PaireClefs newPaireClefs = new PaireClefs(clef1, clef2);
		this.sel++;
		return newPaireClefs;
	}
	
	/**
	 * Enregistre la paire de clefs données dans la chambre.
	 * 
	 * @param paireClefs L'objet {@link PaireClefs} à stocker.
	 */
	public void inscrireClefs(final PaireClefs paireClefs) {
		this.paireClefs = paireClefs;
	}
	
	/**
	 * Libère la chambre de son client en le dissociant du badge associé à la
	 * chambre (bidirectionnellement). Maintient le lien entre la chambre et son
	 * badge. Marque la chambre comme non occupée.
	 */
	public void liberer() {
		this.occupee = false;
		this.badge.dissocierClient(true);
		this.badge.dissocierChambre(true);
		
		assert this.invariant();
	}
	/**
	 * Enregistrer la chambre en marquant la chambre comme non occupée.
	 */
	public void enregistrerChambre() {
		this.occupee = true;
		assert this.invariant();
	}

	/**
	 * Implémentation de equals() pour {@link Chambre} pour laquelle l'égalité
	 * est basée sur les membres {@link #id}, {@link #graine} et {@link #sel}.
	 * <br>
	 * <br>
	 * {@inheritDoc}
	 *
	 * @return Si la chambre est égale à l'objet donné.
	 */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Chambre chambre = (Chambre) o;
		return id == chambre.id;
	}

	/**
	 * Implémentation de hashCode() pour {@link Chambre} basée sur les membres
	 * {@link #id}, {@link #graine} et {@link #sel}. <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Implémentation de toString() pour {@link Chambre}. <br>
	 * <br>
	 * {@inheritDoc}
	 *
	 * @return Une représentation textuelle de la chambre.
	 */
	@Override
	public String toString() {
		return String.format("Chambre [id = %s, graine = %s, sel = %s, occupee = %s, paireClefs = %s]",
								this.id, this.graine, this.sel, this.occupee, this.paireClefs);
	}
}
