import javax.swing.*;
import java.awt.*;

public class CVue {

    private JFrame frame;
    private VueGrille grille;
    private VueCommandes commandes;


    public CVue(CModele modele) {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Ile Interdite MAILLE KEMICHE");
        frame.setLayout(new FlowLayout());
        grille = new VueGrille(modele);
        frame.add(grille);
        commandes = new VueCommandes(modele);
        frame.add(commandes);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
