import IG.ZoneCliquable;
import java.util.Random;

class Bouton extends ZoneCliquable {

    private Plateau plateau;

    public Bouton(Plateau p) {
        super("Fin De Tour", 100, 30);
        this.plateau = p;
    }

    public void clicGauche() {
        for(int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * (plateau.getTaille() - 0));
            int y = (int) (Math.random() * (plateau.getTaille() - 0));
            if (plateau.getCase(x, y).etat == 0) {
                plateau.getCase(x, y).etat = 1;
            } else {
                plateau.getCase(x, y).etat = 2;
            }
            plateau.getCase(x, y).ChangementEtat();
        }
    }

    public void clicDroit() {}
}