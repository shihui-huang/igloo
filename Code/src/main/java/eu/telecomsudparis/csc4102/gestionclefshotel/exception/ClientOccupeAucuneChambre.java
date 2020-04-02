package eu.telecomsudparis.csc4102.gestionclefshotel.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * The type Client occupe aucune chambre.
 */
public class ClientOccupeAucuneChambre extends OperationImpossible {
    /**
     * numéro de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * construit une instance.
     *
     * @param message le message de l'exception.
     */
    public ClientOccupeAucuneChambre(final String message) {
        super(message);
    }
}
