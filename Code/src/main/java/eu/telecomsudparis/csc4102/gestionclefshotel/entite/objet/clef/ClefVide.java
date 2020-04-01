package eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.clef;


public final class ClefVide extends Clef {
	private static final ClefVide INSTANCE = new ClefVide();
	
	private ClefVide() {
		this.value = null;
	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static ClefVide getInstance() {
		return ClefVide.INSTANCE;
	}
	
	@Override
	public final boolean invariant() {
		return this.value == null;
	}
}
