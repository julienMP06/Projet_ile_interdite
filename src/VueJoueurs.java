import javax.swing.*;
import java.awt.*;

public class VueJoueurs extends JPanel{

    private CModele modele;

    GridLayout grid = new GridLayout(4, 3);

    public VueJoueurs (CModele modele){

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(grid);
        this.modele=modele;

        for(int i =0; i < 4;i++) {
            JoueurPanneau(i, panel);
        }
    }

    public void JoueurPanneau(int i,JPanel panel){
        String l = modele.getJoueurs().get(i).getNom_joueur();
        int n = modele.getJoueurs().get(i).getNb_cle();
        int x = modele.getJoueurs().get(i).getNb_act();
        JLabel label = new JLabel();
        JLabel labeln = new JLabel();
        JLabel labelx = new JLabel();
        label.setText(l);
        labeln.setText("clé : "+n);
        labelx.setText("Arteacts : "+x);
        panel.add(label);
        panel.add(labeln);
        panel.add(labelx);
        this.add(panel);
    }
}

