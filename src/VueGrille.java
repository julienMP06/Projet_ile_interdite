import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

class VueGrille extends JPanel implements Observer {
    private CModele modele;
    private final static int TAILLE = 50;

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
        Fond(g,c,x,y);
        if (c.contient_joueur()) {
            g.setColor(Color.DARK_GRAY); // apres d'autres coulleurs pour chaque Jr
            g.fillOval(x, y, TAILLE, TAILLE);
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(25f));
            g.drawString("j",x+23,y+30);
        } else {
            Fond(g,c,x,y);
        }

    }

    public void Fond(Graphics g, Case c, int x, int y) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, TAILLE, TAILLE);
        if (c.GetEtat() == 1) {
            g.setColor(Color.CYAN);
            g.fillRect(x, y, TAILLE, TAILLE);
        }
        if (c.GetEtat() == 2) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, TAILLE, TAILLE);
        }
        if (c.GetEtat() == 10) {
            g.setColor(Color.RED);
            g.fillRect(x, y, TAILLE, TAILLE);
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(25f));
            g.drawString("H",x+16,y+35);
        }
        if (c.GetEtat() == 21) {
            g.setColor(Color.PINK);
            g.fillRect(x, y, TAILLE, TAILLE);
        }
        if (c.GetEtat() == 22) {
            g.setColor(Color.RED);
            g.fillRect(x, y, TAILLE, TAILLE);
        }
        if (c.GetEtat() == 23) {
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, TAILLE, TAILLE);
        }
        if (c.GetEtat() == 24) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, TAILLE, TAILLE);
        }
    }
}