package eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.clef;

import java.util.Arrays;

import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.Util;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.salle.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;


/**
 * Paire de deux tableaux d'octets représentant des clés servant à simuler ou
 * déverrouiller des serrures.
 *
 * @author Paul Mabileau
 * @see    Chambre
 * @see    Badge
 * @see    GestionClefsHotel
 */
public class PaireClefs {
	/**
	 * La première clef de la paire.
	 */
	protected Clef clef1;
	/**
	 * La deuxième clef de la paire.
	 */
	protected Clef clef2;
	
	/**
	 * Instantiates a new Paire clefs.
	 *
	 * @param  clef1                      the clef 1
	 * @param  clef2                      the clef 2
	 * @throws ProblemeDansGenerationClef the probleme dans generation clef
	 */
	public PaireClefs(final Clef clef1, final Clef clef2) {
		this.clef1 = clef1;
		this.clef2 = clef2;
		assert this.invariant();
	}
	
	/**
	 * Invariant boolean.
	 *
	 * @return the boolean
	 */
	public boolean invariant() {
		return this.clef1 != null && this.clef1.getValue().length == Util.TAILLE_CLEF
				&& this.clef2 != null && this.clef2.getValue().length == Util.TAILLE_CLEF;
	}
	
	/**
	 * @return Une copie de la première clef.
	 */
	public Clef getClef1() {
		return this.clef1;
	}
	
	/**
	 * @return Une copie de la deuxième clef.
	 */
	public Clef getClef2() {
		return this.clef2;
	}
	
	/**
	 * Implémentation de hashCode() pour {@link PaireClefs} basée sur les deux
	 * clefs de la paire. <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.clef1.getValue());
		result = prime * result + Arrays.hashCode(this.clef2.getValue());
		return result;
	}
	
	/**
	 * Implémentation de equals() pour {@link PaireClefs} basée sur les deux
	 * clefs de la paire. <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof PaireClefs)) {
			return false;
		}
		
		final PaireClefs other = (PaireClefs) obj;
		
		if (!Arrays.equals(this.clef1.getValue(), other.clef1.getValue())) {
			return false;
		}
		
		if (!Arrays.equals(this.clef2.getValue(), other.clef2.getValue())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Implémentation de toString() pour {@link PaireClefs} basée sur les deux
	 * clefs de la paire. <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("PaireClefs [clef1 = %s, clef2 = %s]",
							Util.clefToString(this.clef1.getValue()),
							Util.clefToString(this.clef2.getValue()));
	}
}
