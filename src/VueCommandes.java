import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class VueCommandes extends JPanel {
    private CModele modele;

    public VueCommandes(CModele modele) {
        this.modele = modele;
        JButton boutonAvance = new JButton("Fin De Tour");
        this.add(boutonAvance);
        Controleur ctrl = new Controleur(modele);
        boutonAvance.addActionListener(ctrl);
        /*Test De Déplacement*/
        JButton button = new JButton("Déplacement");
        this.add(button);
        JListenner jtl = new JListenner(modele,button,ctrl);
        button.addKeyListener(jtl);
        JLabel label=new JLabel();
    	label.setText("   "+modele.getJ_actuel().getNom_joueur()+"\n              Nombre action restantes :"+modele.getJ_actuel().getNb_act());
        this.add(label);
        
        
        
    }
    
}
