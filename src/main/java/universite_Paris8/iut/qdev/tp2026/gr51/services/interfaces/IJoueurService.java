package universite_Paris8.iut.qdev.tp2026.gr51.services.interfaces;

import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.CentreInteretDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.JoueurDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.AnneeNaissanceInvalideException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.EmailInvalideException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.JoueurIntrouvableException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.PseudoDejaUtiliseException;

import java.util.List;

/**
 * Interface du service Joueur (SME Joueur).
 */
public interface IJoueurService {

    /**
     * Retourne la liste de tous les joueurs inscrits.
     *
     * @return liste de {@link JoueurDTO}, éventuellement vide
     */
    List<JoueurDTO> listerJoueurs();

    /**
     * Ajoute un nouveau joueur dans le système.
     *
     * @param pseudo           pseudo unique du joueur (ne doit pas commencer par un chiffre)
     * @param prenom           prénom du joueur
     * @param anneeNaissance   année de naissance (plage : annéeCourante-100 à annéeCourante-7)
     * @param email            adresse email (doit contenir un '@'), peut être null/vide
     * @param centresInterets  liste des centres d'intérêt (peut être vide)
     * @param langueEnum           langue préférée du joueur
     * @return le {@link JoueurDTO} créé
     * @throws PseudoDejaUtiliseException      si le pseudo est déjà utilisé
     * @throws EmailInvalideException          si l'email ne contient pas de '@'
     * @throws AnneeNaissanceInvalideException si l'année de naissance est hors plage
     */
    JoueurDTO ajouterJoueur(String pseudo, String prenom, int anneeNaissance,
                            String email, List<CentreInteretDTO> centresInterets, universite_paris8.iut.qdev.tp2026.gr51.communs.enums.LangueEnum langueEnum)
            throws PseudoDejaUtiliseException, EmailInvalideException, AnneeNaissanceInvalideException;

    /**
     * Recherche un joueur par son pseudo.
     *
     * @param pseudo le pseudo unique du joueur
     * @return le {@link JoueurDTO} correspondant
     * @throws JoueurIntrouvableException si aucun joueur ne correspond
     */
    JoueurDTO chercherJoueurParPseudo(String pseudo) throws JoueurIntrouvableException;
}