package eu.telecomsudparis.csc4102.gestionclefshotel.exception;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
/**
 * Cette classe définit le type d'exception pour un client existe déjà dans le système.
 */
public class ChambreNonOccupee extends OperationImpossible{/**
 * numéro de version pour la sérialisation.
 */
private static final long serialVersionUID = 1L;

    /**
     * construit une instance.
     *
     * @param message le message de l'exception.
     */
    public ChambreNonOccupee(final String message) {
        super(message);
    }
}
