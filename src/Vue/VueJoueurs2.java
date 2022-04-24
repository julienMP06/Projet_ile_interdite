package Vue;

import Modele.*;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class VueJoueurs2 extends JPanel implements Observer{
    private CModele modele;

    /**
     * La même class que Vue.VueJoueurs mais pour afficher les Actions Spéciales et les Artefacts
     * On affiche ça dans la 4eme partie de notre fenetre qui est en bas à droite (GridLayout(2,0))
     */
    GridLayout grid = new GridLayout(5, 0);

    private JLabel Affichage = new JLabel();
    private JLabel labelJ1 = new JLabel();
    private JLabel labelJ2 = new JLabel();
    private JLabel labelJ3 = new JLabel();
    private JLabel labelJ4 = new JLabel();
    private JPanel panelJ = new JPanel();
    private String s;
    public VueJoueurs2 (CModele modele){

        panelJ.setLayout(grid);
        this.modele=modele;
        grid.setVgap(40);
        modele.addObserver(this);

        Affichage.setText("Actions Speciales            Atefacts");
        panelJ.add(Affichage);
        panelJ.add(labelJ1);
        panelJ.add(labelJ2);
        panelJ.add(labelJ3);
        panelJ.add(labelJ4);

        this.add(panelJ);
        this.update();
    }

    public void update() {
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
        label.setText("Sable : "+j.get_nb_ActionSacSable()+"  Helico : "+j.get_nb_ActionHelico()+"            "+j.noms_artefacts_possession());

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



