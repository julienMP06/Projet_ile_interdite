import javax.swing.*;
import java.awt.*;

public class CVue {

    private JFrame frame;
    private VueGrille grille;
    private VueCommandes commandes;
    private VueJoueurs joueurs;

    GridLayout grid = new GridLayout(2, 2);
    public CVue(CModele modele) {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Ile Interdite MAILLE-PAEZ KEMICHE");
        grid.setVgap(30);
        frame.setLayout(grid);

        grille = new VueGrille(modele);
        frame.add(grille);

        commandes = new VueCommandes(modele);
        frame.add(commandes);

        joueurs = new VueJoueurs(modele);
        frame.add(joueurs);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}