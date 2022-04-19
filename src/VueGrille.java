import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

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
        ArrayList<Joueur> l = c.get_joueurs();
        if (c.contient_joueur()) {
            switch (l.size()){
                case 1:
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(x, y, TAILLE / 2, TAILLE / 2);
                    g.setColor(Color.BLACK);
                    g.setFont(g.getFont().deriveFont(15f));
                    g.drawString(l.get(0).getNom_joueur(), x + 10, y + 15);
                    break;
                case 2:
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(x, y, TAILLE / 2, TAILLE / 2);
                    g.fillOval(x+25, y, TAILLE / 2, TAILLE / 2);
                    g.setColor(Color.BLACK);
                    g.setFont(g.getFont().deriveFont(15f));
                    g.drawString(l.get(0).getNom_joueur(), x + 10, y + 15);
                    g.drawString(l.get(1).getNom_joueur(), x + 35, y + 15);
                    break;
                case 3:
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(x, y, TAILLE / 2, TAILLE / 2);
                    g.fillOval(x, y+25, TAILLE / 2, TAILLE / 2);
                    g.fillOval(x+25, y, TAILLE / 2, TAILLE / 2);
                    g.setColor(Color.BLACK);
                    g.setFont(g.getFont().deriveFont(15f));
                    g.drawString(l.get(0).getNom_joueur(), x + 10, y + 15);
                    g.drawString(l.get(1).getNom_joueur(), x + 35, y + 15);
                    g.drawString(l.get(2).getNom_joueur(), x + 10, y + 40);
                    break;
                case 4:
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(x, y, TAILLE / 2, TAILLE / 2);
                    g.fillOval(x, y+25, TAILLE / 2, TAILLE / 2);
                    g.fillOval(x+25, y, TAILLE / 2, TAILLE / 2);
                    g.fillOval(x+25, y+25, TAILLE / 2, TAILLE / 2);
                    g.setColor(Color.BLACK);
                    g.setFont(g.getFont().deriveFont(15f));
                    g.drawString(l.get(0).getNom_joueur(), x + 10, y + 15);
                    g.drawString(l.get(1).getNom_joueur(), x + 35, y + 15);
                    g.drawString(l.get(2).getNom_joueur(), x + 10, y + 40);
                    g.drawString(l.get(3).getNom_joueur(), x + 35, y + 40);
                    break;
            }
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
        if (c.contient_heleco()) {
            g.setColor(Color.RED);
            g.fillOval(x, y, TAILLE, TAILLE);
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(25f));
            g.drawString("H",x+16,y+35);
        }
        if (c.contient_artefact() && c.get_artefact_nom()=="Air") {
            g.setFont(g.getFont().deriveFont(20f));
            g.setColor(Color.WHITE);
            g.fillOval(x, y, TAILLE, TAILLE);
            g.setColor(Color.PINK);
            g.drawString("Air",x+9,y+33);
        }
        if (c.contient_artefact() && c.get_artefact_nom()=="Feu") {
            g.setFont(g.getFont().deriveFont(20f));
            g.setColor(Color.RED);
            g.fillOval(x, y, TAILLE, TAILLE);
            g.setColor(Color.ORANGE);
            g.drawString("Feu",x+7,y+33);
        }
        if (c.contient_artefact() && c.get_artefact_nom()=="Terre") {
            g.setFont(g.getFont().deriveFont(18f));
            g.setColor(Color.ORANGE);
            g.fillOval(x, y, TAILLE, TAILLE);
            g.setColor(Color.yellow);
            g.drawString("Terre",x,y+32);

        }
        if (c.contient_artefact() && c.get_artefact_nom()=="Eau") {
            g.setFont(g.getFont().deriveFont(20f));
            g.setColor(Color.CYAN);
            g.fillOval(x, y, TAILLE, TAILLE);
            g.setColor(Color.WHITE);
            g.drawString("Eau",x+7,y+33);
        }
    }
}