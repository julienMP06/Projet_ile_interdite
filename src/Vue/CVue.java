package Vue;
import Modele.*;
import javax.swing.*;
import javax.swing.border.Border;
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
                        frameRegle.setResizable(false);
                        frameRegle.setTitle("Ile Interdite MAILLE-PAEZ KEMICHE Regle");
                        GridLayout gridRegle = new GridLayout(4,0);
                        JPanel pan = new JPanel();
                        //pan.setLayout(gridRegle);
                        BorderLayout bord = new BorderLayout();
                        frameRegle.setLayout(bord);

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