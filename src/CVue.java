import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CVue extends JFrame implements ActionListener {

    private JFrame frame;

    private JFrame frameMenu;
    private VueGrille grille;
    private VueCommandes commandes;
    private VueJoueurs joueurs;

    private VueJoueurs2 joueurs2;

    JButton bouton = new JButton ("Jouer");

    JButton boutonRegle = new JButton ("Regles");
    GridLayout grid = new GridLayout(2, 2);

    GridLayout gridMenu = new GridLayout(2, 0);
    private CModele modele;
    public CVue(CModele modele) {
        this.modele = modele;
        frameMenu = new JFrame();
        frameMenu.setResizable(false);
        frameMenu.setTitle("Ile Interdite MAILLE-PAEZ KEMICHE Menu");
        frameMenu.setSize(600,600);
        frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        BorderLayout flow1 = new BorderLayout();
        frameMenu.setLayout(flow1);

        JPanel pan1 = new JPanel( );
        pan1.setLayout(gridMenu);
        pan1.add(bouton);
        pan1.add(boutonRegle);
        frameMenu.add(pan1);

        bouton.addActionListener(this);
        frameMenu.setVisible(true);
        frameMenu.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            frameMenu.dispose();
            frame = new JFrame();
            frame.setResizable(false);
            frame.setTitle("Ile Interdite MAILLE-PAEZ KEMICHE Jeu");
            grid.setVgap(30);
            frame.setLayout(grid);

            grille = new VueGrille(modele);
            frame.add(grille);

            commandes = new VueCommandes(modele);
            frame.add(commandes);

            joueurs = new VueJoueurs(modele);
            frame.add(joueurs);

            joueurs2 = new VueJoueurs2(modele);
            frame.add(joueurs2);

            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
    }
}