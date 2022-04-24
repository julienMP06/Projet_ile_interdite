package Modele;

abstract class Zone_speciale {
    private String nom;

    public Zone_speciale(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
