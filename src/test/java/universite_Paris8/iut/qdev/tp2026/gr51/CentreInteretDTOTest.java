package universite_Paris8.iut.qdev.tp2026.gr51;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.CentreInteretDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests CentreInteretDTO")
class CentreInteretDTOTest {

    private CentreInteretDTO ciPredefini;
    private CentreInteretDTO ciPersonnalise;

    @BeforeEach
    void setUp() {
        // Centre d'intérêt prédéfini : estPersonnalise=false, certifie=true
        ciPredefini = new CentreInteretDTO(1, "football", false, true,
                LocalDate.of(2024, 1, 1), null, 0);

        // Centre d'intérêt personnalisé : estPersonnalise=true, certifie=false
        ciPersonnalise = new CentreInteretDTO(2, "kitesurf", true, false,
                LocalDate.now(), "Naosh1", 0);
    }

    // ── Constructeur ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Un CI prédéfini a estPersonnalise=false et certifie=true")
    void ciPredefini_certifieTrue_estPersonnaliseFalse() {
        assertFalse(ciPredefini.isEstPersonnalise());
        assertTrue(ciPredefini.isCertifie());
    }

    @Test
    @DisplayName("Un CI personnalisé a estPersonnalise=true et certifie=false")
    void ciPersonnalise_certifieFalse_estPersonnaliseTrue() {
        assertTrue(ciPersonnalise.isEstPersonnalise());
        assertFalse(ciPersonnalise.isCertifie());
    }

    @Test
    @DisplayName("Le constructeur vide crée un objet non null")
    void constructeurVide_objetNonNull() {
        assertNotNull(new CentreInteretDTO());
    }

    // ── Popularité ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("La popularité est initialisée à 0")
    void popularite_initialiseAZero() {
        assertEquals(0, ciPredefini.getPopularite());
        assertEquals(0, ciPersonnalise.getPopularite());
    }

    @Test
    @DisplayName("La popularité peut être incrémentée")
    void popularite_peutEtreIncrementee() {
        ciPredefini.setPopularite(ciPredefini.getPopularite() + 1);
        assertEquals(1, ciPredefini.getPopularite());
    }

    // ── Créateur ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Un CI prédéfini n'a pas de créateur (null)")
    void ciPredefini_createurEstNull() {
        assertNull(ciPredefini.getCreateurPseudo());
    }

    @Test
    @DisplayName("Un CI personnalisé a un créateur renseigné")
    void ciPersonnalise_createurRenseigne() {
        assertEquals("Naosh1", ciPersonnalise.getCreateurPseudo());
    }

    // ── Date de création ─────────────────────────────────────────────────────

    @Test
    @DisplayName("La date de création est non null")
    void dateCreation_nonNull() {
        assertNotNull(ciPredefini.getDateCreation());
    }

    // ── Setters ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Les setters modifient correctement les valeurs")
    void setters_modifientValeurs() {
        ciPredefini.setNom("rugby");
        ciPredefini.setPopularite(42);
        assertEquals("rugby", ciPredefini.getNom());
        assertEquals(42, ciPredefini.getPopularite());
    }

    // ── toString ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("toString contient le nom du CI")
    void toString_contientNom() {
        assertTrue(ciPredefini.toString().contains("football"));
    }
}
