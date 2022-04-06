import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;

public class Controleur implements ActionListener {
    CModele modele;
   

    public Controleur(CModele modele) { this.modele = modele; }

    public void actionPerformed(ActionEvent e) {
        
        System.out.println("avant"+modele.getJ_actuel().getNom_joueur());
        // quand on clique sur fin de tour le joueur suivant devient le joueur actuel
        modele.j_suivant();
        System.out.println("-----apres"+modele.getJ_actuel().getNom_joueur());
        for(int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * (CModele.LARGEUR));
            int y = (int) (Math.random() * (CModele.HAUTEUR));
            if (modele.getCas(x,y).etat == 0){
                modele.getCas(x,y).etat = 1;
            }
            else if (modele.getCas(x,y).etat == 1){
                modele.getCas(x,y).etat = 2;
            }
        }
        modele.MAJ();
    }

}