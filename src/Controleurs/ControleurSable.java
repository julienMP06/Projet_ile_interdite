package Controleurs;
import Vue.*;
import Modele.*;
import java.awt.event.*;
import javax.swing.*;

public class ControleurSable extends VueJoueurs implements ActionListener {

    /**
     * Le controleur du bouton Sac de Sable permet au joueur d'assecher n'importe quelle case sur la grille
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

    public ControleurSable(CModele modele, JLabel labelC, JPanel panelC, JTextField EntrerX, JTextField EntrerY, JButton valider, JCheckBox oui, JButton validerEchange, JTextField CleEchange) {
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
        if (modele.partie_perdue()) {
            label.setText("Tu ne peux plus jouer Recommence");
        } else {
            if (modele.getJ_actuel().get_nb_ActionSacSable() > 0){
                panelC.remove(EntrerX);
                panelC.remove(EntrerY);
                panelC.remove(valider);
                panelC.remove(oui);
                panelC.remove(CleEchange);
                panelC.remove(validerEchange);
                panelC.revalidate();
                panelC.repaint();
                EntrerX.setText("Entre X");
                EntrerY.setText("Entre Y");
                label.setText("Pour annuler mets 0 0 et valide");
                panelC.add(EntrerX);
                panelC.add(EntrerY);
                panelC.add(valider);
                valider.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String textX = EntrerX.getText();
                                int x = Integer.parseInt(textX);
                                String textY = EntrerY.getText();
                                int y = Integer.parseInt(textY);
                                if (x > 0 && x < 7 && y > 0 && y < 7) {
                                    panelC.revalidate();
                                    panelC.remove(EntrerX);
                                    panelC.remove(EntrerY);
                                    panelC.remove(valider);
                                    panelC.repaint();
                                    modele.getJ_actuel().Sac_a_sable(x, y);
                                    modele.getJ_actuel().supprimer_ActionSpe("Sac a sable");
                                    label.setText("Case " + x + " " + y + " asseche");
                                    modele.MAJ();
                                }else if(x == 0 && y == 0){
                                    panelC.revalidate();
                                    panelC.remove(EntrerX);
                                    panelC.remove(EntrerY);
                                    panelC.remove(valider);
                                    panelC.repaint();
                                    label.setText("Action annule");
                                }else {
                                    label.setText("Hors Grille");
                                    panelC.revalidate();
                                    panelC.remove(EntrerX);
                                    panelC.remove(EntrerY);
                                    panelC.remove(valider);
                                    panelC.repaint();
                                }
                                modele.MAJ();
                            }
                        }
                );
                panelC.revalidate();
                panelC.repaint();
                modele.MAJ();

            }else{
                label.setText("Tu n'as pas d'action Sac de Sable");
            }
            modele.MAJ();
        }

    }
}