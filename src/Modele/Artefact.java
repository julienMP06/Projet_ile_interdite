package Modele;

public class Artefact extends items {

    private Case c;

    public Artefact(String nom, Case c) {
        super(nom);
        this.c = c;
    }

    public Case EstDans() {
        return this.c;
    }

    public void setC(Case c) {
        this.c = c;
    }


}
