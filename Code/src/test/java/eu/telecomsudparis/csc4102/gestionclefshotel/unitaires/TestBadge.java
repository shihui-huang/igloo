package eu.telecomsudparis.csc4102.gestionclefshotel.unitaires;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.Client;


public class TestBadge {
	private Badge badge;
	
	@Before
	public void setUp() {
		this.badge = new Badge(42);
	}
	
	@After
	public void tearDown() {
		this.badge = null;
	}
	
	@Test
	public void testConstructeur() {
		Assert.assertNotNull(this.badge);
		Assert.assertNull(this.badge.getClefs());
		Assert.assertNull(this.badge.getClient());
		Assert.assertNull(this.badge.getChambre());
	}

//	@Test
//	public void testAssociationClient(){
//		final Client client = new Client(43, "Zidane", "Zinedine");
//		this.badge.associerClient(client);
//		Assert.assertEquals(client, this.badge.getClient());
//		Assert.assertNull(client.getBadge());
//	}

	@Test
	public void testAssociationClientBidirectionnelle() {
		final Client client = new Client(44, "d'Artois", "Robert");
		this.badge.associerClient(client, true);
		Assert.assertEquals(client, this.badge.getClient());
		Assert.assertEquals(this.badge, client.getBadge());
	}


}
