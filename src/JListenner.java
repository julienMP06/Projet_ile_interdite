import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class JListenner extends KeyAdapter {
    protected CModele modele;
    protected JButton button;
    Controleur ctrl;

    public JListenner(CModele modele, JButton btn, Controleur ctrl) {
        this.modele = modele;
        this.button = btn;
        this.ctrl = ctrl;
        //this.frame = (JFrame) frame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Point p = button.getLocation();
        if (modele.getJ_actuel().getNb_act() > 0) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_RIGHT:
                    int y = 0;
                    int x = 0;
                    // avoir les coordonnees du joueur qui joue
                    Case c=modele.getJ_actuel().getC();
                    x=c.getX();
                    y=c.getY();
                       
                    // pour eviter les effet de bord
                    if (x + 1 < CModele.LARGEUR) {
                    	// on supp le joueur actuel de la case courante
                        modele.getCas(x, y).supprimer_joueur(modele.getJ_actuel());
                    	// on ajoute le joueur actuel a la case de droite
                        modele.getCas(x + 1, y).ajouter_joueur(modele.getJ_actuel());
                        
                    }
                    break;

                case KeyEvent.VK_LEFT:
                	 c=modele.getJ_actuel().getC();
                     x=c.getX();
                     y=c.getY();
                    if (x - 1 > 0) {
                    	modele.getCas(x, y).supprimer_joueur(modele.getJ_actuel());
                        modele.getCas(x - 1, y).ajouter_joueur(modele.getJ_actuel());
                        
                    }
                    break;

                case KeyEvent.VK_UP:
                	 c=modele.getJ_actuel().getC();
                     x=c.getX();
                     y=c.getY();
                                if (y - 1 > 0) {
                                	modele.getCas(x, y).supprimer_joueur(modele.getJ_actuel());
                                    modele.getCas(x, y - 1).ajouter_joueur(modele.getJ_actuel());
                                    
                                }
                                break;
                        

                case KeyEvent.VK_DOWN:
                    
                    c=modele.getJ_actuel().getC();
                    x=c.getX();
                    y=c.getY();
                    modele.getCas(x, y).supprimer_joueur(modele.getJ_actuel());
                    modele.getCas(x, y + 1).ajouter_joueur(modele.getJ_actuel());
                    
            }
            //le nombre d action du joueur se decremente apres avoir deplacer
            modele.getJ_actuel().action_moins();  
            modele.MAJ();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {

    }
}
