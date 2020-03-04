package eu.telecomsudparis.csc4102.gestionclefshotel;


public class Client {					// TODO Documentation.
	private final long id;
	private final String nom;
	private final String prenom;
	private Badge badge;
	
	public Client(final long id, final String nom, final String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public boolean invariant() {
		return false;					// TODO
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
			this.badge.associerClient(this, false);
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
	
	public long getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.id ^ this.id >>> 32);
		result = prime * result + (this.nom == null ? 0 : this.nom.hashCode());
		result = prime * result + (this.prenom == null ? 0 : this.prenom.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Client)) {
			return false;
		}
		
		final Client other = (Client) obj;
		
		if (this.id != other.id) {
			return false;
		}
		
		if (this.nom == null) {
			if (other.nom != null) {
				return false;
			}
		}
		else if (!this.nom.equals(other.nom)) {
			return false;
		}
		
		if (this.prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		}
		else if (!this.prenom.equals(other.prenom)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("Client [id = %s, nom = %s, prenom = %s]",
							this.id, this.nom, this.prenom);
	}
}
