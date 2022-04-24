package Modele;
import Vue.*;
import Vue.Observable;
import Vue.VueJoueurs2;

import java.util.*;


public class CModele extends Observable {

	public static final int HAUTEUR = 6, LARGEUR = 6;
	private Case[][] plateau;
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
		ZoneSpeciale();
		TourIles();
		set_joueurs();
		set_artefacts();
	}
	
	public boolean partie_gagnee() {
		//le nombre d'artefacts en possession des joueurs 
		int nb_artefact=joueurs.get(0).getArtefacts().size()+joueurs.get(1).getArtefacts().size()+joueurs.get(2).getArtefacts().size()+joueurs.get(3).getArtefacts().size();
		//tous les joueurs sont dans l'heleco
		boolean tous_helo= joueurs.get(0).getC().contient_heleco()&&joueurs.get(1).getC().contient_heleco()&&joueurs.get(2).getC().contient_heleco()&&joueurs.get(3).getC().contient_heleco();
		
		return nb_artefact==4 && tous_helo;
		
	}
	
	public boolean partie_perdue() {
		// un des joueurs noye
		System.out.print(joueurs.get(0).noye());
		boolean joueur_noye= joueurs.get(0).noye()|| joueurs.get(1).noye()|| joueurs.get(2).noye()|| joueurs.get(3).noye();
		return (joueur_noye || this.artefact_heleco_noye());
		
	}
	public boolean artefact_heleco_noye() {
		// dire si l helco ou l un des artefacts est noy�
		for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
            	Case c= getCas(i,j);
                if ((c.contient_artefact() || c.contient_heleco())&&(c.GetEtat()>1)){
                	return true;
                }
            }
        }
		return false;
	}
	


	/*Si la partie est perdue ou non */


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
		Joueur j_1 = new Joueur(this, this.getCas(3, 1), "J1");
		Joueur j_2 = new Joueur(this, this.getCas(6, 3), "J2");
		Joueur j_3 = new Joueur(this, this.getCas(4, 6), "J3");
		Joueur j_4 = new Joueur(this, this.getCas(1, 4), "J4");
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

	public void set_artefacts() {

		Artefact Feu ;
		Artefact Eau;
		Artefact Air;
		Artefact Terre;

		//creer des artefacts et les mettre sur les cases
		int x1 =(int) (Math.random()*(5-2)) + 2;
		int y1 =(int) (Math.random()*(5-2)) + 2;
		while (this.getCas(x1, y1).GetEtat() != 0 || this.getCas(x1, y1).contient_artefact() || this.getCas(x1,y1).contient_heleco()) {
			x1 = (int) (Math.random() * (5 - 2)) + 2;
			y1 = (int) (Math.random() * (5 - 2)) + 2;
		}
		Terre = new Artefact( "Terre",getCas(x1,y1));
		this.getCas(x1, y1).ajoute_artefact(Terre);
		
		
		int x2 =(int) (Math.random()*(5-2)) + 2;
		int y2 =(int) (Math.random()*(5-2)) + 2;
		while (this.getCas(x2, y2).GetEtat() != 0 || this.getCas(x2, y2).contient_artefact() || this.getCas(x2,y2).contient_heleco()) {
			x2 = (int) (Math.random() * (5 - 2)) + 2;
			y2 = (int) (Math.random() * (5 - 2)) + 2;
		}
		Air = new Artefact( "Air",this.getCas(x2,y2));
		this.getCas(x2, y2).ajoute_artefact(Air);
		
		
		int x3 =(int) (Math.random()*(5-2)) + 2;
		int y3 =(int) (Math.random()*(5-2)) + 2;
		while (this.getCas(x3, y3).GetEtat() != 0 || this.getCas(x3, y3).contient_artefact() || this.getCas(x3,y3).contient_heleco()) {
			x3 = (int) (Math.random() * (5 - 2)) + 2;
			y3 = (int) (Math.random() * (5 - 2)) + 2;
		}
		Eau = new Artefact( "Eau",this.getCas(x3,y3));
		this.getCas(x3, y3).ajoute_artefact(Eau);

		
		int x4 =(int) (Math.random()*(5-2)) + 2;
		int y4 =(int) (Math.random()*(5-2)) + 2;
		while (this.getCas(x4, y4).GetEtat() != 0 || this.getCas(x4, y4).contient_artefact() || this.getCas(x4,y4).contient_heleco()) {
			x4 = (int) (Math.random() * (5 - 2)) + 2;
			y4 = (int) (Math.random() * (5 - 2)) + 2;
		}
		Feu = new Artefact( "Feu",this.getCas(x4,y4));
		this.getCas(x4, y4).ajoute_artefact(Feu);
	}
	
	public void ZoneSpeciale() {
		/*On place aleatoirement l'h�liport*/
		int x =(int) (Math.random()*(5-2)) + 2;
		int y =(int) (Math.random()*(5-2)) + 2;
		if (this.getCas(x, y).etat != 0 || this.getCas(x,y).contient_artefact()) {
			while (this.getCas(x, y).etat != 0 || this.getCas(x,y).contient_artefact()) {
				x = (int) (Math.random() * (5 - 2)) + 2;
				y = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		Heleco H = new Heleco(this.getCas(x,y));
		this.getCas(x,y).ajoute_heleco(H);
	}



public void noyer_trois_tuiles (){
		// cette liste sera utilis�e pour evit� de monter l'eau deux fois dans un seul fin de tour
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * (CModele.LARGEUR)) + 1;
            int y = (int) (Math.random() * (CModele.HAUTEUR)) + 1;
            Point p= new Point(x,y);
            
            while (getCas(x, y).GetEtat() >= 2 && this.nb_tuiles_non_noyee()>=1 || p.In(points)) {
                x = (int) (Math.random() * (CModele.LARGEUR)) + 1;
                y = (int) (Math.random() * (CModele.HAUTEUR)) + 1;
                p= new Point(x,y);
            }
            getCas(x, y).setEtat(getCas(x, y).GetEtat()+1);
            points.add(p);
		}
		
		
	}
	
	public int nb_tuiles_non_noyee() {
		int nb=0;
		for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
            	if (this.getCas(i, j).GetEtat() <=1)  nb++;           
            }
        }
	
		return nb;
	}
}


