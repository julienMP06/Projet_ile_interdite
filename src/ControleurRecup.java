import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ControleurRecup extends VueJoueurs implements ActionListener {
    CModele modele;

    private JLabel label;

    public ControleurRecup(CModele modele, JLabel label) {
        super(modele);
        this.modele = modele;
        this.label = label;
    }

    public void actionPerformed(ActionEvent e) {
        if (modele.partie_perdue()) {
            label.setText("Tu ne peux plus jouer Recommence");
        } else if (modele.getJ_actuel().getNb_act()>0) {
            int x = modele.getJ_actuel().getC().getX();
            int y = modele.getJ_actuel().getC().getY();
            if (modele.getCas(x, y).contient_artefact()) {
                Artefact A = modele.getCas(x, y).get_Artefacts().get(0);
                if (modele.getJ_actuel().get_nb_cle(A.getNom())>=1) {
                	modele.getCas(x, y).supprime_artefact();
                	modele.getJ_actuel().ajouter_artefact(A);
                	modele.getJ_actuel().supprimer_cle(A.getNom());
                	modele.getJ_actuel().action_moins();

                              
            } else {
                label.setText("Il n'y a pas d'artefact ici");
            }
        }
        modele.MAJ();
    }

}
}
