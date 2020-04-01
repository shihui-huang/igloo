package eu.telecomsudparis.csc4102.gestionclefshotel;


public final class ClefVide extends Clef {
	public ClefVide() {
		this.value = null;
	}
	
	@Override
	public final byte[] getValue() {
		return this.value;
	}
}
