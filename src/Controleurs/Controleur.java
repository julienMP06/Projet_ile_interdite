package Controleurs;
import Vue.*;
import Modele.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class Controleur extends VueJoueurs implements ActionListener {

    /**
     *Le controleur pour le bouton fin de tour. il doit noyer 3 tuiles , ajouter une cle (on pas) au joueurs qui vient
     * de jouer de même pour les actions spéciales. Passer au joueur suivant et afficher un petit texte.
     * Et vérifier si la partie est perdue/gagnée
     */
    CModele modele;

    //On initialise une variable qui compte le nombre de "fin de tour" pour pouvoir inonder les dernières tuiles à la fin;
    private int N = 0;

    private JLabel label;

    private JPanel panelC;

    private JTextField EntrerX;

    private JTextField EntrerY;

    private JButton valider;

    private JCheckBox oui;

    private JButton validerEchange;

    private JTextField CleEchange;

    public Controleur(CModele modele, JLabel labelC, JPanel panelC, JTextField EntrerX, JTextField EntrerY, JButton valider, JCheckBox oui, JButton validerEchange, JTextField CleEchange) {
        super(modele);
        this.modele = modele;
        this.label = labelC;
        this.panelC = panelC;
        this.EntrerX = EntrerX;
        this.EntrerY = EntrerY;
        this.valider = valider;
        this.oui = oui;
        this.validerEchange = validerEchange;
        this.CleEchange = CleEchange;
    }

    public void actionPerformed(ActionEvent e) {
        panelC.remove(EntrerX);
        panelC.remove(EntrerY);
        panelC.remove(valider);
        panelC.remove(oui);
        panelC.remove(CleEchange);
        panelC.remove(validerEchange);
        panelC.revalidate();
        panelC.repaint();
    	N = N + 1;
        modele.getJ_actuel().setNb_act(0);
        if (modele.partie_gagnee()) {label.setText("Vous avez gagne, Bravo !");}
        else if (modele.partie_perdue()){label.setText("Vous avez perdus, reesayez une autre fois");}
        else{
        	modele.noyer_trois_tuiles();
        	modele.getJ_actuel().ajoute_Cle();
            modele.getJ_actuel().ajoute_ActionSpe();
            modele.j_suivant();
            label.setText("Au tour de "+modele.getJ_actuel().getNom_joueur());
        }
        modele.MAJ();
    }   	
}
