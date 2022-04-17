import javax.swing.*;
import java.awt.*;
public class VueJoueurs extends JPanel implements Observer{
    private CModele modele;

    GridLayout grid = new GridLayout(4, 0);

    private JLabel labelJ1 = new JLabel();
    private JLabel labelJ2 = new JLabel();
    private JLabel labelJ3 = new JLabel();
    private JLabel labelJ4 = new JLabel();

    private JPanel panelJ = new JPanel();

    public VueJoueurs (CModele modele){
        panelJ.setLayout(grid);
        this.modele=modele;
        grid.setVgap(50);
        modele.addObserver(this);

        panelJ.add(labelJ1);
        panelJ.add(labelJ2);
        panelJ.add(labelJ3);
        panelJ.add(labelJ4);
        this.add(panelJ);
        this.update();
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        paint(g,modele.getJoueurs().get(0),labelJ1);
        paint(g,modele.getJoueurs().get(1),labelJ2);
        paint(g,modele.getJoueurs().get(2),labelJ3);
        paint(g,modele.getJoueurs().get(3),labelJ4);
    }

    private void paint(Graphics g, Joueur j, JLabel label) {
        label.setText("%s  Action :  %d  Clé :  %d   Artefacts : %d".formatted(j.getNom_joueur(),j.getNb_act(),j.getNb_cleE(),j.getArtefacts()));
    }
}


