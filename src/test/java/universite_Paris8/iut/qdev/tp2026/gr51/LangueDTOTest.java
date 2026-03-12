package universite_Paris8.iut.qdev.tp2026.gr51;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests LangueDTO")
class LangueDTOTest {

    // ── Constructeur ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Le constructeur plein initialise correctement tous les champs")
    void constructeurPlein_initialiseChamps() {
        LangueDTO l = new LangueDTO(1, "fr", "Français");
        assertEquals(1, l.getId());
        assertEquals("fr", l.getCode());
        assertEquals("Français", l.getNom());
    }

    @Test
    @DisplayName("Le constructeur vide crée un objet non null")
    void constructeurVide_objetNonNull() {
        assertNotNull(new LangueDTO());
    }

    // ── Codes valides ────────────────────────────────────────────────────────

    @ParameterizedTest(name = "code={0}, nom={1}")
    @CsvSource({
        "fr, Français",
        "en, English",
        "de, Deutch",
        "es, Española",
        "it, Italiana"
    })
    @DisplayName("Chaque code de langue autorisé est accepté")
    void codesLangues_tousAcceptes(String code, String nom) {
        LangueDTO l = new LangueDTO(1, code, nom);
        assertEquals(code, l.getCode());
        assertEquals(nom, l.getNom());
    }

    // ── Setters ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Les setters modifient correctement les valeurs")
    void setters_modifientValeurs() {
        LangueDTO l = new LangueDTO();
        l.setId(5);
        l.setCode("en");
        l.setNom("English");
        assertEquals(5, l.getId());
        assertEquals("en", l.getCode());
        assertEquals("English", l.getNom());
    }

    // ── toString ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("toString contient le code et le nom")
    void toString_contientInfosCles() {
        LangueDTO l = new LangueDTO(2, "en", "English");
        String s = l.toString();
        assertTrue(s.contains("en"));
        assertTrue(s.contains("English"));
    }
}
