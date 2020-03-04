package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;


public class Chambre {					// TODO Documentation.
	private final long id;
	private final String graine;
	private int sel;
	private boolean occupee;
	private Badge badge;
	private PaireClefs paireClefs;
	
	public Chambre(final long id, final String graine, final int sel) throws ProblemeDansGenerationClef {
		this.id = id;
		this.graine = graine;
		this.sel = sel;
		this.occupee = false;
		
		final byte[] clef1 = Util.genererUneNouvelleClef(graine, Integer.toString(sel));
		this.sel++;
		final byte[] clef2 = Util.genererUneNouvelleClef(graine, Integer.toString(sel));
		this.sel++;
		this.paireClefs = new PaireClefs(clef1, clef2);
	}
	
	public boolean invariant() {
		return false;					// TODO invariant.
	}
	
	public long getId() {
		return this.id;
	}
	
	public Badge getBadge() {
		return this.badge;
	}
	
	public void associerBadge(final Badge badge) {
		this.associerBadge(badge, false);
	}
	
	public void associerBadge(final Badge badge, boolean symetrique) {
		this.badge = badge;
		
		if (symetrique) {
			this.badge.associerChambre(this, false);
		}
	}
	
	public void dissocierBadge() {
		this.dissocierBadge(false);
	}
	
	public void dissocierBadge(boolean symetrique) {
		if (symetrique && this.badge != null) {
			this.badge.dissocierClient(false);
		}
		
		this.badge = null;
	}
	
	public String getGraine() {
		return this.graine;
	}
	
	public int getSel() {
		return this.sel;
	}
	
	public boolean estOccupee() {
		return this.occupee;
	}
	
	public PaireClefs getClefs() {
		return this.paireClefs;
	}
	
	public PaireClefs obtenirNouvellePaireClefs() throws ProblemeDansGenerationClef {
		this.paireClefs = new PaireClefs(this.paireClefs.getClef1(),
										Util.genererUneNouvelleClef(this.graine,
																	String.format("%010d%n", this.sel)));
		this.sel++;
		return this.paireClefs;
	}
	
	public void inscrireClefs(final PaireClefs paireClefs) {
		this.paireClefs = paireClefs;
	}
	
	public void liberer() {
		this.occupee = false;
		this.badge.dissocierClient(true);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.graine == null ? 0 : this.graine.hashCode());
		result = prime * result + (int) (this.id ^ this.id >>> 32);
		result = prime * result + this.sel;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Chambre)) {
			return false;
		}
		
		final Chambre other = (Chambre) obj;
		
		if (this.graine == null) {
			if (other.graine != null) {
				return false;
			}
		}
		else if (!this.graine.equals(other.graine)) {
			return false;
		}
		
		if (this.id != other.id) {
			return false;
		}
		
		if (this.sel != other.sel) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("Chambre [id = %s, graine = %s, sel = %s, occupee = %s, paireClefs = %s]",
							this.id, this.graine, this.sel, this.occupee, this.paireClefs);
	}
}
