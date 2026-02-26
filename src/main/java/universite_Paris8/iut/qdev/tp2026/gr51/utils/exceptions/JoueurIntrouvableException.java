package universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions;

/**
 * Levée lorsqu'aucun joueur ne correspond au critère de recherche.
 */
public class JoueurIntrouvableException extends Exception {

    public JoueurIntrouvableException(String pseudo) {
        super("Aucun joueur trouvé avec le pseudo '" + pseudo + "'.");
    }

    public JoueurIntrouvableException() {
        super("Aucun joueur trouvé.");
    }
}
