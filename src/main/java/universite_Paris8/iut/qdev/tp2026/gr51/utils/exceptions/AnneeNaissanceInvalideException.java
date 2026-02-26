package universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions;

/**
 * Levée lorsque la date de naissance est hors de la plage autorisée.
 * Plage : [annéeCourante − 100 ; annéeCourante − 7]
 */
public class AnneeNaissanceInvalideException extends Exception {

    public AnneeNaissanceInvalideException(int annee, int min, int max) {
        super("L'année de naissance " + annee
                + " est invalide. Elle doit être comprise entre "
                + min + " et " + max + ".");
    }
}
