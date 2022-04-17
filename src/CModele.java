import java.awt.*;
import java.util.*;

public class CModele extends Observable {

	public static final int HAUTEUR = 6, LARGEUR = 6;
	private Case[][] plateau;
	int joueur = 1;
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>(4);// il y a exactement 4 joueurs
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
		ZoneSpéciale();
		TourIles();
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
		Joueur j_1 = new Joueur(this, this.getCas(3, 1), "joueurs_1");
		Joueur j_2 = new Joueur(this, this.getCas(6, 3), "joueurs_2");
		Joueur j_3 = new Joueur(this, this.getCas(4, 6), "joueurs_3");
		Joueur j_4 = new Joueur(this, this.getCas(1, 4), "joueurs_4");
		joueurs.add(j_1);
		joueurs.add(j_2);
		joueurs.add(j_3);
		joueurs.add(j_4);
		j_actuel = j_1; // par defaut le joueur 1 commence
		//le joeur commence avec 3 actions par defaut
		j_actuel.maj_action();


	}
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	//renvoie le joueur suivant du joueur actuel
	public void j_suivant() {
		int ind = joueurs.indexOf(j_actuel);
		ind++;
		if (ind == joueurs.size()) {
			ind = 0;
		}
		j_actuel = joueurs.get(ind);
		// le joueur suivant commence avec 3 act
		j_actuel.maj_action();

	}

	public void TourIles() {
		/*On innonde les bords de la grille pour avoir la bonne forme de l'ile*/
		this.getCas(1, 1).etat = 2;
		this.getCas(2, 1).etat = 2;
		this.getCas(1, 2).etat = 2;

		this.getCas(6, 1).etat = 2;
		this.getCas(5, 1).etat = 2;
		this.getCas(6, 2).etat = 2;

		this.getCas(1, 6).etat = 2;
		this.getCas(1, 5).etat = 2;
		this.getCas(2, 6).etat = 2;

		this.getCas(6, 6).etat = 2;
		this.getCas(5, 6).etat = 2;
		this.getCas(6, 5).etat = 2;
	}

	public void ZoneSpéciale() {
		/*On place aléatoirement l'héliport et les 4 artefacts*/
		int x =(int) (Math.random()*(5-2)) + 2;
		int y =(int) (Math.random()*(5-2)) + 2;
		if (this.getCas(x, y).etat != 0) {
			while (this.getCas(x, y).etat != 0) {
				x = (int) (Math.random() * (5 - 2)) + 2;
				y = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		System.out.println("x"+x+"y"+y+this.getCas(x,y).etat);
		this.getCas(x,y).etat = 10;

		int x1 =(int) (Math.random()*(5-2)) + 2;
		int y1 =(int) (Math.random()*(5-2)) + 2;
		if (this.getCas(x1, y1).etat != 0) {
			while (this.getCas(x1, y1).etat != 0) {
				x1 = (int) (Math.random() * (5 - 2)) + 2;
				y1 = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		this.getCas(x1,y1).etat = 21;
		System.out.println("x"+x1+"y"+y1);

		int x2 =(int) (Math.random()*(5-2)) + 2;
		int y2 =(int) (Math.random()*(5-2)) + 2;
		if (this.getCas(x2, y2).etat != 0) {
			while (this.getCas(x2, y2).etat != 0) {
				x2 = (int) (Math.random() * (5 - 2)) + 2;
				y2 = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		this.getCas(x2,y2).etat = 22;
		System.out.println("x"+x2+"y"+y2);

		int x3 =(int) (Math.random()*(5-2)) + 2;
		int y3 =(int) (Math.random()*(5-2)) + 2;
		if (this.getCas(x3, y3).etat != 0) {
			while (this.getCas(x3, y3).etat != 0) {
				x3 = (int) (Math.random() * (5 - 2)) + 2;
				y3 = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		this.getCas(x3,y3).etat = 23;
		System.out.println("x"+x3+"y"+y3);

		int x4 =(int) (Math.random()*(5-2)) + 2;
		int y4 =(int) (Math.random()*(5-2)) + 2;
		if (this.getCas(x4, y4).etat != 0) {
			while (this.getCas(x4, y4).etat != 0) {
				x4 = (int) (Math.random() * (5 - 2)) + 2;
				y4 = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		this.getCas(x4,y4).etat = 24;
		System.out.println("x"+x4+"y"+y4);

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
	 this.joueur = nbr;
 }
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

	public boolean contient_Artefacts() {
		return !(joueurs.isEmpty());
	}
}
class Joueur {
	private int nb_act;
	private int cle;
	private Case c;

	private int Artefacts;
	private String nom_joueur;
	private boolean tour=false;
	private CModele modele;
	public Joueur(CModele modele, Case c, String nom_joueur) {
		this.c = c;
		this.c.ajouter_joueur(this);
		this.nom_joueur = nom_joueur;
		this.cle = 0;
		this.Artefacts =0;
		this.modele = modele;
	}

	public int getNb_act() {
		return nb_act;
	}

	public int getNb_cle() {
		return cle;
	}

	public void ajoute_Cle() {
		float x = (float) Math.random();
		if (x >= 0.5) {
			this.cle += 1;
		}
		System.out.println(cle);
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

	public void set_Art(int x){this.Artefacts=x;}

	public int getArtefacts(){return this.Artefacts;}
}

class Artefacts{

	private Case c;

	private String nom_artefact;

	private CModele modele;

	public Artefacts(CModele modele, Case c, String nom_artefact) {
		this.c = c;
		this.nom_artefact = nom_artefact;
		this.modele = modele;
	}

	public String Get_Nom(){return nom_artefact;}

	public Case getC() {
		return c;
	}
}
