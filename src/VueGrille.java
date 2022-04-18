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
            System.out.println("Y"+c.getY());
            System.out.println("X"+c.getX());
            int p = c.getJoueur();
            System.out.println(p);
            //if(c.contient_joueur1()) {
                g.setColor(Color.DARK_GRAY);
                g.fillOval(x, y, TAILLE / 2, TAILLE / 2);
                g.setColor(Color.BLACK);
                g.setFont(g.getFont().deriveFont(15f));
                g.drawString("j1", x + 10, y + 15);
            /*}else if(c.contient_joueur2()) {
                g.setColor(Color.DARK_GRAY);
                g.fillOval(x, y+25, TAILLE / 2, TAILLE / 2);
                g.setColor(Color.BLACK);
                g.setFont(g.getFont().deriveFont(15f));
                g.drawString("j2", x + 10, y + 15);
            }else if(c.contient_joueur3()) {
                g.setColor(Color.DARK_GRAY);
                g.fillOval(x+25, y, TAILLE / 2, TAILLE / 2);
                g.setColor(Color.BLACK);
                g.setFont(g.getFont().deriveFont(15f));
                g.drawString("j2", x + 10, y + 15);
            }else if(c.contient_joueur4()) {
                g.setColor(Color.DARK_GRAY);
                g.fillOval(x+25, y+25, TAILLE / 2, TAILLE / 2);
                g.setColor(Color.BLACK);
                g.setFont(g.getFont().deriveFont(15f));
                g.drawString("j2", x + 10, y + 15);
            }*/
        } else {
            g.setFont(g.getFont().deriveFont(25f));
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
            g.setColor(Color.GREEN);
            g.fillRect(x, y, TAILLE, TAILLE);
            g.setColor(Color.RED);
            g.fillOval(x, y, TAILLE, TAILLE);
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(25f));
            g.drawString("H",x+16,y+35);
        }
        if (c.GetEtat() == 21) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, TAILLE, TAILLE);
            g.setColor(Color.PINK);
            g.drawString("气",x+11,y+35);
        }
        if (c.GetEtat() == 22) {
            g.setColor(Color.RED);
            g.fillRect(x, y, TAILLE, TAILLE);
            g.setColor(Color.ORANGE);
            g.drawString("火",x+11,y+35);
        }
        if (c.GetEtat() == 23) {
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, TAILLE, TAILLE);
            g.setColor(Color.yellow);
            g.drawString("地",x+11,y+35);

        }
        if (c.GetEtat() == 24) {
            g.setColor(Color.CYAN);
            g.fillRect(x, y, TAILLE, TAILLE);
            g.setColor(Color.WHITE);
            g.drawString("水",x+11,y+35);
        }
    }
}