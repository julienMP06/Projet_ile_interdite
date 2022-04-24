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

	/**
	 * Cette class gère tous les boutons de la création au placement dans la seconde partie de notre fenetre
	 * en haut a droite.
	 */
    private CModele modele;
	GridLayout grid = new GridLayout(7, 0);
	JTextField EntrerX = new JTextField("Entre X");
	JTextField EntrerY = new JTextField("Entre Y");
	JButton valider = new JButton("Valider");

	JCheckBox oui = new JCheckBox("Emporter",false);

	JButton validerEchange = new JButton("Valider");

	JTextField CleEchange = new JTextField("Cle A E T F");
	JLabel label = new JLabel();
	public VueCommandes(CModele modele, JFrame frameMenu, JFrame frame) {

        JPanel panelC = new JPanel();

        panelC.setLayout(grid);
        grid.setHgap(10);
        grid.setVgap(15);
        this.modele=modele;

		JLabel labelSpace = new JLabel("       ");

        JButton boutonAvance = new JButton("Fin De Tour");
        Controleur ctrl = new Controleur(modele, label,panelC,EntrerX,EntrerY,valider,oui,validerEchange,CleEchange);
        boutonAvance.addActionListener(ctrl);


        JButton button = new JButton("Deplacement");


        JButton buttonAsseche = new JButton("Assecher");
		ControleurAsseche ctrlAss = new ControleurAsseche(modele, label,panelC,EntrerX,EntrerY,valider,oui,validerEchange,CleEchange);
		buttonAsseche.addKeyListener(ctrlAss);

        JButton buttonRecup = new JButton("Recuperer");
        ControleurRecup ctrlRec = new ControleurRecup(modele, label,panelC,EntrerX,EntrerY,valider,oui,validerEchange,CleEchange);
		buttonRecup.addActionListener(ctrlRec);

        JButton EchangeCle = new JButton("Donner cle");
		ControleurEchange ctrlEch = new ControleurEchange(modele, label,panelC,EntrerX,EntrerY,valider,oui,validerEchange,CleEchange);
		EchangeCle.addActionListener(ctrlEch);

        JButton boutonEnvole = new JButton("S'envoler");
		ControleurEnvole ctrlEnv = new ControleurEnvole(modele, label);
		boutonEnvole.addActionListener(ctrlEnv);
		
		JButton SacSable= new JButton("Sac de sable");
		ControleurSable ctrlSable = new ControleurSable(modele, label,panelC,EntrerX,EntrerY,valider,oui,validerEchange,CleEchange);
		SacSable.addActionListener(ctrlSable);

		JButton Helico = new JButton("Helico");
		ControleurHelico ctrlHelico = new ControleurHelico(modele, label,panelC,EntrerX,EntrerY,valider,oui,validerEchange,CleEchange);
		Helico.addActionListener(ctrlHelico);

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