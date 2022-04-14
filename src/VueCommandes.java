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
        panel.add(buttonAsseche);

        Controleur ctrl2 = new Controleur(modele);
        buttonAsseche.addActionListener(ctrl2);

        JButton buttonRecup = new JButton("Récupérer");
        panel.add(buttonRecup);

        this.add(panel);

        JListenner jtl = new JListenner(modele,button,ctrl);
        button.addKeyListener(jtl);
    }
}

