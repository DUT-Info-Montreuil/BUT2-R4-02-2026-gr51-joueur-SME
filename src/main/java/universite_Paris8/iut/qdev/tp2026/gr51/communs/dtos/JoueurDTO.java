package universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO représentant un joueur.
 *
 * Règle anneeNaissance :
 *   - Min  = année courante − 100
 *   - Max  = année courante − 7
 *   - Valeur par défaut : 01/01/anneeMinimum
 */
public class JoueurDTO {

    private String pseudo;           // unique et obligatoire
    private String prenom;
    private int score;               // entier relatif, initialisé à 0
    private LocalDate anneeNaissance;
    private String email;            // doit contenir un '@', obligatoire
    private List<CentreInteretDTO> centreInterets;
    private universite_paris8.iut.qdev.tp2026.gr51.communs.enums.LangueEnum langueEnum;

    public JoueurDTO() {}

    public JoueurDTO(String pseudo, String prenom, int score,
                     LocalDate anneeNaissance, String email,
                     List<CentreInteretDTO> centresInterets, universite_paris8.iut.qdev.tp2026.gr51.communs.enums.LangueEnum langueEnum) {
        this.pseudo = pseudo;
        this.prenom = prenom;
        this.score = score;
        this.anneeNaissance = anneeNaissance;
        this.email = email;
        this.centreInterets = centresInterets;
        this.langueEnum = langueEnum;
    }

    public String getPseudo() { return pseudo; }
    public void setPseudo(String pseudo) { this.pseudo = pseudo; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public LocalDate getAnneeNaissance() { return anneeNaissance; }
    public void setAnneeNaissance(LocalDate anneeNaissance) { this.anneeNaissance = anneeNaissance; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<CentreInteretDTO> getCentreInterets() { return centreInterets; }
    public void setCentreInterets(List<CentreInteretDTO> centreInterets) { this.centreInterets = centreInterets; }

    public universite_paris8.iut.qdev.tp2026.gr51.communs.enums.LangueEnum getLangue() { return langueEnum; }
    public void setLangue(universite_paris8.iut.qdev.tp2026.gr51.communs.enums.LangueEnum langueEnum) { this.langueEnum = langueEnum; }

    @Override
    public String toString() {
        return "JoueurDTO{pseudo='" + pseudo + "', prenom='" + prenom
                + "', score=" + score + ", email='" + email + "'}";
    }
}