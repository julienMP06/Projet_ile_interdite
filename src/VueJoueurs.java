import javax.swing.*;
import java.awt.*;

public class VueJoueurs extends JPanel{

    private static CModele modele;

    private JPanel panel = new JPanel();
    GridLayout grid = new GridLayout(4, 4);

    JLabel label = new JLabel();
    JLabel labeln = new JLabel();
    JLabel labelx = new JLabel();
    JLabel labela = new JLabel();

    public VueJoueurs (CModele modele){
        this.modele=modele;
        setLayout(new BorderLayout());
        panel.setLayout(grid);
        JoueurPanneau(panel);

        //this.add(panel);
    }
    public void JoueurPanneau(JPanel panel){
        String l = modele.getJoueurs().get(0).getNom_joueur();
        int n = modele.getJoueurs().get(0).getNb_cle();
        int x = modele.getJoueurs().get(0).getArtefacts();
        int a = modele.getJoueurs().get(0).getNb_act();
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

    public JPanel Get_JT(){
        return this.panel;
    }
}

