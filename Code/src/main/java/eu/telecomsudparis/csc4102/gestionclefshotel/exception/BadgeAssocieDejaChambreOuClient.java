package eu.telecomsudparis.csc4102.gestionclefshotel.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour un badge a associé déjà une chambre .
 */
public class BadgeAssocieDejaChambreOuClient extends OperationImpossible {
    /**
     * numéro de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * construit une instance.
     *
     * @param message le message de l'exception.
     */
    public BadgeAssocieDejaChambreOuClient(final String message) {
        super(message);
    }
}
