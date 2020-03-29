package eu.telecomsudparis.csc4102.gestionclefshotel.validation;
import eu.telecomsudparis.csc4102.gestionclefshotel.*;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.*;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestDeclarerPerduDuBadge {
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
    @Test(expected = BadgeInexistant.class)
    public void declarerPerduDuBadgeTest1Jeu1() throws Exception {
        this.systeme.declarerPerduDuBadge(20);
    }

    @Test(expected = BadgeInexistant.class)
    public void declarerPerduDuBadgeTest2Jeu1() throws Exception  {
        this.systeme.declarerPerduDuBadge(22);
        systeme.enregistrerOccupationChambre(11,22,33);//apres declaration,le badge n'exist plus.Donc,il va y avoir l'erreur
    }

    @Test(expected = ChambreNonOccupee.class)
    public void declarerPerduDuBadgeTest2Jeu2() throws Exception {
        this.systeme.enregistrerOccupationChambre(11, 22, 33);
        this.systeme.enregistrerOccupationChambre(12, 23, 34);
        this.systeme.declarerPerduDuBadge(22);
        this.systeme.libererChambre(11, 23, 34);//apres declaration,le chambre n'est plus occupee.
    }

    @Test(expected = ClientOccupeAucuneChambre.class)
    public void declarerPerduDuBadgeTest2Jeu3() throws Exception {

        this.systeme.enregistrerOccupationChambre(11, 22, 33);
        this.systeme.enregistrerOccupationChambre(12, 23, 34);
        this.systeme.declarerPerduDuBadge(22);
        this.systeme.libererChambre(12, 23, 33);//apres declaration,le client n'a plus chambre.Donc,il va y avoir pas l'erreur

    }
}
