import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

interface Observer {
    public void update();
}

abstract class Observable {
    private ArrayList<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

public class IleInterdite {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            CModele modele = new CModele();
            CVue vue = new CVue(modele);
            modele.getCas(1, 1).etat = 3;
            for (int i = 0; i < 60; i++) {
                for (int j = 0; j < 40; j++) {
                    System.out.println(modele.getCas(i, j).etat);
                }
            }
        });
    }
}

class CModele extends Observable {

    public static final int HAUTEUR = 40, LARGEUR = 60;
    private Case[][] plateau;

    public CModele() {
        plateau = new Case[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                plateau[i][j] = new Case(this, i, j);
            }
        }
        init();
    }

    public void init() {
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                plateau[i][j].etat = 0;
            }
        }
    }

    public Case getCas(int x, int y) {
        return plateau[x][y];
    }

    public void MAJ() {
        notifyObservers();
    }
}

class Case {
    private CModele modele;
    protected int etat;
    private final int x, y;

    public Case(CModele modele, int x, int y) {
        this.modele = modele;
        this.etat = 0;
        this.x = x; this.y = y;
    }

    public int GetEtat(){
        return this.etat;
    }
}

class CVue {

    private JFrame frame;
    private VueGrille grille;
    private VueCommandes commandes;


    public CVue(CModele modele) {

        frame = new JFrame();
        frame.setTitle("Ile Interdite MAILLE KEMICHE");

        frame.setLayout(new FlowLayout());

        grille = new VueGrille(modele);
        frame.add(grille);
        commandes = new VueCommandes(modele);
        frame.add(commandes);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class VueGrille extends JPanel implements Observer {
    private CModele modele;
    private final static int TAILLE =  12;

    public VueGrille(CModele modele) {
        this.modele = modele;
        modele.addObserver(this);
        Dimension dim = new Dimension(TAILLE*CModele.LARGEUR,
                TAILLE*CModele.HAUTEUR);
        this.setPreferredSize(dim);
    }

    public void update() { repaint(); }

    public void paintComponent(Graphics g) {
        super.repaint();
        for(int i=1; i<=CModele.LARGEUR; i++) {
            for(int j=1; j<=CModele.HAUTEUR; j++) {
                paint(g, modele.getCas(i, j), (i-1)*TAILLE, (j-1)*TAILLE);
            }
        }
    }
    private void paint(Graphics g, Case c, int x, int y) {
        g.setColor(Color.WHITE);
        if (c.GetEtat() == 1) {
            g.setColor(Color.CYAN);
        }
        if (c.GetEtat() == 2) {
            g.setColor(Color.BLUE);
        }
        if (c.GetEtat() == 3) {
            g.setColor(Color.BLACK);
        }
        g.fillRect(x, y, TAILLE, TAILLE);
    }

    public static int GetTaille(){
        return TAILLE;
    }
}

class VueCommandes extends JPanel {
    private CModele modele;
    public VueCommandes(CModele modele) {
        this.modele = modele;
        JButton boutonAvance = new JButton("Fin De Tour");
        this.add(boutonAvance);
        Controleur ctrl = new Controleur(modele);
        boutonAvance.addActionListener(ctrl);

        /*Test De Déplacement*/
        JButton button = new JButton("Déplacement");
        this.add(button);
        button.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                Point p = button.getLocation();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        int y = 0;
                        int x = 0;
                        for (int i = 1; i <= CModele.LARGEUR; i++) {
                            for (int j = 1; j <= CModele.HAUTEUR; j++) {
                                if (modele.getCas(i, j).etat == 3) {
                                    x = i;
                                    y = j;
                                }
                            }
                        }
                        modele.getCas(x, y).etat = 0;
                        modele.getCas(x+1, y).etat = 3;
                        break;
                    case KeyEvent.VK_LEFT:
                        y = 0;
                        x = 0;
                        for (int i = 1; i <= CModele.LARGEUR; i++) {
                            for (int j = 1; j <= CModele.HAUTEUR; j++) {
                                if (modele.getCas(i, j).etat == 3) {
                                    x = i;
                                    y = j;
                                }
                            }
                        }
                        modele.getCas(x, y).etat = 0;
                        modele.getCas(x-1, y).etat = 3;
                        break;
                    case KeyEvent.VK_UP:
                        y = 0;
                        x = 0;
                        for (int i = 1; i <= CModele.LARGEUR; i++) {
                            for (int j = 1; j <= CModele.HAUTEUR; j++) {
                                if (modele.getCas(i, j).etat == 3) {
                                    x = i;
                                    y = j;
                                }
                            }
                        }
                        modele.getCas(x, y).etat = 0;
                        modele.getCas(x, y-1).etat = 3;
                        break;
                    case KeyEvent.VK_DOWN:
                        y = 0;
                        x = 0;
                        for (int i = 1; i <= CModele.LARGEUR; i++) {
                            for (int j = 1; j <= CModele.HAUTEUR; j++) {
                                if (modele.getCas(i, j).etat == 3) {
                                    x = i;
                                    y = j;
                                }
                            }
                        }
                        modele.getCas(x, y).etat = 0;
                        modele.getCas(x, y + 1).etat = 3;
                        break;
                }
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
    }
}

class Controleur implements ActionListener {
    CModele modele;
    public Controleur(CModele modele) { this.modele = modele; }
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * (CModele.LARGEUR)+1);
            int y = (int) (Math.random() * (CModele.HAUTEUR)+1);
            System.out.println(x);
            System.out.println(y);
            if (modele.getCas(x,y).etat == 0){
                modele.getCas(x,y).etat = 1;
            }
            else if (modele.getCas(x,y).etat == 1){
                modele.getCas(x,y).etat = 2;
            }
        }
        modele.MAJ();
    }
}
