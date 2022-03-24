package IG;

import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel {

    public Grille (int hauteur, int largeur){
        setLayout(new GridLayout(hauteur, largeur, 3, 3));
    }
    public void ajouteElement (JComponent element) {
        this.add(element);
    }
}
