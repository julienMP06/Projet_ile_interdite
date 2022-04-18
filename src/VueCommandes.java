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
    GridLayout grid = new GridLayout(4, 0);
    public VueCommandes(CModele modele) {

        JPanel panelC = new JPanel();
        JLabel label = new JLabel();
        panelC.setLayout(grid);
        grid.setHgap(10);
        grid.setVgap(15);
        this.modele=modele;

        JButton boutonAvance = new JButton("Fin De Tour");
        panelC.add(boutonAvance);

        Controleur ctrl = new Controleur(modele);
        boutonAvance.addActionListener(ctrl);


        JButton button = new JButton("Déplacement");
        panelC.add(button);

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
                                    label.setText("Asseche Droite");
                                }
                                else{label.setText("Impossible !");}
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
                                }
                                else{label.setText("Impossible !");}
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
                                }
                                else{label.setText("Impossible !");}
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
                                }
                                else{label.setText("Impossible !");}
                                break;
                            case KeyEvent.VK_SPACE:
                                // avoir les coordonnees du joueur qui joue
                                c = modele.getJ_actuel().getC();
                                x = c.getX();
                                y = c.getY();
                                if (modele.getCas(x , y).etat == 1) {
                                    modele.getCas(x , y).setEtat(0);
                                    modele.getJ_actuel().action_moins();
                                    label.setText("Asseché Actuelle");
                                }
                                else{label.setText("Impossible !");}
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

        panelC.add(buttonAsseche);

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
        panelC.add(buttonRecup);

        JButton EchangeCle = new JButton("Donner clé");
        panelC.add(EchangeCle);
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

        this.add(panelC);
        this.add(label);
        JListenner jtl = new JListenner(modele,button,ctrl,label);
        button.addKeyListener(jtl);
    }
}