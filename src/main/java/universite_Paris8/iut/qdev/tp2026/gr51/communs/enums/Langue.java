package universite_Paris8.iut.qdev.tp2026.gr51.communs.enums;

public enum Langue {

    FR("fr", "Français"),
    EN("en", "English"),
    DE("de", "Deutch"),
    ES("es", "Española"),
    IT("it", "Italiana");

    private final String code;
    private final String nom;

    Langue(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public static Langue fromCode(String code) {
        for (Langue l : values()) {
            if (l.code.equalsIgnoreCase(code)) {
                return l;
            }
        }
        throw new IllegalArgumentException("Code de langue inconnu : " + code);
    }
}
