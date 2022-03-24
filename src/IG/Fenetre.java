package IG;
import javax.swing.*;
//import javax.swing.JComponent;

public class Fenetre extends JFrame {
    private JPanel elements;

    public Fenetre(String nom){
        super(nom);
        this.setSize(800,600);
        this.elements = new JPanel();
        this.add(elements);
    }

    public void ajouteElement(JComponent element) {
        elements.add(element);
    }

    public void dessineFenetre() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
