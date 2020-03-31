package eu.telecomsudparis.csc4102.gestionclefshotel;


public final class PaireClefsVide extends PaireClefs {
	public PaireClefsVide() {
		super(new ClefVide(), new ClefVide());
	}
	
	@Override
	public final boolean invariant() {
		return this.clef1 != null && this.clef1 instanceof ClefVide
				&& this.clef2 != null && this.clef2 instanceof ClefVide;
	}
}
