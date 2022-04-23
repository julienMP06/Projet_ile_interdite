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
    	
    	
    	N = N + 1;
    	modele.j_suivant();
        if (modele.partie_gagnee()) {}
        else if (modele.partie_perdue()){label.setText("Vous avez perdus, reesayez une autre fois");}
        else {
        	modele.noyer_trois_tuiles();
        	modele.getJ_actuel().ajoute_Cle();
            modele.getJ_actuel().ajoute_ActionSpe();
        }
        modele.MAJ();
    	
    }   	
}
