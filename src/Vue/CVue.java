package Vue;
import Modele.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        frameMenu.setLayout(null);
        frameMenu.setTitle("Ile Interdite MAILLE-PAEZ KEMICHE Menu");
        frameMenu.setSize(800,1200);
        frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bouton.addActionListener(this);
        boutonRegle.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frameMenu.dispose();
                        frameRegle = new JFrame();
                        frameRegle.setResizable(false);
                        frameRegle.setTitle("Ile Interdite MAILLE-PAEZ KEMICHE Regle");
                        GridLayout gridRegle = new GridLayout(4,0);
                        JPanel pan = new JPanel();

                        JTextArea Titre = new JTextArea(" Regles : ");
                        Titre.setFont(new Font("sansserif", Font.BOLD, 16));
                        JTextArea explication = new JTextArea("\n Vous incarnez ici 4 aventuriers. Sur cette ile vous allez devoir recuperer les 4 artefacts presents sur la carte puis\n " +
                                "retourner a l'heliport pour pouvoir vous echapper. Mais attention car l'ile sombre dans les profondeurs de l'ocean." +
                                "\n\n Pour parvenir a votre objectif vous avez a votre disposition plusieurs atouts : " +
                                "\n             - Vous pourrez utiliser le bouton 'Asseche', pour assecher une case adjacente a votre personnage et sa\n case actuelle." +
                                "\n             - Le bouton 'Recuperer' vous permettra simplement  de recuperer l'artefact qui se situe sur votre case,\n si vous avec les cles necessaires." +
                                "\n             - Le bouton 'Echange Cle' permet justement d'echanger des cle avec le joueurs qui est sur votre case." +
                                "\n             - A la fin de votre partie vous pouvez utilliser 'S'envoler' pour terminer et valider votre partie" +
                                "\n             - Vous disposez d'actions speciales que vous pouvez obtenir tout au long de la partie\n");
                        explication.setFont(new Font("sansserif", Font.BOLD, 12));
                        JTextArea Touches = new JTextArea(" Les Touches :");
                        Touches.setFont(new Font("sansserif", Font.BOLD, 16));
                        JTextArea TouchesExpl = new JTextArea("     - Deplacement : Fleches du clavier\n     - Asseche : Fleches du clavier pour les directions et espace pour case actuel" +
                                "\n     - Donner cle : Entrer le nom de la cle et appuyer sur valider\n     - Sac de Sable : Entrer les coordonnees de la case et valider" +
                                "\n     - Helico : - Seul : Entrer les coordonnees de la case et valider\n          " +
                                "          - A plusieurs : Entrer les coordonnees de la case cocher 'Emporter' pour" +
                                "emporter les \n autres joueurs de la case et valider");
                        TouchesExpl.setFont(new Font("sansserif", Font.BOLD, 12));

                        Titre.setEditable(false);
                        explication.setEditable(false);
                        Touches.setEditable(false);
                        TouchesExpl.setEditable(false);

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
                        pan.add(Titre);
                        pan.add(explication);
                        pan.add(Touches);
                        pan.add(TouchesExpl);
                        frameRegle.add(pan,BorderLayout.CENTER);
                        frameRegle.add(buttonRetour,BorderLayout.EAST);
                        frameRegle.pack();
                        frameRegle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frameRegle.setVisible(true);
                        frameRegle.setLocationRelativeTo(null);
                    }
                }
        );

        frameMenu.setContentPane(new JPanel() {
            BufferedImage icon;

            {
                try {
                    icon = ImageIO.read(new File("L_ile_interdite_face_HD.jpg"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(icon, -20, -20, 900, 1200, this);
                bouton.setBounds(100,650,150,50);
                boutonRegle.setBounds(100,750,150,50);
                frameMenu.add(bouton);
                frameMenu.add(boutonRegle);
            }
        });



        frameMenu.setLocationRelativeTo(null);
        frameMenu.setVisible(true);

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

            commandes = new VueCommandes(modele,frameMenu,frame);
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