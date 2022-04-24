import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ControleurEnvole extends VueJoueurs implements ActionListener {

    /**
     * Le controleur du bouton Envole qui permet de terminer une partie si toutes les conditions sont remplies
     */
    CModele modele;
    private JLabel label;

    public ControleurEnvole(CModele modele, JLabel label) {
        super(modele);
        this.modele = modele;
        this.label = label;
    }

    public void actionPerformed(ActionEvent e) {
        if (modele.partie_perdue()) {
            label.setText("Tu ne peux plus jouer Recommence");
        } else {
            if (modele.partie_gagnee()) {
                label.setText("Partie Gagnee, Bravo !");
            } else {
                label.setText("Tu n'as pas fini de chercher");
            }
        }
    }
}