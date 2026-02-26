package universite_Paris8.iut.qdev.tp2026.gr51.services.interfaces;

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
     * @param joueur le joueur à ajouter
     * @return le {@link JoueurDTO} créé (avec les valeurs par défaut appliquées)
     * @throws PseudoDejaUtiliseException     si le pseudo est déjà utilisé
     * @throws EmailInvalideException         si l'email ne contient pas de '@' ou est vide
     * @throws AnneeNaissanceInvalideException si l'année de naissance est hors plage
     */
    JoueurDTO ajouterJoueur(JoueurDTO joueur)
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
