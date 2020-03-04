package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Arrays;


/**
 * Paire de deux tableaux d'octets représentant des clés
 * servant à simuler ou déverrouiller des serrures.
 * 
 * @see Chambre
 * @see Badge
 * @see GestionClefsHotel
 * @author Paul Mabileau
 */
public class PaireClefs {
	/**
	 * La première clef de la paire.
	 */
	private final byte[] clef1;
	/**
	 * La deuxième clef de la paire.
	 */
	private final byte[] clef2;
	
	/**
	 * Construit la paire à partir des deux clefs fournies.
	 * @param clef1 La première clef de la paire.
	 * @param clef2 La deuxième clef de la paire.
	 */
	public PaireClefs(final byte[] clef1, final byte[] clef2) {
		this.clef1 = clef1.clone();
		this.clef2 = clef2.clone();
	}
	
	public boolean invariant() {
		return false;					// TODO invariant.
	}
	
	/**
	 * @return Une copie de la première clef.
	 */
	public byte[] getClef1() {
		return this.clef1.clone();
	}
	
	/**
	 * @return Une copie de la deuxième clef.
	 */
	public byte[] getClef2() {
		return this.clef2.clone();
	}
	
	/**
	 * Implémentation de hashCode() pour {@link PaireClefs}
	 * basée sur les deux clefs de la paire.
	 * <br><br>{@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.clef1);
		result = prime * result + Arrays.hashCode(this.clef2);
		return result;
	}
	
	/**
	 * Implémentation de equals() pour {@link PaireClefs}
	 * basée sur les deux clefs de la paire.
	 * <br><br>{@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof PaireClefs)) {
			return false;
		}
		
		final PaireClefs other = (PaireClefs) obj;
		
		if (!Arrays.equals(this.clef1, other.clef1)) {
			return false;
		}
		
		if (!Arrays.equals(this.clef2, other.clef2)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Implémentation de toString() pour {@link PaireClefs}
	 * basée sur les deux clefs de la paire.
	 * <br><br>{@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("PaireClefs [clef1 = %s, clef2 = %s]",
							Arrays.toString(this.clef1), Arrays.toString(this.clef2));
	}
}
