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
                ArrayList<Artefact> l = modele.getCas(x, y).get_Artefacts();
                switch (l.get(0).getNom()) {
                    case "F":
                        if (modele.getJ_actuel().getCleFeu() >= 1) {
                            modele.getJ_actuel().ajouter_artefact(l.get(0));
                            modele.getJ_actuel().action_moins();
                            modele.getCas(x, y).supprime_artefact();
                            label.setText(modele.getJ_actuel().getNom_joueur()+ " recupere l'artefact Feu");
                        } else {
                            label.setText("Tu n'as pas assez de cle");
                        }
                        break;
                    case "E":
                        if (modele.getJ_actuel().getCleEau() >= 1) {
                            modele.getJ_actuel().ajouter_artefact(l.get(0));
                            modele.getJ_actuel().action_moins();
                            modele.getCas(x, y).supprime_artefact();
                            label.setText(modele.getJ_actuel().getNom_joueur()+ " recupere l'artefact Eau");
                        } else {
                            label.setText("Tu n'as pas assez de cle");
                        }
                        break;
                    case "A":
                        if (modele.getJ_actuel().getCleAir() >= 1) {
                            modele.getJ_actuel().ajouter_artefact(l.get(0));
                            modele.getJ_actuel().action_moins();
                            modele.getCas(x, y).supprime_artefact();
                            label.setText(modele.getJ_actuel().getNom_joueur()+ " recupere l'artefact Air");
                        } else {
                            label.setText("Tu n'as pas assez de cle");
                        }
                        break;
                    case "T":
                        if (modele.getJ_actuel().getCleTerre() >= 1) {
                            modele.getJ_actuel().ajouter_artefact(l.get(0));
                            modele.getJ_actuel().action_moins();
                            modele.getCas(x, y).supprime_artefact();
                            label.setText(modele.getJ_actuel().getNom_joueur()+ " recupere l'artefact Terre");
                        } else {
                            label.setText("Tu n'as pas assez de cle");
                        }
                        break;
                }

            } else {
                label.setText("Il n'y a pas d'artefact ici");
            }
        }
        modele.MAJ();
    }

}
