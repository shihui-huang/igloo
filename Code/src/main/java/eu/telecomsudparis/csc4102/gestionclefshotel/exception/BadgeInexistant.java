package eu.telecomsudparis.csc4102.gestionclefshotel.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;


/**
 * Cette classe définit le type d'exception pour un badge d'accès non trouvée.
 */
public class BadgeInexistant extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * construit une instance.
	 *
	 * @param message le message de l'exception.
	 */
	public BadgeInexistant(final String message) {
		super(message);
	}
}
