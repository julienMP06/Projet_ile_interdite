package Controleurs;
import Vue.*;
import Modele.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ControleurRecup extends VueJoueurs implements ActionListener {

    /**
     * Le controleur du bouton Recuperer qui permet de vérifier le nombre de clé du joueur ainsi que le contenu
     * de la case du joueur et de récupérer l'artefact si il y en a un et que le joueur a assez de cle.
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
    public ControleurRecup(CModele modele, JLabel labelC, JPanel panelC, JTextField EntrerX, JTextField EntrerY, JButton valider, JCheckBox oui, JButton validerEchange, JTextField CleEchange) {
        super(modele);
        this.modele = modele;
        this.label = labelC;
        this.panelC = panelC;
        this.EntrerX = EntrerX;
        this.EntrerY = EntrerY;
        this.valider = valider;
        this.oui = oui;
        this.validerEchange = validerEchange;
        this.CleEchange = CleEchange;
    }

    public void actionPerformed(ActionEvent e) {
        panelC.remove(EntrerX);
        panelC.remove(EntrerY);
        panelC.remove(valider);
        panelC.remove(oui);
        panelC.remove(CleEchange);
        panelC.remove(validerEchange);
        panelC.revalidate();
        panelC.repaint();
    	if (modele.partie_gagnee()) {}
    	else if (modele.partie_perdue()) {
            label.setText("Tu ne peux plus jouer Recommence");
        } else if (modele.getJ_actuel().getNb_act()>0) {
            int x = modele.getJ_actuel().getC().getX();
            int y = modele.getJ_actuel().getC().getY();
            if (modele.getCas(x, y).contient_artefact()) {
                Artefact A = modele.getCas(x, y).get_Artefacts().get(0);
                if (modele.getJ_actuel().get_nb_cle(A.getNom())>=4) {
                	modele.getCas(x, y).supprime_artefact();
                	modele.getJ_actuel().ajouter_artefact(A);
                	for (int i=1; i<=4; i++) {
                		modele.getJ_actuel().supprimer_cle(A.getNom());
                	}
                	modele.getJ_actuel().action_moins();
                	label.setText(A.getNom()+" recupere !");

                              
                } else{
                    label.setText("Tu n'as pas assez de cle");
                }
            }else {
                label.setText("Il n'y a pas d'artefact ici");
            }
        
        modele.MAJ();
            
    }

}
}
