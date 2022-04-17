import javax.swing.*;
import java.awt.*;

public class VueJoueurs extends JPanel{

    private static CModele modele;

    private int x =0;
    private JPanel panel = new JPanel();
    GridLayout grid = new GridLayout(4, 0);

    JLabel label = new JLabel();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();

    public VueJoueurs (CModele modele){
        this.modele=modele;
        setLayout(new BorderLayout());
        //panel.setLayout(grid);
        JoueurPanneau(panel);
    }
    public void JoueurPanneau(JPanel panel){
        x = x+1;
        label.setText("%s   clé : %s   Artefacts : %d   Action : %d".formatted(modele.getJoueurs().get(0).getNom_joueur(), modele.getJoueurs().get(0).getNb_cle(), modele.getJoueurs().get(0).getArtefacts(), modele.getJoueurs().get(0).getNb_act()));
        label1.setText(modele.getJoueurs().get(1).getNom_joueur()+"   clé : "+modele.getJoueurs().get(1).getNb_cle()+"   Artefacts : "+modele.getJoueurs().get(1).getArtefacts()+"   Action : "+modele.getJoueurs().get(1).getNb_act());
        label2.setText(modele.getJoueurs().get(2).getNom_joueur()+"   clé : "+modele.getJoueurs().get(2).getNb_cle()+"   Artefacts : "+modele.getJoueurs().get(2).getArtefacts()+"   Action : "+modele.getJoueurs().get(2).getNb_act());
        label3.setText(modele.getJoueurs().get(3).getNom_joueur()+"   clé : "+modele.getJoueurs().get(3).getNb_cle()+"   Artefacts : "+modele.getJoueurs().get(3).getArtefacts()+"   Action : "+modele.getJoueurs().get(3).getNb_act());
        label3.setText(String.valueOf(x));
        System.out.println(x);
        panel.add(label);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        this.add(label3);
    }
    public void MajJpanel(){
        JoueurPanneau(panel);
    }
}


