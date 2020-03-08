package eu.telecomsudparis.csc4102.gestionclefshotel.exception;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour une chambre occupée dans
 * le système.
 */
public class ChambreOccupee extends OperationImpossible{
    /**
     * numéro de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * construit une instance.
     *
     * @param message le message de l'exception.
     */
    public ChambreOccupee(final String message) {
        super(message);
    }
}
