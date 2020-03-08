package eu.telecomsudparis.csc4102.gestionclefshotel.exception;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
/**
 * Cette classe définit le type d'exception pour un badge déjà présente dans
 * le système.
 */
public class BadgeDejaPresent extends OperationImpossible {

    /**
     * numéro de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * construit une instance.
     *
     * @param message le message de l'exception.
     */
    public BadgeDejaPresent(final String message) {
        super(message);
    }
}