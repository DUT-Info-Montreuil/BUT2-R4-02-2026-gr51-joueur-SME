package universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions;

/**
 * Levée lorsqu'un joueur avec le même pseudo existe déjà.
 */
public class PseudoDejaUtiliseException extends Exception {

    public PseudoDejaUtiliseException(String pseudo) {
        super("Le pseudo '" + pseudo + "' est déjà utilisé.");
    }
}
