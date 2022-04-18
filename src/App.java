import java.awt.*;

//IleInterdite
public class App {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            CModele modele = new CModele();
            //mettre les joueurs sur place
            modele.set_joueurs();
            modele.set_artefacts();
            CVue vue = new CVue(modele);
            
        });
    }
}