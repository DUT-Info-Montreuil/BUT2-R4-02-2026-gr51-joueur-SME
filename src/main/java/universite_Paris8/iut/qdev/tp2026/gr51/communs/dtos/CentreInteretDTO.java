package universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos;

import java.time.LocalDate;

/**
 * DTO représentant un centre d'intérêt.
 */
public class CentreInteretDTO {

    private int id;
    private String nom;                // ex : "football", "sport", "voiture" — unique et prédéfini
    private boolean estPersonnalise;   // true si ajouté par un joueur, false si prédéfini
    private boolean certifie;          // false si personnalisé, true si prédéfini
    private LocalDate dateCreation;
    private String createurPseudo;     // pseudo du joueur créateur (null si prédéfini)
    private int popularite;            // nombre de joueurs ayant cet intérêt (initialisé à 0)

    public CentreInteretDTO() {}

    public CentreInteretDTO(int id, String nom, boolean estPersonnalise,
                            boolean certifie, LocalDate dateCreation,
                            String createurPseudo, int popularite) {
        this.id = id;
        this.nom = nom;
        this.estPersonnalise = estPersonnalise;
        this.certifie = certifie;
        this.dateCreation = dateCreation;
        this.createurPseudo = createurPseudo;
        this.popularite = popularite;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public boolean isEstPersonnalise() { return estPersonnalise; }
    public void setEstPersonnalise(boolean estPersonnalise) { this.estPersonnalise = estPersonnalise; }

    public boolean isCertifie() { return certifie; }
    public void setCertifie(boolean certifie) { this.certifie = certifie; }

    public LocalDate getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDate dateCreation) { this.dateCreation = dateCreation; }

    public String getCreateurPseudo() { return createurPseudo; }
    public void setCreateurPseudo(String createurPseudo) { this.createurPseudo = createurPseudo; }

    public int getPopularite() { return popularite; }
    public void setPopularite(int popularite) { this.popularite = popularite; }

    @Override
    public String toString() {
        return "CentreInteretDTO{id=" + id + ", nom='" + nom + "', estPersonnalise=" + estPersonnalise
                + ", certifie=" + certifie + ", popularite=" + popularite + "}";
    }
}
