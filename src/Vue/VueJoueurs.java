package Vue;
import Modele.*;
import Controleurs.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class VueJoueurs extends JPanel implements Observer{
    private CModele modele;
    /**
     *  On definit le nombre de ligne de notre panel ainsi que tous les labels nécessaires (pour tous les joueurs)
    */
    GridLayout grid = new GridLayout(5, 0);
    private JLabel Affichage = new JLabel();
    private JLabel labelJ1 = new JLabel();
    private JLabel labelJ2 = new JLabel();
    private JLabel labelJ3 = new JLabel();
    private JLabel labelJ4 = new JLabel();
    private JPanel panelJ = new JPanel();
    private String s;
    public VueJoueurs (CModele modele){

        panelJ.setLayout(grid);
        this.modele=modele;
        grid.setVgap(40);
        modele.addObserver(this);

        /**
         * On affiche le texte du haut, puis on ajoute au panel les différents textes pour les joueurs
         */
        Affichage.setText("Joueur    Actions    Cle (F,A,T,E)");
        panelJ.add(Affichage);
        panelJ.add(labelJ1);
        panelJ.add(labelJ2);
        panelJ.add(labelJ3);
        panelJ.add(labelJ4);

        /**
         * On ajoute le panel pour pouvoir l'afficher
         */
        this.add(panelJ);
        this.update();
    }

    public void update() {
        /**
         * Pour mettre a jour les couleur et les valeurs, on refait les textes des labels
         * */
        repaint();
        paintComponent();
    }

    public void paintComponent() {
        super.repaint();
        paint(modele.getJoueurs().get(0),labelJ1);
        paint(modele.getJoueurs().get(1),labelJ2);
        paint(modele.getJoueurs().get(2),labelJ3);
        paint(modele.getJoueurs().get(3),labelJ4);
    }

    private void paint(Joueur j, JLabel label) {
        /**
         * On Fait le texte du label pour tous les joueurs sous la même forme,
         * On a plus qu'a appeler la méthode pour les différents joueurs
         */
        label.setText(j.getNom_joueur()+"               "+j.getNb_act()+"                "+
                j.get_nb_cle("Feu")+"  "+j.get_nb_cle("Air")+"  "+j.get_nb_cle("Terre")+"  "+j.get_nb_cle("Eau"));

        /**
         * Ici on regarde le joueur actuel, ce qui va nous permettre de colorier son label en vert et remettre le
         * label du joueur précédent en noir.
         */
        s = modele.getJ_actuel().getNom_joueur();
        if (s == "J1") {
            labelJ4.setForeground(Color.BLACK);
            labelJ1.setForeground(Color.GREEN);
        }else if (s == "J2") {
            labelJ1.setForeground(Color.BLACK);
            labelJ2.setForeground(Color.GREEN);
        }else if (s == "J3") {
            labelJ2.setForeground(Color.BLACK);
            labelJ3.setForeground(Color.GREEN);
        }else if (s == "J4") {
            labelJ3.setForeground(Color.BLACK);
            labelJ4.setForeground(Color.GREEN);
        }
    }
}



