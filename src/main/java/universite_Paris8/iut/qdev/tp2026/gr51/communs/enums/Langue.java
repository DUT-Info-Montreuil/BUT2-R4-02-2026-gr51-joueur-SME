package universite_paris8.iut.qdev.tp2026.gr51.communs.enums;

public enum Langue {

    FR(1, "fr", "Français"),
    EN(2, "en", "English"),
    DE(3, "de", "Deutch"),
    ES(4, "es", "Española"),
    IT(5, "it", "Italiana");

    private final int id;
    private final String code;
    private final String nom;

    Langue(int id, String code, String nom) {
        this.id = id;
        this.code = code;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public static Langue fromId(int id) {
        for (Langue l : values()) {
            if (l.id == id) return l;
        }
        throw new IllegalArgumentException("Id de langue inconnu : " + id);
    }

    public static Langue fromCode(String code) {
        for (Langue l : values()) {
            if (l.code.equalsIgnoreCase(code)) return l;
        }
        throw new IllegalArgumentException("Code de langue inconnu : " + code);
    }
}
