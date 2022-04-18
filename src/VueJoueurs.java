import javax.swing.*;
import java.awt.*;
public class VueJoueurs extends JPanel implements Observer{
    private CModele modele;

    GridLayout grid = new GridLayout(4, 0);
    GridLayout grid2 = new GridLayout(4, 0);

    private JLabel labelJ1 = new JLabel();
    private JLabel labelJ2 = new JLabel();
    private JLabel labelJ3 = new JLabel();
    private JLabel labelJ4 = new JLabel();
    private JLabel labelCJ1 = new JLabel();
    private JLabel labelCJ2 = new JLabel();
    private JLabel labelCJ3 = new JLabel();
    private JLabel labelCJ4 = new JLabel();
    private JPanel panelJ = new JPanel();
    private JPanel panelC = new JPanel();

    private String s;
    public VueJoueurs (CModele modele){
        panelJ.setLayout(grid);
        panelC.setLayout(grid2);
        this.modele=modele;
        grid.setVgap(50);
        modele.addObserver(this);

        panelJ.add(labelJ1);
        panelC.add(labelCJ1);
        panelJ.add(labelJ2);
        panelC.add(labelCJ2);
        panelJ.add(labelJ3);
        panelC.add(labelCJ3);
        panelJ.add(labelJ4);
        panelC.add(labelCJ4);
        //panelJ.add(panelC);
        this.add(panelJ);
        this.update();
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        paint(g,modele.getJoueurs().get(0),labelJ1,labelCJ1);
        paint(g,modele.getJoueurs().get(1),labelJ2,labelCJ2);
        paint(g,modele.getJoueurs().get(2),labelJ3,labelCJ3);
        paint(g,modele.getJoueurs().get(3),labelJ4,labelCJ4);
    }

    private void paint(Graphics g, Joueur j, JLabel label, JLabel labelC) {

        label.setText(j.getNom_joueur()+ "    Action : "+j.getNb_act()+ "    Artefacts : "+ j.getArtefacts() +"    Cle : "+j.getNb_cleA());
                //" E = "+j.getNb_cleE()+
                //" T = "+j.getNb_cleT()+
                //" F = "+j.getNb_cleF());

        s = modele.getJ_actuel().getNom_joueur();
        if (s == "J1") {
            labelJ4.setForeground(Color.BLACK);
            labelJ1.setForeground(Color.GREEN);
        }else if (s == "J2") {
            labelJ1.setForeground(Color.BLACK);
            labelJ2.setForeground(Color.GREEN);
        }else if (s == "J3") {
            labelJ2.setForeground(Color.BLACK);
            labelJ3.setForeground(Color.GREEN);
        }else if (s == "J4") {
            labelJ3.setForeground(Color.BLACK);
            labelJ4.setForeground(Color.GREEN);
        }
    }
}



