import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

class VueGrille extends JPanel implements Observer {
    private CModele modele;
    private final static int TAILLE = 12;

    public VueGrille(CModele modele) {
        this.modele = modele;
        modele.addObserver(this);
        Dimension dim = new Dimension(TAILLE * CModele.LARGEUR,
                TAILLE * CModele.HAUTEUR);
        this.setPreferredSize(dim);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for (int i = 1; i <= CModele.LARGEUR; i++) {
            for (int j = 1; j <= CModele.HAUTEUR; j++) {
                paint(g, modele.getCas(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
            }
        }
    }

    private void paint(Graphics g, Case c, int x, int y) {
        MajColor(g, c, x, y);
    }

    public static int GetTaille() {
        return TAILLE;
    }

    private void MajColor(Graphics g, Case c, int x, int y) {
        if (c.contient_joueur()) {
            g.setColor(Color.BLACK); // apres d'autres coulleurs pour chaque Jr
            
        } else {
            g.setColor(Color.WHITE);
            if (c.GetEtat() == 1) {
                g.setColor(Color.CYAN);
            }
            if (c.GetEtat() == 2) {
                g.setColor(Color.BLUE);
            }
        }
        g.fillRect(x, y, TAILLE, TAILLE);
    }
}