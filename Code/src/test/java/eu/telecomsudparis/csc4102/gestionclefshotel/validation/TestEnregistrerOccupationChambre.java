package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeDejaAssocieChambreOuClient;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeInexistant;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreDejaOccupee;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreInexistante;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientInexistant;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientOccupeDejaChambre;


public class TestEnregistrerOccupationChambre {
	private GestionClefsHotel systeme;
	
	@Before
	public void setUp() {
		this.systeme = new GestionClefsHotel();
		
		try {
			this.systeme.creerChambre(11, "graine1", 0);
			this.systeme.creerChambre(12, "graine2", 0);
			this.systeme.creerBadge(22);
			this.systeme.creerBadge(23);
			this.systeme.creerClient(33, "Huang", "Shihui");
			this.systeme.creerClient(34, "Mabileau", "Paul");
		}
		catch (Exception e) {
			Assert.fail();
		}
	}
	
	@After
	public void tearDown() {
		this.systeme = null;
	}
	
	@Test(expected = ChambreInexistante.class)
	public void enregistrerOccupationChambreTest1Jeu1() throws Exception {
		this.systeme.enregistrerOccupationChambre(00, 22, 33);
	}
	
	@Test(expected = BadgeInexistant.class)
	public void enregistrerOccupationChambreTest2Jeu1() throws Exception {
		this.systeme.enregistrerOccupationChambre(11, 00, 33);
	}
	
	@Test(expected = ClientInexistant.class)
	public void enregistrerOccupationChambreTest3Jeu1() throws Exception {
		this.systeme.enregistrerOccupationChambre(11, 22, 00);
	}
	
	@Test(expected = ChambreDejaOccupee.class)
	public void enregistrerOccupationChambreTest4Jeu1() throws Exception {
		this.systeme.enregistrerOccupationChambre(11, 22, 33);
		this.systeme.enregistrerOccupationChambre(11, 23, 34);
	}
	
	@Test(expected = ClientOccupeDejaChambre.class)
	public void enregistrerOccupationChambreTest5Jeu1() throws Exception {
		this.systeme.enregistrerOccupationChambre(11, 22, 33);
		this.systeme.enregistrerOccupationChambre(12, 23, 33);
	}
	
	@Test(expected = BadgeDejaAssocieChambreOuClient.class)
	public void enregistrerOccupationChambreTest6Jeu1() throws Exception {
		this.systeme.enregistrerOccupationChambre(11, 22, 33);
		this.systeme.enregistrerOccupationChambre(12, 22, 34);
	}
	
	// TODO tester les paireClefs dans badge sont null
	
	@Test
	public void enregistrerOccupationChambreTest8() throws Exception {
		Chambre chambre1 = this.systeme.chercherChambre(11);
		Badge Badge1 = this.systeme.chercherBadge(22);
		Client client1 = this.systeme.chercherClient(33);
		
		Assert.assertFalse(chambre1.estOccupee());
		Assert.assertNull(Badge1.getClefs());
		Assert.assertNull(Badge1.getClient());
		Assert.assertNull(Badge1.getChambre());
		Assert.assertNull(chambre1.getBadge());
		Assert.assertNull(client1.getBadge());
		this.systeme.enregistrerOccupationChambre(11, 22, 33);
		Assert.assertTrue(chambre1.estOccupee());
		Assert.assertNotNull(Badge1.getClefs());
		Assert.assertNotNull(Badge1.getClient());
		Assert.assertNotNull(Badge1.getChambre());
		Assert.assertEquals(chambre1.getBadge(), Badge1);
		Assert.assertEquals(client1.getBadge(), Badge1);
	}
}
