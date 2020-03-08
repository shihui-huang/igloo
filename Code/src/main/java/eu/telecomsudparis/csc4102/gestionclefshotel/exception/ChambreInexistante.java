package eu.telecomsudparis.csc4102.gestionclefshotel.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour une chambre non trouvée.
 */
public class ChambreInexistante extends OperationImpossible {

    /**
     * numéro de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * construit une instance.
     *
     * @param message le message de l'exception.
     */
    public ChambreInexistante(final String message) {
        super(message);
    }
}