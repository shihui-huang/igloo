package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.*;
import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestEnregistrerOccupationChambre {

    private GestionClefsHotel systeme;

    @Before
    public void setUp() {
        systeme = new GestionClefsHotel();
        try {
            systeme.creerChambre(11, "graine1", 0);
            systeme.creerChambre(12, "graine2", 0);
            systeme.creerBadge(22);
            systeme.creerBadge(23);
            systeme.creerClient(33, "Huang", "Shihui");
            systeme.creerClient(34, "Mabileau", "Paul");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @After
    public void tearDown() {
        systeme = null;
    }

    
    @Test(expected = ChambreInexistante.class)
    public void enregistrerOccupationChambreTest1Jeu1() throws Exception {
        systeme. enregistrerOccupationChambre(00, 22, 33);
    }

    @Test(expected = BadgeInexistant.class)
    public void enregistrerOccupationChambreTest1Jeu2() throws Exception {
        systeme. enregistrerOccupationChambre(11, 00, 33);
    }


    @Test(expected = ClientInexistant.class)
    public void enregistrerOccupationChambreTest1Jeu3() throws Exception {
        systeme. enregistrerOccupationChambre(11, 22, 00);
    }


	@Test(expected = ChambreOccupee.class)
	public void  enregistrerOccupationChambreTest2Jeu1() throws Exception {
		systeme. enregistrerOccupationChambre(11, 22, 33);
        systeme. enregistrerOccupationChambre(11, 23, 34);
	}


    @Test(expected = ClientOccupeeDejaChambre.class)
    public void  enregistrerOccupationChambreTest3Jeu1() throws Exception {
        systeme. enregistrerOccupationChambre(11, 22, 33);
        systeme. enregistrerOccupationChambre(12, 23, 33);
    }



    @Test(expected = BadgeAssocieDejaChambreOuClient.class)
    public void  enregistrerOccupationChambreTest4Jeu1() throws Exception {
        systeme. enregistrerOccupationChambre(11, 22, 33);
        systeme. enregistrerOccupationChambre(12, 22, 34);
    }

    //Todo tester les paireClefs dans badge sont null

    @Test
    public void  enregistrerOccupationChambreTest6() throws Exception {
        Chambre chambre1 = systeme.chercherChambre(11);
        Badge Badge1 = systeme.chercherBadge(22);

        System.out.println(chambre1);
        Client client1 = systeme.chercherClient(33);
        Assert.assertFalse(chambre1.estOccupee());
        Assert.assertNull(Badge1. getClefs());
        Assert.assertNull(Badge1. getClient());
        Assert.assertNull(Badge1. getChambre());
        Assert.assertNull(chambre1.getBadge());
        Assert.assertNull(client1.getBadge());
        systeme. enregistrerOccupationChambre(11, 22, 33);
        Assert.assertTrue(chambre1.estOccupee());
        Assert.assertNotNull(Badge1. getClefs());
        Assert.assertNotNull(Badge1. getClient());
        Assert.assertNotNull(Badge1. getChambre());
        Assert.assertEquals(chambre1.getBadge(), Badge1);
        Assert.assertEquals(client1.getBadge(), Badge1);
    }


}