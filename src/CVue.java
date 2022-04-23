import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CVue extends JFrame implements ActionListener {

    private JFrame frame;

    private JFrame frameMenu;

    private JFrame frameRegle;
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

        gridMenu.setVgap(30);
        CardLayout flow1 = new CardLayout(200,200);
        frameMenu.setLayout(flow1);

        JPanel pan1 = new JPanel( );
        pan1.setLayout(gridMenu);

        pan1.add(bouton);
        bouton.addActionListener(this);

        pan1.add(boutonRegle);
        boutonRegle.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frameMenu.dispose();
                        frameRegle = new JFrame();
                        frameRegle.setSize(600,600);
                        frameRegle.setResizable(false);
                        frameRegle.setTitle("Ile Interdite MAILLE-PAEZ KEMICHE Regle");
                        grid.setVgap(30);
                        frameRegle.setLayout(grid);

                        JTextArea explication = new JTextArea("Vous incarnez ici 4 aventuriers. Sur cette ile vous allez devoir ");
                        explication.setEditable(false);

                        JButton buttonRetour = new JButton("<-");
                        buttonRetour.addActionListener(
                                new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        frameMenu.setVisible(true);
                                        frameRegle.dispose();
                                    }
                                }
                        );


                        frameRegle.add(explication);
                        frameRegle.add(buttonRetour);
                        frameRegle.pack();
                        frameRegle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frameRegle.setVisible(true);
                        frameRegle.setLocationRelativeTo(null);
                    }
                }
        );

        frameMenu.add(pan1,BorderLayout.CENTER);


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