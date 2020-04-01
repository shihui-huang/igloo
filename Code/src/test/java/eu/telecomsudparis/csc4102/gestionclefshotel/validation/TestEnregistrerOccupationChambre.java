package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.*;
import org.junit.*;

import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.objet.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.personne.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.entite.salle.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeDejaAssocieChambreOuClient;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeInexistant;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreDejaOccupee;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreInexistante;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientInexistant;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientOccupeDejaChambre;

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

	@Test(expected = ChambreDejaOccupee.class)
	public void enregistrerOccupationChambreTest7Jeu1() throws Exception {
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
		// On sais pas comment tester le badge dans client est équal au badge qui fait enregeterOccupationChambre
	}

}
