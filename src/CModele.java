import java.util.*;

public class CModele extends Observable {

    public static final int HAUTEUR = 40, LARGEUR = 60;
    private Case[][] plateau;
    int joueur =1;
    private ArrayList<Joueur> joueurs=new ArrayList<Joueur>(4);// il y a exactement 4 joueurs
    private Joueur j_actuel;

    public CModele() {
        plateau = new Case[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                plateau[i][j] = new Case(this, i, j);
            }
        }
        init();
    }
    public void init() {
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                plateau[i][j].etat = 0;
            }
        }
    }

    public Case getCas(int x, int y) {
        return plateau[x][y];
    }

    //return le joueur qui joue actuelement
    public Joueur getJ_actuel() {
		return j_actuel;
	}
    
	public void setJ_actuel(Joueur j_actuel) {
		this.j_actuel = j_actuel;
	}
	
	public Case[][] getPlatreau() {
        return plateau;
    }

    public void MAJ() {
        notifyObservers();
    }
    
    public void set_joueurs() {
    	//creer des joueurs et les mettre sur les cases
    	Joueur j_1=new Joueur(this, this.getCas(5,5), "joueurs_1");
    	Joueur j_2=new Joueur(this, this.getCas( 4,9), "joueurs_2");
    	Joueur j_3=new Joueur(this, this.getCas(7,3), "joueurs_3");
    	Joueur j_4=new Joueur(this, this.getCas(12,4), "joueurs_4");
    	joueurs.add(j_1);
    	joueurs.add(j_2);
    	joueurs.add(j_3);
    	joueurs.add(j_4);
    	j_actuel=j_1; // par defaut le joueur 1 commence
    	//le joeur commence avec 3 actions par defaut
    	j_actuel.maj_action();
    	
    
    	
    }
    

    public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
    
    //renvoie le joueur suivant du joueur actuel
	public void j_suivant() {
    	int ind=joueurs.indexOf(j_actuel);
    	ind++;
    	if (ind ==joueurs.size()) {
    		ind=0;
    	}
    	j_actuel=joueurs.get(ind);
    	// le joueur suivant commence avec 3 act
    	j_actuel.maj_action();
    	
    }
    	
    }
	

//tuile
class Case {
 private CModele modele;
 protected int etat; // 0: Normale  1: Innondée  2: Submergée 
 private final int x, y;
 private int joueur = 0; // 0 : pas de joueur  1: j1 2: j2  3: j3 4: j4   max J 4
 private ArrayList<Joueur> joueurs = new ArrayList(); //max 4
 

 public Case(CModele modele, int x, int y) {
     this.modele = modele;
     this.etat = 0;
     this.x = x; this.y = y;
     
 }

 public int GetEtat(){
     return this.etat;
 }

 public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setJoueur(int nbr){
     this.joueur = nbr;  }

 public int getJoueur(){
     return this.joueur;
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
 
}

class Joueur {
	
	private int nb_act;
	private Case c;
	private String nom_joueur;
	private boolean tour=false;
	private CModele modele;
	public void assecher(String direction) {
		
		switch(direction) {
		case "current":
			Case current = this.getC();
			if(current.GetEtat()>0) {
				current.setEtat(current.GetEtat()-1);
			}
			break;
		case "left":
			current = this.getC();
			int x=current.getX();
			int y=current.getY();
			if (x-1>0) {
			 Case left=modele.getCas(x-1, y);
			if(left.GetEtat()>0) {
				left.setEtat(left.GetEtat()-1);
			}
			}
			break;
		case "right":
			current = this.getC();
			 x=current.getX();
			 y=current.getY();
			if (x+1<modele.LARGEUR) {
			 Case right=modele.getCas(x+1, y);
			if(right.GetEtat()>0) {
				right.setEtat(right.GetEtat()-1);
			}
			}
			break;
		case "up":
			current = this.getC();
			 x=current.getX();
			 y=current.getY();
			if (y-1>0) {
			 Case up=modele.getCas(x, y-1);
			if(up.GetEtat()>0) {
				up.setEtat(up.GetEtat()-1);
			}
			}
			break;
		case "down":
			current = this.getC();
			 x=current.getX();
			 y=current.getY();
			if (y+1>modele.HAUTEUR) {
			 Case down=modele.getCas(x, y+1);
			if(down.GetEtat()>0) {
				down.setEtat(down.GetEtat()-1);
			}
			}
			break;
			
		}
	}
	public Joueur(CModele modele, Case c, String nom_joueur) {
		this.c = c;
		this.c.ajouter_joueur(this);
		this.nom_joueur = nom_joueur;
		this.modele = modele;
	}

	public int getNb_act() {
		return nb_act;
	}

	public void setNb_act(int nb_act) {
		this.nb_act = nb_act;
	}

	public Case getC() {
		return c;
	}

	public void setC(Case c) {
		this.c = c;
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
		nb_act=3;
	}
	public void action_moins() {
     nb_act--;
 }
	
	
}

	
	
