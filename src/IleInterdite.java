import IG.Fenetre;
import IG.ZoneCliquable;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class IleInterdite {
    public static void main(String[] args){
       Fenetre fenetre = new Fenetre("Ile Interdite MAILLE KEMICHE");
       Plateau p = new Plateau(10);
       Bouton bouton = new Bouton(p);

       fenetre.ajouteElement(p);
       fenetre.ajouteElement(bouton);
       fenetre.dessineFenetre();
    }
}
