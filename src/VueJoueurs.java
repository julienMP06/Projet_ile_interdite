import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VueJoueurs extends JPanel{

    private CModele modele;

    private JPanel panel = new JPanel();
    GridLayout grid = new GridLayout(4, 4);

    public VueJoueurs (CModele modele){

        setLayout(new BorderLayout());

        panel.setLayout(grid);
        this.modele=modele;
        updateJoueur();
    }
    public void JoueurPanneau(int i,JPanel panel){
        String l = modele.getJoueurs().get(i).getNom_joueur();
        int n = modele.getJoueurs().get(i).getNb_cle();
        int x = modele.getJoueurs().get(i).getArtefacts();
        int a = modele.getJoueurs().get(i).getNb_act();
        JLabel label = new JLabel();
        JLabel labeln = new JLabel();
        JLabel labelx = new JLabel();
        JLabel labela = new JLabel();
        label.setText(l);
        labeln.setText("clé : "+n);
        labelx.setText("Arteacts : "+x);
        labela.setText("    Action : "+a);
        panel.add(label);
        panel.add(labeln);
        panel.add(labelx);
        panel.add(labela);
        this.add(panel);
    }

    public void updateJoueur(){
        for(int i =0; i < 4;i++) {
            JoueurPanneau(i, panel);
        }
    }
}

