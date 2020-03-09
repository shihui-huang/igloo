package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.*;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestLibererChambre {


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
    public void libererChambreTest1Jeu1() throws Exception {

        systeme.libererChambre(00,22,33);
    }

    @Test(expected = BadgeInexistant.class)
    public void libererChambreTest2Jeu1() throws Exception {
        systeme.libererChambre(11,00,33);
    }

    @Test(expected = ClientInexistant.class)
    public void libererChambreTest3Jeu1() throws Exception {
        systeme.libererChambre(11,22,00);
    }

// Todo tester si client occupe aucune chambre

    @Test(expected = ClientOccupeePasBonneChambre.class)
    public void libererChambreTest5Jeu1() throws Exception {
        systeme.enregistrerOccupationChambre(11,22,33);
        systeme.enregistrerOccupationChambre(12,23,34);
        systeme.libererChambre(12,23,33);
    }

    @Test(expected = ChambreNonOccupee.class)
    public void libererChambreTest6Jeu1() throws Exception {
        systeme.enregistrerOccupationChambre(11,22,33);
        systeme.libererChambre(12,22,33);
    }


    @Test
    public void libererChambreTest7() throws Exception {
        systeme.creerChambre(13,"graine2",0);
        systeme.creerBadge(24);
        systeme.creerClient(35,"Huang","shihui");

        Chambre chambre1 = systeme.chercherChambre(13);
        Badge Badge1 = systeme.chercherBadge(24);
        Client client1 = systeme.chercherClient(35);

        systeme.enregistrerOccupationChambre(13,24,35);

        Assert.assertTrue(chambre1.estOccupee());
        Assert.assertNotNull(Badge1. getClefs());
        Assert.assertNotNull(Badge1. getClient());
        Assert.assertNotNull(Badge1. getChambre());
        Assert.assertNotNull(chambre1.getBadge());
        Assert.assertNotNull(client1.getBadge());
        Assert.assertEquals(chambre1.getBadge(), Badge1);
        Assert.assertEquals(client1.getBadge(), Badge1);
        systeme. libererChambre(13, 24, 35);
        Assert.assertFalse(chambre1.estOccupee());
        Assert.assertNull(Badge1. getClefs());
        Assert.assertNull(Badge1. getClient());
        Assert.assertNull(Badge1. getChambre());
        Assert.assertNull(chambre1.getBadge());
        Assert.assertNull(client1.getBadge());

    }
}