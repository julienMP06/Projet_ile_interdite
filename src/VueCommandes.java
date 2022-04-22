import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

class VueCommandes extends JPanel {
    private CModele modele;
    GridLayout grid = new GridLayout(5, 0);

	JPanel panel2 = new JPanel();
    JLabel label = new JLabel();
    public VueCommandes(CModele modele) {

        JPanel panelC = new JPanel();

        panelC.setLayout(grid);
        grid.setHgap(10);
        grid.setVgap(15);
        this.modele=modele;

        JButton boutonAvance = new JButton("Fin De Tour");
        panelC.add(boutonAvance);

        Controleur ctrl = new Controleur(modele,label);
        boutonAvance.addActionListener(ctrl);


        JButton button = new JButton("Deplacement");
        panelC.add(button);

        JButton buttonAsseche = new JButton("Assecher");

        buttonAsseche.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (modele.partie_perdue()) {
                            label.setText("Tu ne peux plus jouer Recommence");
                        } else {
                        	if (modele.getJ_actuel().getNb_act()>0){
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
	                                    } else {
	                                        label.setText("Impossible !");
	                                    }
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
	                                    } else {
	                                        label.setText("Impossible !");
	                                    }
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
	                                    } else {
	                                        label.setText("Impossible !");
	                                    }
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
	                                    } else {
	                                        label.setText("Impossible !");
	                                    }
	                                    break;
	                                case KeyEvent.VK_SPACE:
	                                    // avoir les coordonnees du joueur qui joue
	                                    c = modele.getJ_actuel().getC();
	                                    x = c.getX();
	                                    y = c.getY();
	                                    if (modele.getCas(x, y).etat == 1) {
	                                        modele.getCas(x, y).setEtat(0);
	                                        modele.getJ_actuel().action_moins();
	                                        label.setText("Asseché Actuelle");
	                                    } else {
	                                        label.setText("Impossible !");
	                                    }
	                                    break;
	                            }
                            
                        	}
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

        JButton buttonRecup = new JButton("Recuperer");
        buttonRecup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (modele.partie_perdue()) {
                            label.setText("Tu ne peux plus jouer Recommence");
                        } else {
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
                                        } else {
                                            label.setText("Tu n'as pas assez de cle");
                                        }
                                        break;
                                    case "E":
                                        if (modele.getJ_actuel().getCleEau() >= 1) {
                                            modele.getJ_actuel().ajouter_artefact(l.get(0));
                                            modele.getJ_actuel().action_moins();
                                            modele.getCas(x, y).supprime_artefact();
                                        } else {
                                            label.setText("Tu n'as pas assez de cle");
                                        }
                                        break;
                                    case "A":
                                        if (modele.getJ_actuel().getCleAir() >= 1) {
                                            modele.getJ_actuel().ajouter_artefact(l.get(0));
                                            modele.getJ_actuel().action_moins();
                                            modele.getCas(x, y).supprime_artefact();
                                        } else {
                                            label.setText("Tu n'as pas assez de cle");
                                        }
                                        break;
                                    case "T":
                                        if (modele.getJ_actuel().getCleTerre() >= 1) {
                                            modele.getJ_actuel().ajouter_artefact(l.get(0));
                                            modele.getJ_actuel().action_moins();
                                            modele.getCas(x, y).supprime_artefact();
                                        } else {
                                            label.setText("Tu n'as pas assez de cle");
                                        }
                                        break;
                                }

                            } else {
                                label.setText("Il n'y a pas d'artefact ici");
                            }
                        }
                    }
                }
        );
        panelC.add(buttonRecup);

        JButton EchangeCle = new JButton("Donner cle");
        panelC.add(EchangeCle);
        EchangeCle.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	if (modele.partie_perdue()) {
                            label.setText("Tu ne peux plus jouer Recommence");
                        } else {
                        	if (modele.getJ_actuel().getNb_act()>0){
		                        int x = modele.getJ_actuel().getC().getX();
		                        int y = modele.getJ_actuel().getC().getY();
		                        ArrayList<Joueur> l = modele.getCas(x, y).get_joueurs();
		                        
		                        if(modele.getCas(x,y).contient_joueur()){
		                            switch (l.size()){
		                                case 1 :
		                                    label.setText("impossible d'echanger avec vous meme");
		                                    break;
		                                case 2 :
		                                    label.setText("Appuyez sur A E F T pour donner");
		                                    EchangeCle.addKeyListener(
		                                            new KeyListener() {
		                                                @Override
		                                                public void keyReleased(KeyEvent e) {
		
		                                                }
		
		                                                @Override
		                                                public void keyPressed(KeyEvent e) {
		                                                    switch (e.getKeyCode()) {
		                                                        //Cle c = new Cle(modele,"Air");
		                                                        case KeyEvent.VK_A:
		                                                            if (modele.getJ_actuel().getCleAir() != 0) {
		                                                               
		                                                            	modele.getJ_actuel().supprimer_cle("Air");
		                                                                label.setText("Don de cle Air");
		                                                                ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
		                                                                if(v.get(0) != modele.getJ_actuel()){
		                                                                    v.get(0).ajoute_cle_air();
		                                                                }else {
		                                                                    v.get(1).ajoute_cle_air();
		                                                                }
		                                                            }else{
		                                                                label.setText("Pas assez de cle");
		                                                            }
		                                                            break;
		                                                        case KeyEvent.VK_E:
		                                                            if (modele.getJ_actuel().getCleEau() != 0) {
		                                                              
		                                                                modele.getJ_actuel().supprimer_cle("Eau");
		                                                                ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
		                                                                    if(v.get(0) != modele.getJ_actuel()){
		                                                                        v.get(0).ajoute_cle_eau();
		                                                                    }else {
		                                                                        v.get(1).ajoute_cle_eau();
		                                                                    }
		                                                                label.setText("Don de cle Eau");
		                                                            }else{
		                                                                label.setText("Pas assez de cle");
		                                                            }
		                                                            break;
		                                                        case KeyEvent.VK_T:
		                                                            if (modele.getJ_actuel().getCleTerre() != 0) {
		                                                                ;
		                                                                modele.getJ_actuel().supprimer_cle("Terre");
		                                                                ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
		                                                                if(v.get(0) != modele.getJ_actuel()){
		                                                                    v.get(0).ajoute_cle_terre();
		                                                                }else {
		                                                                    v.get(1).ajoute_cle_terre();
		                                                                }
		                                                                label.setText("Don de cle Terre");
		                                                            }else{
		                                                                label.setText("Pas assez de cle");
		                                                            }
		                                                            break;
		                                                        case KeyEvent.VK_F:
		                                                            if (modele.getJ_actuel().getCleFeu() != 0) {
		                                                            
		                                                                
		                                                                modele.getJ_actuel().supprimer_cle("Feu");
		                                                                
		                                                                ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
		                                                                if(v.get(0) != modele.getJ_actuel()){
		                                                                    v.get(0).ajoute_cle_feu();
		                                                                }else {
		                                                                    v.get(1).ajoute_cle_feu();
		                                                                }
		                                                                label.setText("Don de cle Feu");
		                                                            }else{
		                                                                label.setText("Pas assez de cle");
		                                                            }
		                                                            break;
		                                                    }
		                                                }
		
		                                                @Override
		                                                public void keyTyped(KeyEvent e) {
		
		                                                }
		                                            }
		                                    );
		                                    break;
		                                case 3 :
		                                    label.setText("Vous ne devez etre que deux sur la meme case");
		                                    break;
		                                case 4 :
		                                    label.setText("Vous ne devez etre que deux sur la meme case");
		                                    break;
		                            }
		                            //modele.getJ_actuel().suppr_CleE();
		                            //modele.getJoueurs().get(modele.getCas(x,y).getJoueur()).ajoute_Cle();
		                            modele.getJ_actuel().action_moins();
		                        }
		                    }
	                    }
                    }
                }
        );
        

        JButton boutonEnvole = new JButton("S'envoler");
        panelC.add(boutonEnvole);
        boutonEnvole.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        if(modele.partie_gagnee()){
                            label.setText("Partie Gagnee, Bravo !");
                        } else {
                        label.setText("Tu n'as pas fini de chercher");
                        }
                    }
                }
        );
		
		JButton SacSable= new JButton("Sac de sable");
		JButton Helico = new JButton("Helico");
		panelC.add(SacSable);
		panelC.add(Helico);
		
		/*ActionSpeciale.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("fdfdf");
						panel2.add(SacSable);
						panel2.add(Helico);
						panelC.add(panel2);
					}
				}
		);*/

        this.add(panelC);
        this.add(label);
        JListenner jtl = new JListenner(modele,button,ctrl,label);
        button.addKeyListener(jtl);
    }


}