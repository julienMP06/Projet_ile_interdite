package Modele;

import java.util.ArrayList;

//represente tuile
public class Case {


    private CModele modele;
    private int etat; // 0: Normale  1: Innondee  2: Submergee
    private final int x, y;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>(4); //max 4
    private ArrayList<Artefact> artefact = new ArrayList<Artefact>(1);// contient 0 ou 1
    private ArrayList<Heleco> heleco = new ArrayList<Heleco>(1);//contient 0 ou 1


    public Case(CModele modele, int x, int y) {
        this.modele = modele;
        this.etat = 0;
        this.x = x;
        this.y = y;
    }

    public int GetEtat() {
        return this.etat;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*Pour avoir les coordonnees de toutes les cases adjacentes*/
    public Case getCaseD() {
        if (x + 1 < CModele.LARGEUR + 1) {
            return modele.getCas(x + 1, y);
        }
        return null;

    }

    public Case getCaseG() {
        if (x - 1 > 0) {
            return modele.getCas(x - 1, y);
        }
        return null;
    }

    public Case getCaseH() {
        if (y - 1 > 0) {
            return modele.getCas(x, y - 1);
        }
        return null;
    }

    public Case getCaseB() {
        if (y + 1 < CModele.HAUTEUR + 1) {
            return modele.getCas(x - 1, y + 1);
        }
        return null;
    }

    public boolean CaseAdjacenteLibre() {
        //verifier les cases adjacentes si elle sont libres en evitent que les bords soient compt�s-
        Case H = getCaseH(), G = getCaseG(), D = getCaseD(), B = getCaseB();
        ArrayList<Case> l = new ArrayList<Case>(4);
        // en utilisant le proprite de ET paresseux
        if (H != null && H.GetEtat() <= 1) {
            l.add(H);
        }
        if (G != null && G.GetEtat() <= 1) {
            l.add(G);
        }
        if (B != null && B.GetEtat() <= 1) {
            l.add(B);
        }
        if (D != null && D.GetEtat() <= 1) {
            l.add(D);
        }
        return !(l.isEmpty());
    }

    //ajouter joueur a la case
    public void ajouter_joueur(Joueur j) {
        joueurs.add(j);
        j.setC(this);
    }

    //dire si la case a un joueur
    public boolean contient_joueur() {
        return !(joueurs.isEmpty());
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    //supprime le joueur j de la case
    public void supprimer_joueur(Joueur j) {
        joueurs.remove(j);
        j.setC(null);
    }

    //dire si la case contient un artefact
    public boolean contient_Artefacts() {
        return !(artefact.isEmpty());
    }

    // renvoie l artefacr de la case
    public ArrayList<Artefact> get_Artefacts() {
        return artefact;
    }

    // renvoie la liste des joueurs qui sont dans la case
    public ArrayList<Joueur> get_joueurs() {
        return joueurs;
    }

    //ajoute artefact a la case
    public void ajoute_artefact(Artefact a) {
        this.artefact.add(a);
        a.setC(this);
    }

    //supprime l artefact de la case
    public void supprime_artefact() {
        artefact.get(0).setC(null);
        this.artefact.remove(0);
    }

    //ajoute un heliport a la case
    public void ajoute_heleco(Heleco h) {
        this.heleco.add(h);
    }

    // supprime l heliport de la case
    public void supprime_heleco() {
        this.heleco.remove(0);
    }

    public String get_artefact_nom() {
        return artefact.get(0).getNom();
    }

    // dire si la case contient un artefact
    public boolean contient_artefact() {
        return !(artefact.isEmpty());
    }

    //dire si la case contient un heliport
    public boolean contient_heleco() {
        return !(heleco.isEmpty());
    }


}
