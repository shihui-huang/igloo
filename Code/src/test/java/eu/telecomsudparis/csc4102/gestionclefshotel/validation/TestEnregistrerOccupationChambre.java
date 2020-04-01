package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.*;
import org.junit.*;

import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;

import java.util.Optional;


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

	@Test(expected = ChambreNonOccupee.class)
	public void enregistrerOccupationChambreTest7Jeu1() throws Exception {
//		Optional<Chambre> chambre1 = this.systeme.chercherChambre(11);
//		Optional<Badge> Badge1 = this.systeme.chercherBadge(22);
//		Optional<Client> client1 = this.systeme.chercherClient(33);
//
//		Assert.assertFalse(chambre1.get().estOccupee());
//		Assert.assertNull(Badge1.get().getClefs());
//		Assert.assertNull(Badge1.get().getClient());
//		Assert.assertNull(Badge1.get().getChambre());
//		Assert.assertNull(chambre1.get().getBadge());
//		Assert.assertNull(client1.get().getBadge());
		this.systeme.enregistrerOccupationChambre(11, 22, 33);
		this.systeme.enregistrerOccupationChambre(11, 23, 34);

	}


	@Test(expected = BadgeDejaAssocieChambreOuClient.class)
	public void enregistrerOccupationChambreTest7Jeu2() throws Exception {

		this.systeme.enregistrerOccupationChambre(11, 22, 33);
		this.systeme.enregistrerOccupationChambre(12, 22, 34);


	}
	@Test
	public void enregistrerOccupationChambreTest7Jeu3() throws Exception {

		//Assert.assertEquals(chambre1.get().getBadge(), Badge1.get());
		//Assert.assertEquals(client1.get().getBadge(), Badge1.get());
		// On sais pas comment tester le badge dans client est le badge qui fait enregeterOccupationChambre
	}

}
