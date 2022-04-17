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

    GridLayout grid = new GridLayout(2, 2);
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
        buttonAsseche.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int x = modele.getJ_actuel().getC().getX();
                        int y = modele.getJ_actuel().getC().getY();
                        if(modele.getCas(x,y).etat != 1){
                            label.setText("Assechement Impossible");
                            panel.add(label);
                        }else{
                            modele.getCas(x,y).etat = 0;
                            modele.getJ_actuel().action_moins();
                        }
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

        this.add(panel);
        JListenner jtl = new JListenner(modele,button,ctrl);
        button.addKeyListener(jtl);
    }
}