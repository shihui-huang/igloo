package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreDejaPresente;


public class TestCreerChambre {
	private GestionClefsHotel systeme;
	
	@Before
	public void setUp() {
		this.systeme = new GestionClefsHotel();
	}
	
	@After
	public void tearDown() {
		this.systeme = null;
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerChambreTest1Jeu1() throws Exception {
		this.systeme.creerChambre(123456789, null, 0);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerChambreTest1Jeu2() throws Exception {
		this.systeme.creerChambre(123456789, "", 0);
	}
	
	@Test(expected = ChambreDejaPresente.class)
	public void creerChambreTest3Puis2() throws Exception {
		try {
			this.systeme.creerChambre(123456789, "graine1", 0);
		}
		catch (ChambreDejaPresente e) {
			Assert.fail();
		}
		
		this.systeme.creerChambre(123456789, "graine1", 0);
	}
}
