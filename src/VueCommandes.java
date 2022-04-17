import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.Calendar;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

class VueCommandes extends JPanel {
    private CModele modele;
    GridLayout grid = new GridLayout(3, 0);
    public VueCommandes(CModele modele) {

        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        panel.setLayout(grid);
        grid.setHgap(10);
        grid.setVgap(15);
        this.modele=modele;

        JButton boutonAvance = new JButton("Fin De Tour");
        panel.add(boutonAvance);

        Controleur ctrl = new Controleur(modele);
        boutonAvance.addActionListener(ctrl);

        JButton button = new JButton("Déplacement");
        panel.add(button);

        JButton buttonAsseche = new JButton("Assécher");

        buttonAsseche.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyPressed(KeyEvent e) {
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
                                }
                                break;
                            case KeyEvent.VK_BACK_SPACE:
                                // avoir les coordonnees du joueur qui joue
                                c = modele.getJ_actuel().getC();
                                x = c.getX();
                                y = c.getY();
                                if (modele.getCas(x , y).etat == 1) {
                                    modele.getCas(x , y).setEtat(0);
                                    modele.getJ_actuel().action_moins();
                                }
                                break;
                        }
                    }

                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        panel.add(buttonAsseche);

        JButton buttonRecup = new JButton("Récupérer");
        buttonRecup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int x = modele.getJ_actuel().getC().getX();
                        int y = modele.getJ_actuel().getC().getY();
                        if(modele.getCas(x,y).contient_Artefacts()){
                            modele.getJ_actuel().setNb_act(modele.getJ_actuel().getNb_act()+1);

                        }
                    }
                }
        );
        panel.add(buttonRecup);

        JButton EchangeCle = new JButton("Donner clé");
        panel.add(EchangeCle);
        EchangeCle.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int x = modele.getJ_actuel().getC().getX();
                        int y = modele.getJ_actuel().getC().getY();
                        if(modele.getCas(x,y).contient_joueur()){
                            modele.getJ_actuel().suppr_CleE();
                            modele.getJoueurs().get(modele.getCas(x,y).getJoueur()).ajoute_Cle();
                            modele.getJ_actuel().action_moins();
                        }
                    }
                }
        );

        this.add(panel);
        JListenner jtl = new JListenner(modele,button,ctrl);
        button.addKeyListener(jtl);
    }
}