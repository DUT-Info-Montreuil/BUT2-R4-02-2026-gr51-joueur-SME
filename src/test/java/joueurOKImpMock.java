import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.CentreInteretDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos.JoueurDTO;
import universite_Paris8.iut.qdev.tp2026.gr51.communs.enums.LangueEnum;
import universite_Paris8.iut.qdev.tp2026.gr51.services.interfaces.IJoueurService;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.AnneeNaissanceInvalideException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.EmailInvalideException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.JoueurIntrouvableException;
import universite_Paris8.iut.qdev.tp2026.gr51.utils.exceptions.PseudoDejaUtiliseException;

import java.util.List;

public class joueurOKImpMock implements IJoueurService {
    @Override
    public List<JoueurDTO> listerJoueurs() {
        return List.of();
    }

    @Override
    public JoueurDTO ajouterJoueur(String pseudo, String prenom, int anneeNaissance, String email, List<CentreInteretDTO> centresInterets, LangueEnum langueEnum) throws PseudoDejaUtiliseException, EmailInvalideException, AnneeNaissanceInvalideException {
        return new JoueurDTO();
    }

    @Override
    public JoueurDTO chercherJoueurParPseudo(String pseudo) throws JoueurIntrouvableException {
        return new JoueurDTO();
    }
}
