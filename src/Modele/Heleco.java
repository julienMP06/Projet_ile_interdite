package Modele;

class Heleco extends Zone_speciale {

    private Case c;

    public Heleco(Case c) {
        super("helicoptere");
        this.c = c;
    }

    public Case getCaseHelico() {
        return this.c;
    }

}
