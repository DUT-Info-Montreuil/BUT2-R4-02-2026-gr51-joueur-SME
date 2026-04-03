
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.CentreInteretDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.JoueurDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.enums.LangueEnum;
import universite_Paris8.iut.qdev.tp2026.gr51.services.interfaces.IJoueurService;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.*;

        import java.time.LocalDate;
import java.util.*;

        import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests IJoueurService")
class IJoueurServiceTest {

    // ══════════════════════════════════════════════════════════════════════════
    // STUB
    // ══════════════════════════════════════════════════════════════════════════

    static class JoueurServiceStub implements IJoueurService {

        private final Map<String, JoueurDTO> store = new LinkedHashMap<>();

        @Override
        public List<JoueurDTO> listerJoueurs() {
            return new ArrayList<>(store.values());
        }

        @Override
        public JoueurDTO ajouterJoueur(String pseudo, String prenom, int anneeNaissance,
                                       String email, List<CentreInteretDTO> centresInterets,
                                       LangueEnum langueEnum)
                throws PseudoDejaUtiliseException, EmailInvalideException,
                AnneeNaissanceInvalideException {

            // Pseudo unique
            if (store.containsKey(pseudo))
                throw new PseudoDejaUtiliseException(pseudo);

            // Pseudo ne commence pas par un chiffre
            if (pseudo.isEmpty() || Character.isDigit(pseudo.charAt(0)))
                throw new IllegalArgumentException("Le pseudo ne doit pas commencer par un chiffre.");

            // Email : si renseigné, doit contenir '@'
            if (email != null && !email.isEmpty() && !email.contains("@"))
                throw new EmailInvalideException(email);

            // Année de naissance dans la plage [annéeCourante-100 ; annéeCourante-7]
            int anneeActuelle = LocalDate.now().getYear();
            int min = anneeActuelle - 100;
            int max = anneeActuelle - 7;
            if (anneeNaissance < min || anneeNaissance > max)
                throw new AnneeNaissanceInvalideException(anneeNaissance, min, max);

            List<CentreInteretDTO> ci = (centresInterets != null && !centresInterets.isEmpty())
                    ? centresInterets.get(0) : null;
            JoueurDTO joueur = new JoueurDTO(pseudo, prenom, 0,
                    LocalDate.of(anneeNaissance, 1, 1), email, ci, langueEnum);
            store.put(pseudo, joueur);
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

    // ══════════════════════════════════════════════════════════════════════════
    // SETUP
    // ══════════════════════════════════════════════════════════════════════════

    private IJoueurService service;

    /** Année toujours dans la plage valide [annéeCourante-100 ; annéeCourante-7]. */
    private static final int ANNEE_VALIDE = LocalDate.now().getYear() - 20;

    private static final List<CentreInteretDTO> CI_FOOTBALL = List.of(
            new CentreInteretDTO(1, "football", false, true, LocalDate.now(), null, 0));

    @BeforeEach
    void setUp() {
        service = new JoueurServiceStub();
    }

    // ══════════════════════════════════════════════════════════════════════════
    // listerJoueurs
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("listerJoueurs — retourne une liste vide quand aucun joueur n'est enregistré")
    void listerJoueurs_aucunJoueur_retourneListeVide() {
        List<JoueurDTO> liste = service.listerJoueurs();
        assertNotNull(liste);
        assertTrue(liste.isEmpty());
    }

    @Test
    @DisplayName("listerJoueurs — retourne les joueurs après ajout")
    void listerJoueurs_apresAjout_retourneTousLesJoueurs() throws Exception {
        service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE, null, CI_FOOTBALL, LangueEnum.FR);
        service.ajouterJoueur("Ronin0205", "Aleksa", ANNEE_VALIDE, null, List.of(), LangueEnum.EN);

        assertEquals(2, service.listerJoueurs().size());
    }

    @Test
    @DisplayName("listerJoueurs — le joueur retourné a les bonnes valeurs")
    void listerJoueurs_retourneJoueurAvecBonnesValeurs() throws Exception {
        service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE, null, CI_FOOTBALL, LangueEnum.FR);

        JoueurDTO j = service.listerJoueurs().get(0);
        assertEquals("Naosh1", j.getPseudo());
        assertEquals("Camillia", j.getPrenom());
        assertEquals(LangueEnum.FR, j.getLangue());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // ajouterJoueur — cas nominaux
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("ajouterJoueur — joueur valide : retourne le DTO créé")
    void ajouterJoueur_joueurValide_retourneDTO() throws Exception {
        JoueurDTO res = service.ajouterJoueur(
                "Naosh1", "Camillia", ANNEE_VALIDE, null, CI_FOOTBALL, LangueEnum.FR);

        assertNotNull(res);
        assertEquals("Naosh1", res.getPseudo());
        assertEquals("Camillia", res.getPrenom());
        assertEquals(0, res.getScore());
        assertEquals(LangueEnum.FR, res.getLangue());
    }

    @Test
    @DisplayName("ajouterJoueur — score initialisé à 0")
    void ajouterJoueur_scoreInitialiseAZero() throws Exception {
        JoueurDTO res = service.ajouterJoueur(
                "Naosh1", "Camillia", ANNEE_VALIDE, null, CI_FOOTBALL, LangueEnum.FR);
        assertEquals(0, res.getScore());
    }

    @Test
    @DisplayName("ajouterJoueur — email null accepté")
    void ajouterJoueur_emailNull_accepte() {
        assertDoesNotThrow(() ->
                service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE,
                        null, CI_FOOTBALL, LangueEnum.FR));
    }

    @Test
    @DisplayName("ajouterJoueur — email vide accepté")
    void ajouterJoueur_emailVide_accepte() {
        assertDoesNotThrow(() ->
                service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE,
                        "", CI_FOOTBALL, LangueEnum.FR));
    }

    @Test
    @DisplayName("ajouterJoueur — centres d'intérêt vide accepté")
    void ajouterJoueur_centresInteretVide_accepte() {
        assertDoesNotThrow(() ->
                service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE,
                        null, List.of(), LangueEnum.FR));
    }

    @Test
    @DisplayName("ajouterJoueur — toutes les langues de l'enum sont acceptées")
    void ajouterJoueur_toutesLesLangues_acceptees() {
        int i = 0;
        for (LangueEnum langue : LangueEnum.values()) {
            final String pseudo = "Joueur" + i++;
            assertDoesNotThrow(() ->
                    service.ajouterJoueur(pseudo, "Prenom", ANNEE_VALIDE,
                            null, List.of(), langue));
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // ajouterJoueur — pseudo
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("ajouterJoueur — pseudo dupliqué lève PseudoDejaUtiliseException")
    void ajouterJoueur_pseudoDuplique_levePseudoDejaUtiliseException() throws Exception {
        service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE, null, CI_FOOTBALL, LangueEnum.FR);

        assertThrows(PseudoDejaUtiliseException.class, () ->
                service.ajouterJoueur("Naosh1", "Autre", ANNEE_VALIDE,
                        null, List.of(), LangueEnum.EN));
    }

    @Test
    @DisplayName("ajouterJoueur — pseudo commençant par un chiffre lève IllegalArgumentException")
    void ajouterJoueur_pseudoCommenceParChiffre_leveIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.ajouterJoueur("1Naosh", "Camillia", ANNEE_VALIDE,
                        null, CI_FOOTBALL, LangueEnum.FR));
    }

    @Test
    @DisplayName("ajouterJoueur — même prénom, pseudo différent : les deux joueurs sont acceptés")
    void ajouterJoueur_memePrenomPseudoDifferent_accepte() {
        assertDoesNotThrow(() -> {
            service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE, null, CI_FOOTBALL, LangueEnum.FR);
            service.ajouterJoueur("Naosh2", "Camillia", ANNEE_VALIDE, null, CI_FOOTBALL, LangueEnum.FR);
        });
    }

    // ══════════════════════════════════════════════════════════════════════════
    // ajouterJoueur — email
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("ajouterJoueur — email sans '@' lève EmailInvalideException")
    void ajouterJoueur_emailSansArobase_leveEmailInvalideException() {
        assertThrows(EmailInvalideException.class, () ->
                service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE,
                        "emailsansarobase.com", CI_FOOTBALL, LangueEnum.FR));
    }

    @Test
    @DisplayName("ajouterJoueur — email valide avec '@' accepté")
    void ajouterJoueur_emailValide_accepte() {
        assertDoesNotThrow(() ->
                service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE,
                        "camillia@example.com", CI_FOOTBALL, LangueEnum.FR));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // ajouterJoueur — année de naissance
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("ajouterJoueur — limite basse exacte (annéeCourante - 100) acceptée")
    void ajouterJoueur_limiteBasseExacte_accepte() {
        int anneeMin = LocalDate.now().getYear() - 100;
        assertDoesNotThrow(() ->
                service.ajouterJoueur("Naosh1", "Camillia", anneeMin,
                        null, CI_FOOTBALL, LangueEnum.FR));
    }

    @Test
    @DisplayName("ajouterJoueur — limite haute exacte (annéeCourante - 7) acceptée")
    void ajouterJoueur_limiteHauteExacte_accepte() {
        int anneeMax = LocalDate.now().getYear() - 7;
        assertDoesNotThrow(() ->
                service.ajouterJoueur("Naosh1", "Camillia", anneeMax,
                        null, CI_FOOTBALL, LangueEnum.FR));
    }

    @Test
    @DisplayName("ajouterJoueur — trop jeune (annéeCourante - 6) lève AnneeNaissanceInvalideException")
    void ajouterJoueur_tropJeune_leveAnneeNaissanceInvalideException() {
        int tropJeune = LocalDate.now().getYear() - 6;
        assertThrows(AnneeNaissanceInvalideException.class, () ->
                service.ajouterJoueur("Naosh1", "Camillia", tropJeune,
                        null, CI_FOOTBALL, LangueEnum.FR));
    }

    @Test
    @DisplayName("ajouterJoueur — trop vieux (annéeCourante - 101) lève AnneeNaissanceInvalideException")
    void ajouterJoueur_tropVieux_leveAnneeNaissanceInvalideException() {
        int tropVieux = LocalDate.now().getYear() - 101;
        assertThrows(AnneeNaissanceInvalideException.class, () ->
                service.ajouterJoueur("Naosh1", "Camillia", tropVieux,
                        null, CI_FOOTBALL, LangueEnum.FR));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // chercherJoueurParPseudo
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("chercherJoueurParPseudo — pseudo existant retourne le bon joueur")
    void chercherJoueurParPseudo_pseudoExistant_retourneJoueur() throws Exception {
        service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE, null, CI_FOOTBALL, LangueEnum.FR);

        JoueurDTO trouve = service.chercherJoueurParPseudo("Naosh1");
        assertNotNull(trouve);
        assertEquals("Naosh1", trouve.getPseudo());
    }

    @Test
    @DisplayName("chercherJoueurParPseudo — pseudo inexistant lève JoueurIntrouvableException")
    void chercherJoueurParPseudo_pseudoInexistant_leveJoueurIntrouvableException() {
        assertThrows(JoueurIntrouvableException.class, () ->
                service.chercherJoueurParPseudo("fantome"));
    }

    @Test
    @DisplayName("chercherJoueurParPseudo — sensible à la casse")
    void chercherJoueurParPseudo_sensibleCasse_leveException() throws Exception {
        service.ajouterJoueur("Naosh1", "Camillia", ANNEE_VALIDE, null, CI_FOOTBALL, LangueEnum.FR);

        // "naosh1" != "Naosh1"
        assertThrows(JoueurIntrouvableException.class, () ->
                service.chercherJoueurParPseudo("naosh1"));
    }
}