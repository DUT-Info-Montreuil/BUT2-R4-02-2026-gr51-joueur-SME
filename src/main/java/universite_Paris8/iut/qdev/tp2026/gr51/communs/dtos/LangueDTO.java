package universite_Paris8.iut.qdev.tp2026.gr51.communs.dtos;

/**
 * DTO représentant une langue.
 * Codes acceptés : "fr", "en", "de", "es", "it"
 */
public class LangueDTO {

    private int id;
    private String code;   // "fr", "en", "de", "es", "it"
    private String nom;    // "Français", "English", "Deutch", "Española", "Italiana"

    public LangueDTO() {}

    public LangueDTO(int id, String code, String nom) {
        this.id = id;
        this.code = code;
        this.nom = nom;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    @Override
    public String toString() {
        return "LangueDTO{id=" + id + ", code='" + code + "', nom='" + nom + "'}";
    }
}
