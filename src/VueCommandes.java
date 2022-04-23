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

class VueCommandes extends JPanel{
    private CModele modele;
	GridLayout grid = new GridLayout(7, 0);
	JTextField EntrerX = new JTextField("Entre X");
	JTextField EntrerY = new JTextField("Entre Y");

	JButton valider = new JButton("Valider");
	JLabel label = new JLabel();
	public VueCommandes(CModele modele, JFrame frameMenu, JFrame frame) {

        JPanel panelC = new JPanel();

        panelC.setLayout(grid);
        grid.setHgap(10);
        grid.setVgap(15);
        this.modele=modele;

		JLabel labelSpace = new JLabel("       ");

        JButton boutonAvance = new JButton("Fin De Tour");
		boutonAvance.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						panelC.remove(EntrerX);
						panelC.remove(EntrerY);
						panelC.remove(valider);
						panelC.revalidate();
						panelC.repaint();
					}
				}
		);

        Controleur ctrl = new Controleur(modele,label);

        boutonAvance.addActionListener(ctrl);


        JButton button = new JButton("Deplacement");


        JButton buttonAsseche = new JButton("Assecher");

        buttonAsseche.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyPressed(KeyEvent e) {
						panelC.remove(EntrerX);
						panelC.remove(EntrerY);
						panelC.remove(valider);
						panelC.revalidate();
						panelC.repaint();
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
	                                        label.setText("Asseche Actuelle");
	                                    } else {
	                                        label.setText("Impossible !");
	                                    }
	                                    break;
	                            }

                        	}
                        }
						modele.MAJ();
                    }

                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        JButton buttonRecup = new JButton("Recuperer");
        ControleurRecup ctrlRec = new ControleurRecup(modele,label);
		buttonRecup.addActionListener(ctrlRec);



        JButton EchangeCle = new JButton("Donner cle");

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
		                                                            if (modele.getJ_actuel().get_nb_cle("Air")!= 0) {
		                                                               
		                                                            	modele.getJ_actuel().supprimer_cle("Air");
		                                                                label.setText("Don de cle Air");
		                                                                ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
		                                                                if(v.get(0) != modele.getJ_actuel()){
		                                                                    v.get(0).ajoute_cle("Air");
		                                                                }else {
		                                                                    v.get(1).ajoute_cle("Air");
		                                                                }
		                                                            }else{
		                                                                label.setText("Pas assez de cle");
		                                                            }
		                                                            break;
		                                                        case KeyEvent.VK_E:
		                                                            if (modele.getJ_actuel().get_nb_cle("Eau") != 0) {
		                                                              
		                                                                modele.getJ_actuel().supprimer_cle("Eau");
		                                                                ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
		                                                                    if(v.get(0) != modele.getJ_actuel()){
		                                                                        v.get(0).ajoute_cle("Eau");
		                                                                    }else {
		                                                                        v.get(1).ajoute_cle("Eau");
		                                                                    }
		                                                                label.setText("Don de cle Eau");
		                                                            }else{
		                                                                label.setText("Pas assez de cle");
		                                                            }
		                                                            break;
		                                                        case KeyEvent.VK_T:
		                                                            if (modele.getJ_actuel().get_nb_cle("Terre") != 0) {
		                                                                ;
		                                                                modele.getJ_actuel().supprimer_cle("Terre");
		                                                                ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
		                                                                if(v.get(0) != modele.getJ_actuel()){
		                                                                    v.get(0).ajoute_cle("Terre");
		                                                                }else {
		                                                                    v.get(1).ajoute_cle("Terre");
		                                                                }
		                                                                label.setText("Don de cle Terre");
		                                                            }else{
		                                                                label.setText("Pas assez de cle");
		                                                            }
		                                                            break;
		                                                        case KeyEvent.VK_F:
		                                                            if (modele.getJ_actuel().get_nb_cle("Feu") != 0) {
		                                                            
		                                                                
		                                                                modele.getJ_actuel().supprimer_cle("Feu");
		                                                                
		                                                                ArrayList<Joueur> v = modele.getJ_actuel().getC().get_joueurs();
		                                                                if(v.get(0) != modele.getJ_actuel()){
		                                                                    v.get(0).ajoute_cle("Feu");
		                                                                }else {
		                                                                    v.get(1).ajoute_cle("Feu");
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

        boutonEnvole.addActionListener(
                new ActionListener() {
                    @Override
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
        );
		
		JButton SacSable= new JButton("Sac de sable");

		SacSable.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (modele.partie_perdue()) {
							label.setText("Tu ne peux plus jouer Recommence");
						} else {
							if (modele.getJ_actuel().get_nb_ActionSacSable() > 0){
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
														label.setText("Case " + x + " " + y + " asseche");
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
												}
											}
									);
									panelC.revalidate();
									panelC.repaint();

						}else{
								label.setText("Tu n'as pas d'action Sac de Sable");
							}
						}
					}
				}
		);

		JButton Helico = new JButton("Helico");

		Helico.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (modele.partie_perdue()) {
							label.setText("Tu ne peux plus jouer Recommence");
						} else {
							if (modele.getJ_actuel().get_nb_ActionHelico() > 0){
								EntrerX.setText("Entre X");
								EntrerY.setText("Entre Y");
								panelC.add(EntrerX);
								panelC.add(EntrerY);
								label.setText("Pour annuler mets 0 0 et valide");
								panelC.add(valider);
								valider.addActionListener(
										new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												String textX = EntrerX.getText();
												int x = Integer.parseInt(textX);
												String textY = EntrerY.getText();
												int y = Integer.parseInt(textY);
												if(x > 0 && x < 7 && y > 0 && y < 7){
													panelC.revalidate();
													panelC.remove(EntrerX);
													panelC.remove(EntrerY);
													panelC.remove(valider);
													panelC.repaint();
													label.setText("Deplacement en " +x+" "+y);
													Case c =modele.getJ_actuel().getC();
													ArrayList<Joueur> joueurs=c.get_joueurs();
													modele.getJ_actuel().heleco(x, y);
													modele.getJ_actuel().supprimer_ActionSpe("Heleco");
													modele.MAJ();
												}else if(x == 0 && y == 0){
													panelC.revalidate();
													panelC.remove(EntrerX);
													panelC.remove(EntrerY);
													panelC.remove(valider);
													panelC.repaint();
													label.setText("Action annule");
												}else{
													panelC.revalidate();
													panelC.remove(EntrerX);
													panelC.remove(EntrerY);
													panelC.remove(valider);
													panelC.repaint();
													label.setText("Hors Grille");
												}
												modele.MAJ();
												
											}
										}
								);

							}else{
								label.setText("Tu n'as pas d'action Helico");
							}
						}modele.MAJ();
					}
				}
		);

		JButton retour = new JButton("<- Menu");
		retour.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frameMenu.setVisible(true);
						frame.dispose();
					}
				}
		);

		panelC.add(boutonAvance);
		panelC.add(button);
		panelC.add(buttonAsseche);
		panelC.add(buttonRecup);
		panelC.add(EchangeCle);
		panelC.add(boutonEnvole);
		panelC.add(SacSable);
		panelC.add(Helico);
		panelC.add(retour);
		panelC.add(labelSpace);
		panelC.revalidate();
		panelC.repaint();
		this.add(panelC);
        this.add(label);
        JListenner jtl = new JListenner(modele,button,ctrl,label);
        button.addKeyListener(jtl);
    }

}