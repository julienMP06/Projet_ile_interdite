import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CVue extends JFrame implements ActionListener {

    private JFrame frame;
    private VueGrille grille;
    private VueCommandes commandes;
    private VueJoueurs joueurs;

    private VueJoueurs2 joueurs2;

    JButton bouton = new JButton ("Jouer");
    GridLayout grid = new GridLayout(2, 2);
    private CModele modele;
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

        joueurs2 = new VueJoueurs2(modele);
        frame.add(joueurs2);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        /*this.modele = modele;
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container fene1 = getContentPane( );
        BorderLayout flow1 = new BorderLayout( );
        fene1.setLayout(flow1);

        JPanel pan1 = new JPanel( );
        pan1.add(bouton);
        fene1.add(pan1,BorderLayout.NORTH);

        bouton.addActionListener(this);
        setContentPane(fene1);
        fene1.setVisible(true);
*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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