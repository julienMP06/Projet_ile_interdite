import java.awt.*;

//IleInterdite15
public class App {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            CModele modele = new CModele();
            //mettre les joueurs sur place
            modele.set_joueurs();
            CVue vue = new CVue(modele);
            
        });
    }
}