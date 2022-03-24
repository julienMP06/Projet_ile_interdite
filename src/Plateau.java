import IG.Grille;

import javax.swing.*;

public class Plateau extends Grille {

    private int taille;

    private Case[][] plateau;
    public Case getCase(int i, int j) {
        return plateau[i][j];
    }

    public int getTaille() {
        return taille;
    }

    public Plateau(int taille){
        super(taille, taille);
        this.taille = taille;
        this.plateau = new Case[taille][taille];
        for(int i = 0; i < taille; i++){
            for(int j = 0; j < taille; j++){
                Case cas = new Case(this);
                plateau[i][j] = cas;
                this.ajouteElement(cas);
            }
        }
    }

}
