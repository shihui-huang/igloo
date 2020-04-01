package eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.clef;

import java.util.Arrays;

import eu.telecomsudparis.csc4102.gestionclefshotel.Util;


public class Clef {
	/**
	 * La clef.
	 */
	protected byte[] value;

	/**
	 * Instantiates a new Clef.
	 *
	 * @param clef the clef
	 */
	public Clef(final Clef clef) {
		this.value = clef.value.clone();
	}

	/**
	 * Instantiates a new Clef.
	 *
	 * @param value the value
	 */
	public Clef(final byte[] value) {
		this.value = value.clone();
	}
	
	/**
	 * Instantiates a new Clef.
	 */
	public Clef() {
		this.value = new byte[Util.TAILLE_CLEF];
	}
	
	public boolean invariant() {
		return this.value != null && this.value.length == Util.TAILLE_CLEF;
	}
	
	/**
	 * Get clef byte [ ].
	 *
	 * @return the byte [ ]
	 */
	public byte[] getValue() {
		return this.value.clone();
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		
		return Arrays.equals(this.value, ((Clef) o).value);
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(this.value);
	}
	
	@Override
	public String toString() {
		return Util.clefToString(this.value);
	}
}
