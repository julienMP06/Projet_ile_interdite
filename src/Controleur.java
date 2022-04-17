import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class Controleur extends VueJoueurs implements ActionListener {
    CModele modele;
    public Controleur(CModele modele) {
        super(modele);
        this.modele = modele;
    }

    public void actionPerformed(ActionEvent e) {
        // quand on clique sur fin de tour le joueur suivant devient le joueur actuel
        modele.getJ_actuel().ajoute_Cle();
        modele.j_suivant();
        super.JoueurPanneau(super.Get_JT());

        for(int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * (CModele.LARGEUR))+1;
            int y = (int) (Math.random() * (CModele.HAUTEUR))+1;
            if (modele.getCas(x,y).etat == 0 ){
                modele.getCas(x,y).setEtat(1);
            }
            else if (modele.getCas(x,y).etat == 1){
                modele.getCas(x,y).setEtat(2);
            }
        }
        modele.MAJ();
    }
}
