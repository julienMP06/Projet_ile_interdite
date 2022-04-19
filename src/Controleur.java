import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class Controleur extends VueJoueurs implements ActionListener {
    CModele modele;

    //On initialise une variable qui compte le nombre de "fin de tour" pour pouvoir inonder les dernières tuiles à la fin;
    private int N = 0;

    private JLabel label;
    public Controleur(CModele modele, JLabel label) {
        super(modele);
        this.modele = modele;
        this.label = label;
    }

    public void actionPerformed(ActionEvent e) {
        //On incremente notre n
        N = N + 1;
        // quand on clique sur fin de tour le joueur suivant devient le joueur actuel
        if (modele.isPartiePerdue()) {
            label.setText("Tu ne peux plus jouer Recommence");
        } else {
            modele.getJ_actuel().ajoute_Cle();
            modele.j_suivant();
            //Quand on a passer 24 tours (le nombre de tuiles) on innonde les dernières
            if (N > 24) {
                for (int i = 0; i < 3; i++) {
                    int x = (int) (Math.random() * (CModele.LARGEUR)) + 1;
                    int y = (int) (Math.random() * (CModele.HAUTEUR)) + 1;
                    if (modele.getCas(x, y).GetEtat() == 0) {
                        modele.getCas(x, y).setEtat(1);
                    } else if (modele.getCas(x, y).GetEtat() == 1) {
                        modele.getCas(x, y).setEtat(2);
                    } else {
                        while (modele.getCas(x, y).GetEtat() == 2) {
                            x = (int) (Math.random() * (CModele.LARGEUR)) + 1;
                            y = (int) (Math.random() * (CModele.HAUTEUR)) + 1;
                        }
                        if (modele.getCas(x, y).GetEtat() == 0) {
                            modele.getCas(x, y).setEtat(1);
                        } else if (modele.getCas(x, y).GetEtat() == 1) {
                            modele.getCas(x, y).setEtat(2);
                        }
                        if (modele.getCas(x, y).etat == 2 && modele.getCas(x, y).CaseAdjacenteLibre(x, y) == false && modele.getCas(x, y).contient_joueur()) {
                            modele.SetPartiePerdue();
                            label.setText("Perdue");
                        }
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    int x = (int) (Math.random() * (CModele.LARGEUR)) + 1;
                    int y = (int) (Math.random() * (CModele.HAUTEUR)) + 1;
                    if (modele.getCas(x, y).etat == 0) {
                        modele.getCas(x, y).setEtat(1);
                    } else if (modele.getCas(x, y).etat == 1) {
                        modele.getCas(x, y).setEtat(2);
                    }
                    if (modele.getCas(x, y).etat == 2 && modele.getCas(x, y).CaseAdjacenteLibre(x, y) == false && modele.getCas(x, y).contient_joueur()) {
                        modele.SetPartiePerdue();
                        label.setText("Perdue");
                    } else if (modele.getCas(x, y).etat == 2 && modele.getCas(x, y).contient_artefact()) {
                        modele.SetPartiePerdue();
                        label.setText("Perdue un artefact a coule");
                    } else if (modele.getCas(x, y).etat == 2 && modele.getCas(x, y).contient_heleco()) {
                        modele.SetPartiePerdue();
                        label.setText("Perdue l'héliport à coule");
                    }
                }
            }
            modele.MAJ();
        }
    }
}
