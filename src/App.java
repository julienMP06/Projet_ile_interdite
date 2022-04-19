import java.awt.*;

//IleInterdite
public class App {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            CModele modele = new CModele();
            CVue vue = new CVue(modele);
            
        });
    }
}