import javax.swing.*;
import java.awt.*;
public class VueJoueurs extends JPanel{
    private static CModele modele;
    private JPanel panelJ = new JPanel();

    private int x = 0;
    GridLayout grid = new GridLayout(5, 0);

    private JLabel label = new JLabel();
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel label3 = new JLabel();
    private JLabel label4 = new JLabel();

    public VueJoueurs (CModele modele){
        this.modele=modele;
        setLayout(new BorderLayout());
        panelJ.setLayout(grid);
        JoueurPanneau(panelJ);
    }
    public void JoueurPanneau(JPanel p){
        x = x +1;
        label.setText("%s   clé : %s   Artefacts : %d   Action : %d".formatted(modele.getJoueurs().get(0).getNom_joueur(), modele.getJoueurs().get(0).getNb_cleE(), modele.getJoueurs().get(0).getArtefacts(), modele.getJoueurs().get(0).getNb_act()));
        label1.setText(modele.getJoueurs().get(1).getNom_joueur()+"   clé : "+modele.getJoueurs().get(1).getNb_cleE()+"   Artefacts : "+modele.getJoueurs().get(1).getArtefacts()+"   Action : "+modele.getJoueurs().get(1).getNb_act());
        label2.setText(modele.getJoueurs().get(2).getNom_joueur()+"   clé : "+modele.getJoueurs().get(2).getNb_cleE()+"   Artefacts : "+modele.getJoueurs().get(2).getArtefacts()+"   Action : "+modele.getJoueurs().get(2).getNb_act());
        label3.setText(modele.getJoueurs().get(3).getNom_joueur()+"   clé : "+modele.getJoueurs().get(3).getNb_cleE()+"   Artefacts : "+modele.getJoueurs().get(3).getArtefacts()+"   Action : "+modele.getJoueurs().get(3).getNb_act());
        label4.setText(String.valueOf(x));
        p.add(label4);
        p.add(label);
        p.add(label1);
        p.add(label2);
        p.add(label3);
        p.add(label4);
        this.add(p);
        System.out.println(x);
    }
    public void MajJpanel(){
        JoueurPanneau(panelJ);
    }
}


