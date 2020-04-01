package eu.telecomsudparis.csc4102.gestionclefshotel;


public final class ClefVide extends Clef {
	private static final ClefVide INSTANCE = new ClefVide();
	
	private ClefVide() {
		this.value = null;
	}
	
	public static final ClefVide getInstance() {
		return ClefVide.INSTANCE;
	}
}
