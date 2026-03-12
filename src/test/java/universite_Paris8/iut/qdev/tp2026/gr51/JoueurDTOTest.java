package universite_Paris8.iut.qdev.tp2026.gr51;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.CentreInteretDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.JoueurDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.LangueDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests JoueurDTO")
class JoueurDTOTest {

    private JoueurDTO joueur;
    private LangueDTO langue;
    private CentreInteretDTO centreInteret;

    @BeforeEach
    void setUp() {
        langue = new LangueDTO(1, "fr", "Français");
        centreInteret = new CentreInteretDTO(1, "football", false, true,
                LocalDate.now(), null, 0);
        joueur = new JoueurDTO("Naosh1", "Camillia", 0,
                LocalDate.of(2000, 1, 1), "camillia@example.com",
                centreInteret, langue);
    }

    // ── Constructeur & getters ──────────────────────────────────────────────

    @Test
    @DisplayName("Le constructeur plein initialise correctement tous les champs")
    void constructeurPlein_initialiseChamps() {
        assertEquals("Naosh1", joueur.getPseudo());
        assertEquals("Camillia", joueur.getPrenom());
        assertEquals(0, joueur.getScore());
        assertEquals(LocalDate.of(2000, 1, 1), joueur.getAnneeNaissance());
        assertEquals("camillia@example.com", joueur.getEmail());
        assertNotNull(joueur.getCentreInteret());
        assertNotNull(joueur.getLangue());
    }

    @Test
    @DisplayName("Le constructeur vide crée un objet non null")
    void constructeurVide_objetNonNull() {
        JoueurDTO j = new JoueurDTO();
        assertNotNull(j);
    }

    // ── Score ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Le score est initialisé à 0 par défaut")
    void score_initialiseAZero() {
        assertEquals(0, joueur.getScore());
    }

    @Test
    @DisplayName("Le score peut être négatif (entier relatif)")
    void score_peutEtreNegatif() {
        joueur.setScore(-42);
        assertEquals(-42, joueur.getScore());
    }

    @Test
    @DisplayName("Le score peut être positif")
    void score_peutEtrePositif() {
        joueur.setScore(1500);
        assertEquals(1500, joueur.getScore());
    }

    // ── Pseudo ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Le pseudo est modifiable via le setter")
    void pseudo_setterFonctionne() {
        joueur.setPseudo("Ronin0205");
        assertEquals("Ronin0205", joueur.getPseudo());
    }

    // ── Email ────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("L'email contient bien un '@'")
    void email_contientArobase() {
        assertTrue(joueur.getEmail().contains("@"));
    }

    @Test
    @DisplayName("L'email est modifiable via le setter")
    void email_setterFonctionne() {
        joueur.setEmail("nouveau@mail.com");
        assertEquals("nouveau@mail.com", joueur.getEmail());
    }

    // ── Année de naissance ───────────────────────────────────────────────────

    @Test
    @DisplayName("L'année de naissance est dans la plage autorisée [annéeCourante-100 ; annéeCourante-7]")
    void anneeNaissance_dansPlageLegale() {
        int annee = joueur.getAnneeNaissance().getYear();
        int anneeActuelle = LocalDate.now().getYear();
        assertTrue(annee >= anneeActuelle - 100);
        assertTrue(annee <= anneeActuelle - 7);
    }

    // ── Relations ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("La langue associée est correcte")
    void langue_associeeCorrecte() {
        assertEquals("fr", joueur.getLangue().getCode());
        assertEquals("Français", joueur.getLangue().getNom());
    }

    @Test
    @DisplayName("Le centre d'intérêt associé est correct")
    void centreInteret_associeCorrect() {
        assertEquals("football", joueur.getCentreInteret().getNom());
    }

    // ── toString ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("toString contient le pseudo et l'email")
    void toString_contientInfosCles() {
        String s = joueur.toString();
        assertTrue(s.contains("Naosh1"));
        assertTrue(s.contains("camillia@example.com"));
    }
}
