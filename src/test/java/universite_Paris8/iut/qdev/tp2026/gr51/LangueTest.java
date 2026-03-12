package universite_Paris8.iut.qdev.tp2026.gr51;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.enums.Langue;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests enum Langue")
class LangueTest {

    // ── Valeurs de l'enum ────────────────────────────────────────────────────

    @Test
    @DisplayName("L'enum contient exactement 5 langues")
    void enum_contientCinqLangues() {
        assertEquals(5, Langue.values().length);
    }

    @ParameterizedTest(name = "Langue.{0} existe")
    @ValueSource(strings = {"FR", "EN", "DE", "ES", "IT"})
    @DisplayName("Toutes les constantes attendues sont présentes")
    void constantes_toutesPresentes(String nom) {
        assertDoesNotThrow(() -> Langue.valueOf(nom));
    }

    // ── Codes et noms ────────────────────────────────────────────────────────

    @ParameterizedTest(name = "code={0} → nom={1}")
    @CsvSource({
        "fr, Français",
        "en, English",
        "de, Deutch",
        "es, Española",
        "it, Italiana"
    })
    @DisplayName("Chaque langue a le bon code et le bon nom")
    void langues_codesEtNoms(String code, String nom) {
        Langue l = Langue.fromCode(code);
        assertEquals(code, l.getCode());
        assertEquals(nom, l.getNom());
    }

    // ── fromCode ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("fromCode est insensible à la casse")
    void fromCode_insensibleCasse() {
        assertEquals(Langue.FR, Langue.fromCode("FR"));
        assertEquals(Langue.FR, Langue.fromCode("fr"));
        assertEquals(Langue.EN, Langue.fromCode("EN"));
    }

    @Test
    @DisplayName("fromCode lève IllegalArgumentException pour un code inconnu")
    void fromCode_codeInconnu_leveException() {
        assertThrows(IllegalArgumentException.class, () -> Langue.fromCode("xx"));
    }

    @Test
    @DisplayName("fromCode lève IllegalArgumentException pour une chaîne vide")
    void fromCode_chaineVide_leveException() {
        assertThrows(IllegalArgumentException.class, () -> Langue.fromCode(""));
    }

    // ── Cas nominaux par constante ────────────────────────────────────────────

    @Test
    @DisplayName("FR a le code 'fr' et le nom 'Français'")
    void fr_codeEtNom() {
        assertEquals("fr", Langue.FR.getCode());
        assertEquals("Français", Langue.FR.getNom());
    }

    @Test
    @DisplayName("EN a le code 'en' et le nom 'English'")
    void en_codeEtNom() {
        assertEquals("en", Langue.EN.getCode());
        assertEquals("English", Langue.EN.getNom());
    }

    @Test
    @DisplayName("DE a le code 'de' et le nom 'Deutch'")
    void de_codeEtNom() {
        assertEquals("de", Langue.DE.getCode());
        assertEquals("Deutch", Langue.DE.getNom());
    }

    @Test
    @DisplayName("ES a le code 'es' et le nom 'Española'")
    void es_codeEtNom() {
        assertEquals("es", Langue.ES.getCode());
        assertEquals("Española", Langue.ES.getNom());
    }

    @Test
    @DisplayName("IT a le code 'it' et le nom 'Italiana'")
    void it_codeEtNom() {
        assertEquals("it", Langue.IT.getCode());
        assertEquals("Italiana", Langue.IT.getNom());
    }
}
