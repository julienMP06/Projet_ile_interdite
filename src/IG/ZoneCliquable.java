package IG;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class ZoneCliquable extends JPanel implements MouseListener {

    private Texte texte;

    public ZoneCliquable(String texte, int x, int y) {
        this(x, y);
        this.texte = new Texte(texte);
        this.add(this.texte);
    }

    public ZoneCliquable(int x, int y) {
        setPreferredSize(new Dimension(x,y));
        addMouseListener(this);
        setBackground(Color.WHITE);
    }

    public abstract void clicGauche();
    public abstract void clicDroit();

    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            this.clicDroit();
        } else {
            this.clicGauche();
        }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
