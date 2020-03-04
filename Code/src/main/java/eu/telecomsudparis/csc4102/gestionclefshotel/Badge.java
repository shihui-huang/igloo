package eu.telecomsudparis.csc4102.gestionclefshotel;


public class Badge {					// TODO Documentation.
	private final long id;
	private PaireClefs paireClefs;
	private Chambre chambre;
	private Client client;
	
	public Badge(final long id) {
		this.id = id;
	}
	
	public boolean invariant() {
		return false;					// TODO invariant.
	}
	
	public long getId() {
		return this.id;
	}
	
	public PaireClefs getClefs() {
		return this.paireClefs;
	}
	
	public void inscrireClefs(final PaireClefs paireClefs) {
		this.paireClefs = paireClefs;
	}
	
	public void vider() {
		if (this.chambre != null) {
			this.chambre.dissocierBadge();
		}
		
		this.paireClefs = null;
	}
	
	public Chambre getChambre() {
		return this.chambre;
	}
	
	public void associerChambre(final Chambre chambre) {
		this.associerChambre(chambre, false);
	}
	
	public void associerChambre(final Chambre chambre, boolean bidirectionnel) {
		this.chambre = chambre;
		
		if (bidirectionnel) {
			this.chambre.associerBadge(this, false);
		}
	}
	
	public void dissocierChambre() {
		this.dissocierChambre(false);
	}
	
	public void dissocierChambre(boolean bidirectionnel) {
		if (bidirectionnel && this.chambre != null) {
			this.chambre.dissocierBadge(false);
		}
		
		this.chambre = null;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public void associerClient(final Client client) {
		this.associerClient(client, false);
	}
	
	public void associerClient(final Client client, boolean bidirectionnel) {
		this.client = client;
		
		if (bidirectionnel) {
			this.client.associerBadge(this, false);
		}
	}
	
	public void dissocierClient() {
		this.dissocierClient(false);
	}
	
	public void dissocierClient(boolean bidirectionnel) {
		if (bidirectionnel && this.client != null) {
			this.client.dissocierBadge(false);
		}
		
		this.client = null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.id ^ this.id >>> 32);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Badge)) {
			return false;
		}
		
		final Badge other = (Badge) obj;
		
		if (this.id != other.id) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return String.format("Badge [id = %s, paireClefs = %s, chambre = %s, client = %s]",
							this.id, this.paireClefs, this.chambre, this.client);
	}
}
