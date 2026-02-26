package universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions;

/**
 * Levée lorsqu'une adresse email ne contient pas de '@' ou est nulle/vide.
 */
public class EmailInvalideException extends Exception {

    public EmailInvalideException(String email) {
        super("L'adresse email '" + email + "' est invalide (doit contenir un '@').");
    }

    public EmailInvalideException() {
        super("L'adresse email est obligatoire et doit contenir un '@'.");
    }
}
