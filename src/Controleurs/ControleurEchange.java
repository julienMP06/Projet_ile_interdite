package Controleurs;
import Vue.*;
import Modele.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class ControleurEchange extends VueJoueurs implements ActionListener {

    /**
     * On a ici le contreleur du bouton echange qui permet d'echanger une cle avec un joueur
     * en fonction de la touche utilisée par le joueur
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

    public ControleurEchange(CModele modele, JLabel label, JPanel panelC, JTextField EntrerX, JTextField EntrerY, JButton valider, JCheckBox oui , JButton validerEchange, JTextField CleEchange) {
        super(modele);
        this.modele = modele;
        this.label = label;
        this.panelC = panelC;
        this.EntrerX = EntrerX;
        this.EntrerY = EntrerY;
        this.valider = valider;
        this.validerEchange = validerEchange;
        this.CleEchange = CleEchange;
        this.oui = oui;

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
        if (modele.partie_perdue()) {
            label.setText("Tu ne peux plus jouer Recommence");
        } else {
            if (modele.getJ_actuel().getNb_act() > 0) {
                int x = modele.getJ_actuel().getC().getX();
                int y = modele.getJ_actuel().getC().getY();
                if (modele.getCas(x, y).contient_joueur()) {
                    ArrayList<Joueur> l = modele.getCas(x, y).get_joueurs();
                    switch (l.size()) {
                        case 1:
                            label.setText("impossible d'echanger avec vous meme");
                            break;
                        case 2:
                            CleEchange.setText("Cle A E T F");
                            panelC.add(CleEchange);
                            panelC.add(validerEchange);
                            validerEchange.addActionListener(
                                    new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String C = CleEchange.getText();
                                            if (C.equals("A")) {
                                                if (modele.getJ_actuel().get_nb_cle("Air") != 0) {
                                                    label.setText("Don de cle Air");
                                                    ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
                                                    if (v.get(0) != modele.getJ_actuel()) {
                                                        v.get(0).ajoute_cle("Air");
                                                        v.get(1).supprimer_cle("Air");
                                                    } else {
                                                        v.get(0).supprimer_cle("Air");
                                                        v.get(1).ajoute_cle("Air");
                                                    }
                                                } else {
                                                    label.setText("Pas assez de cle");
                                                }
                                                panelC.remove(CleEchange);
                                                panelC.remove(validerEchange);
                                                panelC.revalidate();
                                                panelC.repaint();
                                                modele.MAJ();
                                            } else if (C.equals("E")) {
                                                if (modele.getJ_actuel().get_nb_cle("Eau") != 0) {
                                                    ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
                                                    if (v.get(0) != modele.getJ_actuel()) {
                                                        v.get(0).ajoute_cle("Eau");
                                                        v.get(1).supprimer_cle("Eau");
                                                    } else {
                                                        v.get(1).ajoute_cle("Eau");
                                                        v.get(0).supprimer_cle("Eau");
                                                    }
                                                    label.setText("Don de cle Eau");
                                                } else {
                                                    label.setText("Pas assez de cle");
                                                }
                                                panelC.remove(CleEchange);
                                                panelC.remove(validerEchange);
                                                panelC.revalidate();
                                                panelC.repaint();
                                                modele.MAJ();
                                            } else if (C.equals("T")) {
                                                if (modele.getJ_actuel().get_nb_cle("Terre") != 0) {
                                                    ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
                                                    if (v.get(0) != modele.getJ_actuel()) {
                                                        v.get(0).ajoute_cle("Terre");
                                                        v.get(1).supprimer_cle("Terre");
                                                    } else {
                                                        v.get(1).ajoute_cle("Terre");
                                                        v.get(0).supprimer_cle("Terre");
                                                    }
                                                    label.setText("Don de cle Terre");
                                                } else {
                                                    label.setText("Pas assez de cle");
                                                }
                                                panelC.remove(CleEchange);
                                                panelC.remove(validerEchange);
                                                panelC.revalidate();
                                                panelC.repaint();
                                                modele.MAJ();
                                            } else if (C.equals("F")) {
                                                if (modele.getJ_actuel().get_nb_cle("Feu") != 0) {
                                                    ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
                                                    if (v.get(0) != modele.getJ_actuel()) {
                                                        v.get(0).ajoute_cle("Feu");
                                                        v.get(1).supprimer_cle("Feu");
                                                    } else {
                                                        v.get(1).ajoute_cle("Feu");
                                                        v.get(0).supprimer_cle("Feu");
                                                    }
                                                    label.setText("Don de cle Feu");
                                                } else {
                                                    label.setText("Pas assez de cle");
                                                }
                                                panelC.remove(CleEchange);
                                                panelC.remove(validerEchange);
                                                panelC.revalidate();
                                                panelC.repaint();
                                                modele.MAJ();
                                            }else{
                                                panelC.remove(CleEchange);
                                                panelC.remove(validerEchange);
                                                panelC.revalidate();
                                                panelC.repaint();
                                                label.setText("La cle n'est pas bonne");
                                            }
                                        }

                                    }
                            );
                            break;
                        case 3:
                            label.setText("Vous etes trop sur la meme case");
                            break;
                        case 4:
                            label.setText("Vous etes trop sur la meme case");
                            break;
                    }
                    modele.getJ_actuel().action_moins();
                }
            }
        }
    }
}