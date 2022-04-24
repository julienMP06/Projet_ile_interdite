package Modele;

import java.util.ArrayList;

public class Joueur {

    private int nb_act;
    private Case c;
    private String nom_joueur;
    private boolean tour = false;
    private CModele modele;
    private ArrayList<Artefact> artefacts = new ArrayList<Artefact>(4);
    private ArrayList<Cle> cles = new ArrayList<Cle>();

    private ArrayList<ActionSpe> ActionSp = new ArrayList<ActionSpe>();


    public Joueur(CModele modele, Case c, String nom_joueur) {
        this.c = c;
        this.c.ajouter_joueur(this);
        this.nom_joueur = nom_joueur;
        this.modele = modele;
    }

    //? utiliser pour fin de tour pour qu'il y est 50% de chance que le joueur re?oit la cle d'un artefact

    public void ajoute_Cle() {
        float x = (float) Math.random();
        if (x >= 0.5) {
            String nom_cle = "";
            float y = (float) Math.random();
            if (y <= 0.25) {
                nom_cle = "Eau";
            } else if (y > 0.25 && y <= 0.50) {
                nom_cle = "Air";
            } else if (y > 0.50 && y <= 0.75) {
                nom_cle = "Feu";
            } else if (y > 0.75 && y <= 1.00) {
                nom_cle = "Terre";
            }
            Cle c = new Cle(nom_cle);
            cles.add(c);
        }
    }

    public void ajoute_ActionSpe() {
        float x = (float) Math.random();
        ActionSpe a = null;
        if (x <= 1.00) {
            float y = (float) Math.random();
            //if (y <= 0.50) {
                a = new Sac_sable();
            //} else if (y > 0.50) {
            //    a = new Heleco_act();
            //}

            if (a != null) {
                this.ActionSp.add(a);
            }
        }
    }

    public int get_nb_ActionSacSable() {
        int x = 0;
        for (ActionSpe i : ActionSp) {
            if (i.getNom() == "Sac a sable") {
                x += 1;
            }
        }
        return x;
    }

    public int get_nb_ActionHelico() {
        int x = 0;
        for (ActionSpe i : ActionSp) {
            if (i.getNom() == "Modele.Heleco") {
                x += 1;
            }
        }
        return x;
    }

    public void ajoute_cle(String nom) {
        Cle c = new Cle(nom);
        cles.add(c);
    }

    public void supprimer_cle(String c) {
        boolean b = false;
        for (Cle i : cles) {
            if (i.getNom() == c) {
                cles.remove(i);
                b = true;
                break;

            }
            if (b) break;
        }
    }

    public void supprimer_ActionSpeSable() {
        boolean b = false;
        for (ActionSpe i : ActionSp) {
            if (i.getNom() == "Sac a sable") {
                ActionSp.remove(i);
                b = true;
                break;
            }
            if (b) break;
        }
    }

    public String getNom_joueur() {
        return nom_joueur;
    }

    public void setNom_joueur(String nom_joueur) {
        this.nom_joueur = nom_joueur;
    }

    public boolean isTour() {
        return tour;
    }

    public void setTour(boolean tour) {
        this.tour = tour;
    }

    public void maj_action() {
        nb_act = 3;
    }

    public void action_moins() {
        nb_act--;
    }

    public void ajouter_artefact(Artefact a) {
        artefacts.add(a);
    }

    public String noms_artefacts_possession() {
        String s = "";
        for (Artefact i : artefacts) {
            s = s + i.getNom();
        }
        return s;
    }


    public String noms_cles_possession() {
        String s = "";
        for (Cle i : cles) {
            s = s + i.getNom();
        }
        return s;
    }

    public int get_nb_cle(String nom) {
        int x = 0;
        for (Cle i : cles) {
            if (i.getNom() == nom) {
                x += 1;
            }
        }
        return x;
    }


    public Case getC() {
        return c;
    }

    public void setC(Case c) {
        this.c = c;
    }

    public int getNb_act() {
        return nb_act;
    }

    public void setNb_act(int nb_act) {
        this.nb_act = nb_act;
    }

    public ArrayList<Artefact> getArtefacts() {
        return artefacts;
    }

    public boolean noye() {
        return (this.getC().GetEtat() > 1) && (!(this.getC().CaseAdjacenteLibre()));
    }

    public boolean contient_action_speciales() {
        return !(ActionSp.isEmpty());
    }

    public boolean Sac_a_sable(int x, int y) {
        // proprite permettant d assecher n importe quelle case non-submerg�e du plateau
        // la fonction renvoie vrai si cette action est faite quand c'est possible sinon �a renvoie faux
        Case c = modele.getCas(x, y);
        if (c.GetEtat() == 1) {
            c.setEtat(c.GetEtat() - 1);
            return true;
        }
        return false;
    }

    public boolean heleco(int x, int y) {
        // proprite permettant de se deplacer � n importe quelle case non-submerg�e du plateau
        // la fonction renvoie vrai si cette action est faite quand c'est possible sinon �a renvoie faux
        // a noter cette action ne fait pas action moins au joueur qu il utilise car on l utilisera pour les autres joueurs dans la meme case
        Case c = modele.getCas(x, y);
        if (c.GetEtat() <= 1) {
            this.getC().supprimer_joueur(this);
            this.setC(c);
            c.ajouter_joueur(this);
            return true;
        }
        return false;
    }

    public void supprimer_ActionSpe(String nom) {
        boolean b = false;
        for (ActionSpe i : ActionSp) {
            if (i.getNom() == nom) {
                ActionSp.remove(i);
                b = true;
                break;
            }
            if (b) break;
        }
    }
}
