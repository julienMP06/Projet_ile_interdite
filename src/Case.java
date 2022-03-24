import IG.Zone;

import javax.swing.*;
import java.awt.*;

public class Case extends Zone {

    protected int etat;
    private Plateau plateau;

    public Case(Plateau p){
        super(50,50);
        this.plateau = p;
    }

    public void ChangementEtat(){
        if (this.etat == 1){
            setBackground(Color.CYAN);
        }
        if (this.etat == 2){
            setBackground(Color.BLUE);
        }
    }
}
