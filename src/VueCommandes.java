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

	JTextField EntrerX = new JTextField();

	JTextField EntrerY = new JTextField();
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

        panelC.add(buttonAsseche);

        JButton buttonRecup = new JButton("Recuperer");
		panelC.add(buttonRecup);

        ControleurRecup ctrlRec = new ControleurRecup(modele,label);
		buttonRecup.addActionListener(ctrlRec);


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
		panelC.add(SacSable);
		SacSable.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (modele.partie_perdue()) {
							label.setText("Tu ne peux plus jouer Recommence");
						} else {
							if (modele.getJ_actuel().getActionSacSable() > 0){
								label.setText("X : ");
								label.add(EntrerX);
								modele.MAJ();
						}else{
								label.setText("Tu n'as pas d'action Sac de Sable");
							}
						}
					}
				}
		);

		JButton Helico = new JButton("Helico");
		panelC.add(Helico);
		/*Helico.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (modele.partie_perdue()) {
							label.setText("Tu ne peux plus jouer Recommence");
						} else {
							if (modele.getJ_actuel().getActionHelico() > 0) {
								label.setText("Donne le X de la case pour utiliser");
								Helico.addKeyListener(
										new KeyListener() {
											int x = 0;
											@Override
											public void keyTyped(KeyEvent e) {

											}

											@Override
											public void keyPressed(KeyEvent e) {
												switch (e.getKeyCode()) {
													case KeyEvent.VK_NUMPAD1:
														x = 1;
														label.setText("Donne le Y de la case pour utiliser");
														Helico.addKeyListener(

																new KeyListener() {
																	int y = 0;

																	@Override
																	public void keyTyped(KeyEvent e) {

																	}

																	@Override
																	public void keyPressed(KeyEvent e) {
																		switch (e.getKeyCode()) {
																			case KeyEvent.VK_NUMPAD1:
																				y = 1;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD2:
																				y = 2;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD3:
																				y = 3;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD4:
																				y = 4;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD5:
																				y = 5;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD6:
																				y = 6;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																		}
																	}

																	@Override
																	public void keyReleased(KeyEvent e) {

																	}
																}
														);
														break;
													case KeyEvent.VK_NUMPAD2:
														x = 2;
														label.setText("Donne le Y de la case pour utiliser");
														Helico.addKeyListener(

																new KeyListener() {
																	int y = 0;

																	@Override
																	public void keyTyped(KeyEvent e) {

																	}

																	@Override
																	public void keyPressed(KeyEvent e) {
																		switch (e.getKeyCode()) {
																			case KeyEvent.VK_NUMPAD1:
																				y = 1;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD2:
																				y = 2;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD3:
																				y = 3;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD4:
																				y = 4;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD5:
																				y = 5;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD6:
																				y = 6;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																		}
																	}

																	@Override
																	public void keyReleased(KeyEvent e) {

																	}
																}
														);
														break;
													case KeyEvent.VK_NUMPAD3:
														x = 3;
														label.setText("Donne le Y de la case pour utiliser");
														Helico.addKeyListener(

																new KeyListener() {
																	int y = 0;

																	@Override
																	public void keyTyped(KeyEvent e) {

																	}

																	@Override
																	public void keyPressed(KeyEvent e) {
																		switch (e.getKeyCode()) {
																			case KeyEvent.VK_NUMPAD1:
																				y = 1;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD2:
																				y = 2;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD3:
																				y = 3;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD4:
																				y = 4;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD5:
																				y = 5;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD6:
																				y = 6;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																		}
																	}

																	@Override
																	public void keyReleased(KeyEvent e) {

																	}
																}
														);
														break;
													case KeyEvent.VK_NUMPAD4:
														x = 4;
														label.setText("Donne le Y de la case pour utiliser");
														Helico.addKeyListener(

																new KeyListener() {
																	int y = 0;

																	@Override
																	public void keyTyped(KeyEvent e) {

																	}

																	@Override
																	public void keyPressed(KeyEvent e) {
																		switch (e.getKeyCode()) {
																			case KeyEvent.VK_NUMPAD1:
																				y = 1;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD2:
																				y = 2;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD3:
																				y = 3;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD4:
																				y = 4;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD5:
																				y = 5;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD6:
																				y = 6;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																		}
																	}

																	@Override
																	public void keyReleased(KeyEvent e) {

																	}
																}
														);
														break;
													case KeyEvent.VK_NUMPAD5:
														x = 5;
														label.setText("Donne le Y de la case pour utiliser");
														Helico.addKeyListener(

																new KeyListener() {
																	int y = 0;

																	@Override
																	public void keyTyped(KeyEvent e) {

																	}

																	@Override
																	public void keyPressed(KeyEvent e) {
																		switch (e.getKeyCode()) {
																			case KeyEvent.VK_NUMPAD1:
																				y = 1;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD2:
																				y = 2;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD3:
																				y = 3;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD4:
																				y = 4;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD5:
																				y = 5;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD6:
																				y = 6;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																		}
																	}

																	@Override
																	public void keyReleased(KeyEvent e) {

																	}
																}
														);
														break;
													case KeyEvent.VK_NUMPAD6:
														x = 6;
														label.setText("Donne le Y de la case pour utiliser");
														Helico.addKeyListener(

																new KeyListener() {
																	int y = 0;

																	@Override
																	public void keyTyped(KeyEvent e) {

																	}

																	@Override
																	public void keyPressed(KeyEvent e) {
																		switch (e.getKeyCode()) {
																			case KeyEvent.VK_NUMPAD1:
																				y = 1;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD2:
																				y = 2;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD3:
																				y = 3;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD4:
																				y = 4;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD5:
																				y = 5;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																			case KeyEvent.VK_NUMPAD6:
																				y = 6;
																				if (modele.getCas(x, y).etat <= 1) {
																					int o = modele.getJ_actuel().getC().getX();
																					int p = modele.getJ_actuel().getC().getY();
																					modele.getCas(o, p).supprimer_joueur(modele.getJ_actuel());
																					modele.getCas(x, y).ajouter_joueur(modele.getJ_actuel());
																					modele.getJ_actuel().supprimer_ActionSpeHelico();
																					label.setText("Case " + x + " " + y );
																				} else {
																					label.setText("Impossible !");
																					x = 0;
																					y = 0;
																				}
																				break;
																		}
																	}

																	@Override
																	public void keyReleased(KeyEvent e) {

																	}
																}
														);
														break;
												}
											}

											@Override
											public void keyReleased(KeyEvent e) {

											}
										}
								);
							}
						}
					}
				}
		);*/
        this.add(panelC);
        this.add(label);
        JListenner jtl = new JListenner(modele,button,ctrl,label);
        button.addKeyListener(jtl);
    }
}