package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Arrays;


public class PaireClefs {				// TODO Documentation.
	private final byte[] clef1;
	private final byte[] clef2;
	
	public PaireClefs(final byte[] clef1, final byte[] clef2) {
		this.clef1 = clef1;
		this.clef2 = clef2;
	}
	
	public boolean invariant() {
		return false;					// TODO
	}
	
	public byte[] getClef1() {
		return this.clef1.clone();
	}
	
	public byte[] getClef2() {
		return this.clef2.clone();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.clef1);
		result = prime * result + Arrays.hashCode(this.clef2);
		return result;
	}
	
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
	
	@Override
	public String toString() {
		return String.format("PaireClefs [clef1 = %s, clef2 = %s]",
							Arrays.toString(this.clef1), Arrays.toString(this.clef2));
	}
}
