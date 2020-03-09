package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeInexistant;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreInexistante;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreNonOccupee;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientInexistant;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientOccupeAutreChambre;


public class TestLibererChambre {
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
	public void libererChambreTest1Jeu1() throws Exception {
		this.systeme.libererChambre(00, 22, 33);
	}
	
	@Test(expected = BadgeInexistant.class)
	public void libererChambreTest2Jeu1() throws Exception {
		this.systeme.libererChambre(11, 00, 33);
	}
	
	@Test(expected = ClientInexistant.class)
	public void libererChambreTest3Jeu1() throws Exception {
		this.systeme.libererChambre(11, 22, 00);
	}
	
	// TODO tester si client occupe aucune chambre
	
	@Test(expected = ClientOccupeAutreChambre.class)
	public void libererChambreTest5Jeu1() throws Exception {
		this.systeme.enregistrerOccupationChambre(11, 22, 33);
		this.systeme.enregistrerOccupationChambre(12, 23, 34);
		this.systeme.libererChambre(12, 23, 33);
	}
	
	@Test(expected = ChambreNonOccupee.class)
	public void libererChambreTest6Jeu1() throws Exception {
		this.systeme.enregistrerOccupationChambre(11, 22, 33);
		this.systeme.libererChambre(12, 22, 33);
	}
	
	@Test
	public void libererChambreTest7() throws Exception {
		this.systeme.creerChambre(13, "graine2", 0);
		this.systeme.creerBadge(24);
		this.systeme.creerClient(35, "Huang", "shihui");
		
		Chambre chambre1 = this.systeme.chercherChambre(13);
		Badge Badge1 = this.systeme.chercherBadge(24);
		Client client1 = this.systeme.chercherClient(35);
		
		this.systeme.enregistrerOccupationChambre(13, 24, 35);
		
		Assert.assertTrue(chambre1.estOccupee());
		Assert.assertNotNull(Badge1.getClefs());
		Assert.assertNotNull(Badge1.getClient());
		Assert.assertNotNull(Badge1.getChambre());
		Assert.assertNotNull(chambre1.getBadge());
		Assert.assertNotNull(client1.getBadge());
		Assert.assertEquals(chambre1.getBadge(), Badge1);
		Assert.assertEquals(client1.getBadge(), Badge1);
		this.systeme.libererChambre(13, 24, 35);
		Assert.assertFalse(chambre1.estOccupee());
		Assert.assertNull(Badge1.getClefs());
		Assert.assertNull(Badge1.getClient());
		Assert.assertNull(Badge1.getChambre());
		Assert.assertNull(chambre1.getBadge());
		Assert.assertNull(client1.getBadge());
	}
}
