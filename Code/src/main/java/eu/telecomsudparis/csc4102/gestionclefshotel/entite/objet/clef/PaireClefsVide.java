package eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.clef;


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
		return this.clef1 != null
					&& this.clef1 instanceof ClefVide
					&& this.clef1 == ClefVide.getInstance()
					&& this.clef1.invariant()
				&& this.clef2 != null
					&& this.clef2 instanceof ClefVide
					&& this.clef2 == ClefVide.getInstance()
					&& this.clef2.invariant();
	}
}
