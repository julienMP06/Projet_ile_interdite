package Controleurs;
import Vue.*;
import Modele.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ControleurHelico extends VueJoueurs implements ActionListener {

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

    public ControleurHelico(CModele modele, JLabel labelC, JPanel panelC, JTextField EntrerX, JTextField EntrerY, JButton valider, JCheckBox oui, JButton validerEchange, JTextField CleEchange) {
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
            if (modele.getJ_actuel().get_nb_ActionHelico() > 0){
                EntrerX.setText("Entre X");
                EntrerY.setText("Entre Y");
                panelC.add(EntrerX);
                panelC.add(EntrerY);
                panelC.add(valider);
                label.setText("Voulez vous emporter les autres avec vous ?");
                panelC.add(oui);
                label.setText("Pour annuler mets 0 0 et valide");
                valider.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String textX = EntrerX.getText();
                                int x = Integer.parseInt(textX);
                                String textY = EntrerY.getText();
                                int y = Integer.parseInt(textY);
                                boolean b = oui.isSelected();

                                if (x > 0 && x < 7 && y > 0 && y < 7) {
                                    if  (b) {
                                        Case c = modele.getJ_actuel().getC();
                                        ArrayList<Joueur> joueurs = c.get_joueurs();
                                        //System.out.println(joueurs.size());
                                        ArrayList<Joueur> l  =new ArrayList<Joueur>();
                                        for (Joueur j : joueurs) {
                                            l.add(j);
                                            //j.heleco, y);
                                            //System.out.println(j.getC().getX()+"  "+j.getC().getY());
                                        }
                                        for (Joueur j : l) {
                                            j.heleco(x, y);
                                        }

                                    }else {
                                        modele.getJ_actuel().heleco(x, y);
                                    }

                                    panelC.revalidate();
                                    panelC.remove(EntrerX);
                                    panelC.remove(EntrerY);
                                    panelC.remove(valider);
                                    panelC.remove(oui);
                                    panelC.repaint();
                                    label.setText("Deplacement en " + x + " " + y);
                                    modele.getJ_actuel().supprimer_ActionSpe("Heleco");
                                    modele.MAJ();
                                } else if (x == 0 && y == 0) {
                                    panelC.revalidate();
                                    panelC.remove(EntrerX);
                                    panelC.remove(EntrerY);
                                    panelC.remove(valider);
                                    panelC.remove(oui);
                                    panelC.repaint();
                                    label.setText("Action annule");
                                } else {
                                    panelC.revalidate();
                                    panelC.remove(EntrerX);
                                    panelC.remove(EntrerY);
                                    panelC.remove(valider);
                                    panelC.remove(oui);
                                    panelC.repaint();
                                    label.setText("Hors Grille");
                                }
                            }
                        }
                );

            }else{
                label.setText("Tu n'as pas d'action Helico");
            }
        }modele.MAJ();
    }
}