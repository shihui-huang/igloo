package eu.telecomsudparis.csc4102.gestionclefshotel;


public final class PaireClefsVide extends PaireClefs {
	private static final PaireClefsVide INSTANCE = new PaireClefsVide();
	
	private PaireClefsVide() {
		super(ClefVide.getInstance(), ClefVide.getInstance());
	}
	
	public static final PaireClefsVide getInstance() {
		return PaireClefsVide.INSTANCE;
	}
	
	@Override
	public final boolean invariant() {
		return this.clef1 != null && this.clef1 instanceof ClefVide
				&& this.clef2 != null && this.clef2 instanceof ClefVide;
	}
}
