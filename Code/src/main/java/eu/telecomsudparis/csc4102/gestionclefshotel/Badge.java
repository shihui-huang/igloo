package eu.telecomsudparis.csc4102.gestionclefshotel;


public class Badge {
	private final long id;
	private PaireClefs paireClefs;
	private Chambre chambre;
	private Client client;
	
	public Badge(final long id) {
		this.id = id;
	}
	
	public boolean invariant() {
		return false;					// TODO
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
		this.paireClefs = null;
	}
	
	public Chambre getChambre() {
		return this.chambre;
	}
	
	public void associerChambre(final Chambre chambre) {
		this.chambre = chambre;
		this.chambre.setBadge(this);
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public void associerClient(final Client client) {
		this.client = client;
	}
	
	public void dissocierClient() {
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
