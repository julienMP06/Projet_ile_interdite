import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class ControleurAsseche extends VueJoueurs implements KeyListener {

    /**
     * On a ici le contreleur du bouton asseche qui permet d'assecher une case en fonction de la touche
     * utilisée par le joueur
     */
    CModele modele;
    private JLabel label;
    private JPanel panelC;
    private JTextField EntrerX;
    private JTextField EntrerY;
    private JButton valider;
    private JCheckBox oui;

    private JButton validerEchange;

    private JTextField CleEchange;

    public ControleurAsseche(CModele modele, JLabel label, JPanel panelC,JTextField EntrerX,JTextField EntrerY,JButton valider, JCheckBox oui, JButton validerEchange, JTextField CleEchange) {
        super(modele);
        this.modele = modele;
        this.label = label;
        this.panelC = panelC;
        this.EntrerX = EntrerX;
        this.EntrerY = EntrerY;
        this.valider = valider;
        this.validerEchange = validerEchange;
        this.CleEchange = CleEchange;
        this.oui = oui;
    }

    public void keyPressed(KeyEvent e) {
        panelC.remove(EntrerX);
        panelC.remove(EntrerY);
        panelC.remove(valider);
        panelC.remove(oui);
        panelC.remove(CleEchange);
        panelC.remove(validerEchange);
        panelC.revalidate();
        panelC.repaint();
        if (modele.partie_perdue()) {
            label.setText("Tu ne peux plus jouer Recommence");
        } else {
            if (modele.getJ_actuel().getNb_act()>0){
                switch (e.getKeyCode()) {

                    case KeyEvent.VK_RIGHT:
                        int y = 0;
                        int x = 0;
                        // avoir les coordonnees du joueur qui joue
                        Case c = modele.getJ_actuel().getC();
                        x = c.getX();
                        y = c.getY();
                        if (modele.getCas(x + 1, y).etat == 1) {
                            modele.getCas(x + 1, y).setEtat(0);
                            modele.getJ_actuel().action_moins();
                            label.setText("Asseche Droite");
                        } else {
                            label.setText("Impossible !");
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        // avoir les coordonnees du joueur qui joue
                        c = modele.getJ_actuel().getC();
                        x = c.getX();
                        y = c.getY();
                        if (modele.getCas(x - 1, y).etat == 1) {
                            modele.getCas(x - 1, y).setEtat(0);
                            modele.getJ_actuel().action_moins();
                            label.setText("Asseche Gauche");
                        } else {
                            label.setText("Impossible !");
                        }
                        break;
                    case KeyEvent.VK_UP:
                        // avoir les coordonnees du joueur qui joue
                        c = modele.getJ_actuel().getC();
                        x = c.getX();
                        y = c.getY();
                        if (modele.getCas(x, y - 1).etat == 1) {
                            modele.getCas(x, y - 1).setEtat(0);
                            modele.getJ_actuel().action_moins();
                            label.setText("Asseche Haut");
                        } else {
                            label.setText("Impossible !");
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        // avoir les coordonnees du joueur qui joue
                        c = modele.getJ_actuel().getC();
                        x = c.getX();
                        y = c.getY();
                        if (modele.getCas(x, y + 1).etat == 1) {
                            modele.getCas(x, y + 1).setEtat(0);
                            modele.getJ_actuel().action_moins();
                            label.setText("Asseche Bas");
                        } else {
                            label.setText("Impossible !");
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        // avoir les coordonnees du joueur qui joue
                        c = modele.getJ_actuel().getC();
                        x = c.getX();
                        y = c.getY();
                        if (modele.getCas(x, y).etat == 1) {
                            modele.getCas(x, y).setEtat(0);
                            modele.getJ_actuel().action_moins();
                            label.setText("Asseche Actuelle");
                        } else {
                            label.setText("Impossible !");
                        }
                        break;
                }

            }
        }
        modele.MAJ();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
