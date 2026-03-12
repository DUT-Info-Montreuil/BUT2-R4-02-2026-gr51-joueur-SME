package universite_Paris8.iut.qdev.tp2026.gr51;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.CentreInteretDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.JoueurDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.LangueDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.services.interfaces.IJoueurService;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.AnneeNaissanceInvalideException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.EmailInvalideException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.JoueurIntrouvableException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.PseudoDejaUtiliseException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests du contrat de l'interface IJoueurService via une implémentation stub
 * inline (pas besoin de Mockito, pas besoin de l'implémentation réelle).
 */
@DisplayName("Tests IJoueurService (contrat de l'interface)")
class IJoueurServiceTest {

    // ── Stub minimal ──────────────────────────────────────────────────────────

    /**
     * Implémentation minimaliste pour valider le contrat de l'interface.
     * Elle reproduit les règles métier définies dans la DDC.
     */
    static class JoueurServiceStub implements IJoueurService {

        private final Map<String, JoueurDTO> store = new HashMap<>();

        @Override
        public List<JoueurDTO> listerJoueurs() {
            return new ArrayList<>(store.values());
        }

        @Override
        public JoueurDTO ajouterJoueur(JoueurDTO joueur)
                throws PseudoDejaUtiliseException, EmailInvalideException,
                       AnneeNaissanceInvalideException {

            // Règle : pseudo unique
            if (store.containsKey(joueur.getPseudo())) {
                throw new PseudoDejaUtiliseException(joueur.getPseudo());
            }
            // Règle : email obligatoire avec '@'
            if (joueur.getEmail() == null || !joueur.getEmail().contains("@")) {
                throw new EmailInvalideException(joueur.getEmail());
            }
            // Règle : année de naissance dans [annéeCourante-100 ; annéeCourante-7]
            int anneeActuelle = LocalDate.now().getYear();
            int min = anneeActuelle - 100;
            int max = anneeActuelle - 7;
            int annee = joueur.getAnneeNaissance().getYear();
            if (annee < min || annee > max) {
                throw new AnneeNaissanceInvalideException(annee, min, max);
            }

            store.put(joueur.getPseudo(), joueur);
            return joueur;
        }

        @Override
        public JoueurDTO chercherJoueurParPseudo(String pseudo)
                throws JoueurIntrouvableException {
            JoueurDTO j = store.get(pseudo);
            if (j == null) throw new JoueurIntrouvableException(pseudo);
            return j;
        }
    }

    // ── Setup ─────────────────────────────────────────────────────────────────

    private IJoueurService service;
    private JoueurDTO joueurValide;

    @BeforeEach
    void setUp() {
        service = new JoueurServiceStub();
        LangueDTO langue = new LangueDTO(1, "fr", "Français");
        CentreInteretDTO ci = new CentreInteretDTO(1, "football", false, true,
                LocalDate.now(), null, 0);
        joueurValide = new JoueurDTO("Naosh1", "Camillia", 0,
                LocalDate.of(2000, 6, 15), "camillia@example.com", ci, langue);
    }

    // ── listerJoueurs ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("listerJoueurs retourne une liste vide si aucun joueur")
    void listerJoueurs_listeVide_quandAucunJoueur() {
        List<JoueurDTO> liste = service.listerJoueurs();
        assertNotNull(liste);
        assertTrue(liste.isEmpty());
    }

    @Test
    @DisplayName("listerJoueurs retourne tous les joueurs ajoutés")
    void listerJoueurs_retourneTousLesJoueurs() throws Exception {
        service.ajouterJoueur(joueurValide);

        LangueDTO l2 = new LangueDTO(1, "en", "English");
        JoueurDTO j2 = new JoueurDTO("Ronin0205", "Aleksa", 0,
                LocalDate.of(1999, 3, 20), "aleksa@example.com",
                null, l2);
        service.ajouterJoueur(j2);

        assertEquals(2, service.listerJoueurs().size());
    }

    // ── ajouterJoueur ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("ajouterJoueur accepte un joueur valide et le retourne")
    void ajouterJoueur_joueurValide_retourneJoueur() throws Exception {
        JoueurDTO resultat = service.ajouterJoueur(joueurValide);
        assertNotNull(resultat);
        assertEquals("Naosh1", resultat.getPseudo());
    }

    @Test
    @DisplayName("ajouterJoueur lève PseudoDejaUtiliseException si pseudo déjà pris")
    void ajouterJoueur_pseudoDuplique_leveException() throws Exception {
        service.ajouterJoueur(joueurValide);
        JoueurDTO doublon = new JoueurDTO("Naosh1", "Autre", 0,
                LocalDate.of(2001, 1, 1), "autre@example.com", null, null);
        assertThrows(PseudoDejaUtiliseException.class,
                () -> service.ajouterJoueur(doublon));
    }

    @Test
    @DisplayName("ajouterJoueur lève EmailInvalideException si email sans '@'")
    void ajouterJoueur_emailSansArobase_leveException() {
        joueurValide.setEmail("emailsanatrobase.com");
        assertThrows(EmailInvalideException.class,
                () -> service.ajouterJoueur(joueurValide));
    }

    @Test
    @DisplayName("ajouterJoueur lève EmailInvalideException si email null")
    void ajouterJoueur_emailNull_leveException() {
        joueurValide.setEmail(null);
        assertThrows(EmailInvalideException.class,
                () -> service.ajouterJoueur(joueurValide));
    }

    @Test
    @DisplayName("ajouterJoueur lève AnneeNaissanceInvalideException si trop jeune")
    void ajouterJoueur_tropJeune_leveException() {
        joueurValide.setAnneeNaissance(LocalDate.now().minusYears(5));
        assertThrows(AnneeNaissanceInvalideException.class,
                () -> service.ajouterJoueur(joueurValide));
    }

    @Test
    @DisplayName("ajouterJoueur lève AnneeNaissanceInvalideException si trop vieux")
    void ajouterJoueur_tropVieux_leveException() {
        joueurValide.setAnneeNaissance(LocalDate.now().minusYears(105));
        assertThrows(AnneeNaissanceInvalideException.class,
                () -> service.ajouterJoueur(joueurValide));
    }

    @Test
    @DisplayName("ajouterJoueur accepte un joueur à la limite basse (annéeCourante - 100)")
    void ajouterJoueur_limiteBasse_accepte() {
        int anneeMin = LocalDate.now().getYear() - 100;
        joueurValide.setAnneeNaissance(LocalDate.of(anneeMin, 1, 1));
        assertDoesNotThrow(() -> service.ajouterJoueur(joueurValide));
    }

    @Test
    @DisplayName("ajouterJoueur accepte un joueur à la limite haute (annéeCourante - 7)")
    void ajouterJoueur_limiteHaute_accepte() {
        int anneeMax = LocalDate.now().getYear() - 7;
        joueurValide.setAnneeNaissance(LocalDate.of(anneeMax, 1, 1));
        assertDoesNotThrow(() -> service.ajouterJoueur(joueurValide));
    }

    // ── chercherJoueurParPseudo ───────────────────────────────────────────────

    @Test
    @DisplayName("chercherJoueurParPseudo retourne le bon joueur")
    void chercherJoueurParPseudo_joueurExiste_retourneJoueur() throws Exception {
        service.ajouterJoueur(joueurValide);
        JoueurDTO trouve = service.chercherJoueurParPseudo("Naosh1");
        assertEquals("Naosh1", trouve.getPseudo());
    }

    @Test
    @DisplayName("chercherJoueurParPseudo lève JoueurIntrouvableException si introuvable")
    void chercherJoueurParPseudo_joueurAbsent_leveException() {
        assertThrows(JoueurIntrouvableException.class,
                () -> service.chercherJoueurParPseudo("fantome"));
    }
}
