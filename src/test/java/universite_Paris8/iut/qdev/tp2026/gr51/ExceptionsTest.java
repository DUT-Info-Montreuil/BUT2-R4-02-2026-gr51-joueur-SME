package universite_Paris8.iut.qdev.tp2026.gr51;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.AnneeNaissanceInvalideException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.EmailInvalideException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.JoueurIntrouvableException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.PseudoDejaUtiliseException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests des exceptions métier")
class ExceptionsTest {

    // ── PseudoDejaUtiliseException ────────────────────────────────────────────

    @Test
    @DisplayName("PseudoDejaUtiliseException contient le pseudo dans son message")
    void pseudoDejaUtilise_messageContientPseudo() {
        PseudoDejaUtiliseException ex = new PseudoDejaUtiliseException("Naosh1");
        assertTrue(ex.getMessage().contains("Naosh1"));
    }

    @Test
    @DisplayName("PseudoDejaUtiliseException est une Exception")
    void pseudoDejaUtilise_estUneException() {
        assertInstanceOf(Exception.class, new PseudoDejaUtiliseException("test"));
    }

    @Test
    @DisplayName("PseudoDejaUtiliseException peut être lancée et attrapée")
    void pseudoDejaUtilise_peutEtreLancee() {
        assertThrows(PseudoDejaUtiliseException.class, () -> {
            throw new PseudoDejaUtiliseException("Ronin0205");
        });
    }

    // ── JoueurIntrouvableException ────────────────────────────────────────────

    @Test
    @DisplayName("JoueurIntrouvableException(pseudo) contient le pseudo dans son message")
    void joueurIntrouvable_avecPseudo_messageContientPseudo() {
        JoueurIntrouvableException ex = new JoueurIntrouvableException("Naosh1");
        assertTrue(ex.getMessage().contains("Naosh1"));
    }

    @Test
    @DisplayName("JoueurIntrouvableException() sans argument a un message non null")
    void joueurIntrouvable_sansArgument_messageNonNull() {
        JoueurIntrouvableException ex = new JoueurIntrouvableException();
        assertNotNull(ex.getMessage());
        assertFalse(ex.getMessage().isBlank());
    }

    @Test
    @DisplayName("JoueurIntrouvableException est une Exception")
    void joueurIntrouvable_estUneException() {
        assertInstanceOf(Exception.class, new JoueurIntrouvableException());
    }

    // ── EmailInvalideException ────────────────────────────────────────────────

    @Test
    @DisplayName("EmailInvalideException(email) contient l'email dans son message")
    void emailInvalide_avecEmail_messageContientEmail() {
        EmailInvalideException ex = new EmailInvalideException("pas-un-email");
        assertTrue(ex.getMessage().contains("pas-un-email"));
    }

    @Test
    @DisplayName("EmailInvalideException() sans argument a un message non null")
    void emailInvalide_sansArgument_messageNonNull() {
        EmailInvalideException ex = new EmailInvalideException();
        assertNotNull(ex.getMessage());
        assertFalse(ex.getMessage().isBlank());
    }

    @Test
    @DisplayName("EmailInvalideException peut être lancée et attrapée")
    void emailInvalide_peutEtreLancee() {
        assertThrows(EmailInvalideException.class, () -> {
            throw new EmailInvalideException("mauvais");
        });
    }

    // ── AnneeNaissanceInvalideException ───────────────────────────────────────

    @Test
    @DisplayName("AnneeNaissanceInvalideException contient l'année et la plage dans son message")
    void anneeNaissanceInvalide_messageContientDetails() {
        int anneeActuelle = LocalDate.now().getYear();
        int min = anneeActuelle - 100;
        int max = anneeActuelle - 7;
        AnneeNaissanceInvalideException ex = new AnneeNaissanceInvalideException(1900, min, max);

        assertTrue(ex.getMessage().contains("1900"));
        assertTrue(ex.getMessage().contains(String.valueOf(min)));
        assertTrue(ex.getMessage().contains(String.valueOf(max)));
    }

    @Test
    @DisplayName("AnneeNaissanceInvalideException est une Exception")
    void anneeNaissanceInvalide_estUneException() {
        assertInstanceOf(Exception.class,
                new AnneeNaissanceInvalideException(2030, 1924, 2019));
    }

    @Test
    @DisplayName("AnneeNaissanceInvalideException peut être lancée et attrapée")
    void anneeNaissanceInvalide_peutEtreLancee() {
        assertThrows(AnneeNaissanceInvalideException.class, () -> {
            throw new AnneeNaissanceInvalideException(2030, 1924, 2019);
        });
    }
}
