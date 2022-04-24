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
                        frameMenu.setLayout(null);
                        frameRegle.setSize(800,1200);
                        frameRegle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frameRegle.setTitle("Ile Interdite MAILLE-PAEZ KEMICHE Regle");

                        JTextArea Titre = new JTextArea(" Regles : ");
                        Titre.setFont(new Font("sansserif", Font.BOLD, 20));
                        Titre.setForeground(Color.WHITE);
                        Titre.setOpaque(true);
                        Titre.setBackground(new Color(100,100,100,150));
                        JTextArea explication = new JTextArea("\n Vous incarnez ici 4 aventuriers. Sur cette ile vous allez \ndevoir recuperer les 4 artefacts presents sur la carte puis " +
                                "retourner a l'heliport pour pouvoir vous echapper. \nMais attention car l'ile sombre dans les profondeurs de l'ocean." +
                                "\n\n Pour parvenir a votre objectif vous avez a votre disposition plusieurs atouts : " +
                                "\n             - Vous pourrez utiliser le bouton 'Asseche', pour assecher une case \nadjacente a votre personnage et sa case actuelle." +
                                "\n             - Le bouton 'Recuperer' vous permettra simplement  de recuperer l'artefact \nqui se situe sur votre case, si vous avec les cles necessaires." +
                                "\n             - Le bouton 'Echange Cle' permet justement d'echanger des cle \navec le joueurs qui est sur votre case." +
                                "\n             - A la fin de votre partie vous pouvez utilliser 'S'envoler' \npour terminer et valider votre partie" +
                                "\n             - Vous disposez d'actions speciales que vous pouvez obtenir tout au long de la partie\n");
                        explication.setFont(new Font("sansserif", Font.BOLD, 17));
                        explication.setForeground(Color.WHITE);
                        explication.setOpaque(true);
                        explication.setBackground(new Color(100,100,100,150));
                        JTextArea Touches = new JTextArea(" Les Touches :");
                        Touches.setFont(new Font("sansserif", Font.BOLD, 20));
                        Touches.setForeground(Color.WHITE);
                        Touches.setOpaque(true);
                        Touches.setBackground(new Color(100,100,100,150));
                        JTextArea TouchesExpl = new JTextArea("     - Deplacement : Fleches du clavier\n     - Asseche : Fleches du clavier pour les directions \net espace pour case actuel" +
                                "\n     - Donner cle : Entrer le nom de la cle et appuyer sur valider\n     - Sac de Sable : Entrer les coordonnees de la case et valider" +
                                "\n     - Helico : - Seul : Entrer les coordonnees de la case et valider\n          " +
                                "           - A plusieurs : Entrer les coordonnees de la case cocher \n'Emporter' pour" +
                                "emporter les autres joueurs de la case et valider");
                        TouchesExpl.setFont(new Font("sansserif", Font.BOLD, 18));
                        TouchesExpl.setForeground(Color.WHITE);
                        TouchesExpl.setOpaque(true);
                        TouchesExpl.setBackground(new Color(100,100,100,150));

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

                        frameRegle.setContentPane(new JPanel() {
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
                                Titre.setBounds(360,360,100,30);
                                explication.setBounds(0,390,800,340);
                                Touches.setBounds(340,740,150,30);
                                TouchesExpl.setBounds(0,770,800,300);

                                buttonRetour.setBounds(10,10,50,30);
                                frameRegle.add(Titre);
                                frameRegle.add(explication);
                                frameRegle.add(Touches);
                                frameRegle.add(TouchesExpl);
                                frameRegle.add(buttonRetour,BorderLayout.NORTH);
                            }
                        });
                        frameRegle.setLocationRelativeTo(null);
                        frameRegle.setVisible(true);
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
        frame.setLayout(null);
        frame.setSize(600,600);
        //grid.setVgap(30);
        frame.setTitle("Ile Interdite MAILLE-PAEZ KEMICHE Jeu");

        frame.setContentPane(new JPanel() {
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
                g.drawImage(icon, -180, -620, 820, 1240, this);
                grille.setBounds(0,0,300,300);
                commandes.setBackground(new Color(100,100,100,150));
                commandes.setBounds(300,0,580,300);
                joueurs.setBackground(new Color(100,100,100,150));
                joueurs.setBounds(0,300,300,600);
                joueurs2.setBackground(new Color(100,100,100,150));
                joueurs2.setBounds(300,300,600,600);
                frame.add(grille);
                frame.add(commandes);
                frame.add(joueurs);
                frame.add(joueurs2);
            }
        });

        grille = new VueGrille(modele);

        commandes = new VueCommandes(modele,frameMenu,frame);

        joueurs = new VueJoueurs(modele);

        joueurs2 = new VueJoueurs2(modele);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}