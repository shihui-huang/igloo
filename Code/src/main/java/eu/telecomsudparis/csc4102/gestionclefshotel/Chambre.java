package eu.telecomsudparis.csc4102.gestionclefshotel;


public class Chambre {
	private final long id;
	private final String graine;
	private final int sel;
	private boolean occupee;
	private Badge badge;
	private PaireClefs paireClefs;
	
	public Chambre(final long id, final String graine, final int sel) {
		this.id = id;
		this.graine = graine;
		this.sel = sel;
		this.occupee = false;
	}
	
	public boolean invariant() {
		return false;					// TODO
	}
	
	public long getId() {
		return this.id;
	}
	
	public Badge getBadge() {
		return this.badge;
	}
	
	public void setBadge(final Badge badge) {
		this.badge = badge;
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
	
	public void inscrireClefs(final PaireClefs paireClefs) {
		this.paireClefs = paireClefs;
	}
	
	public void liberer() {
		this.occupee = false;
		this.badge.dissocierClient();
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
