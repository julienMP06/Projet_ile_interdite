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
		// dire si l helco ou l un des artefacts est noyé
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
		if (this.getCas(x1, y1).etat != 0 || this.getCas(x1,y1).contient_artefact() || this.getCas(x1,y1).contient_heleco()) {
			while (this.getCas(x1, y1).etat != 0 || this.getCas(x1, y1).contient_artefact() || this.getCas(x1,y1).contient_heleco()) {
				x1 = (int) (Math.random() * (5 - 2)) + 2;
				y1 = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		Terre = new Artefact(this, "T",getCas(x1,y1));
		this.getCas(x1, y1).ajoute_artefact(Terre);
		int x2 =(int) (Math.random()*(5-2)) + 2;
		int y2 =(int) (Math.random()*(5-2)) + 2;
		if (this.getCas(x2, y2).etat != 0 || this.getCas(x2,y2).contient_artefact() || this.getCas(x2,y2).contient_heleco()) {
			while (this.getCas(x2, y2).etat != 0 || this.getCas(x2, y2).contient_artefact() || this.getCas(x2,y2).contient_heleco()) {
				x2 = (int) (Math.random() * (5 - 2)) + 2;
				y2 = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		Air = new Artefact(this, "A",this.getCas(x2,y2));
		this.getCas(x2, y2).ajoute_artefact(Air);
		int x3 =(int) (Math.random()*(5-2)) + 2;
		int y3 =(int) (Math.random()*(5-2)) + 2;
		if (this.getCas(x3, y3).etat != 0 || this.getCas(x3,y3).contient_artefact() || this.getCas(x3,y3).contient_heleco()) {
			while (this.getCas(x3, y3).etat != 0 || this.getCas(x3, y3).contient_artefact() || this.getCas(x3,y3).contient_heleco()) {
				x3 = (int) (Math.random() * (5 - 2)) + 2;
				y3 = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		Eau = new Artefact(this, "E",this.getCas(x3,y3));
		this.getCas(x3, y3).ajoute_artefact(Eau);

		int x4 =(int) (Math.random()*(5-2)) + 2;
		int y4 =(int) (Math.random()*(5-2)) + 2;
		if (this.getCas(x4, y4).etat != 0 || this.getCas(x4,y4).contient_artefact() || this.getCas(x4,y4).contient_heleco()) {
			while (this.getCas(x4, y4).etat != 0 || this.getCas(x4, y4).contient_artefact() || this.getCas(x4,y4).contient_heleco()) {
				x4 = (int) (Math.random() * (5 - 2)) + 2;
				y4 = (int) (Math.random() * (5 - 2)) + 2;
			}
		}
		Feu = new Artefact(this, "F",this.getCas(x4,y4));
		this.getCas(x4, y4).ajoute_artefact(Feu);
	}
	
	public void ZoneSpeciale() {
		/*On place aléatoirement l'héliport*/
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
		for (int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * (CModele.LARGEUR)) + 1;
            int y = (int) (Math.random() * (CModele.HAUTEUR)) + 1;
            while (getCas(x, y).GetEtat() == 2 && this.nb_tuiles_non_noyee()>=3) {
                x = (int) (Math.random() * (CModele.LARGEUR)) + 1;
                y = (int) (Math.random() * (CModele.HAUTEUR)) + 1;
            }
            getCas(x, y).setEtat(getCas(x, y).GetEtat()+1);
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

//tuile
class Case {
	
	
	 private CModele modele;
	 protected int etat; // 0: Normale  1: Innondée  2: Submergée 
	 private final int x, y;
	 private ArrayList<Joueur> joueurs = new ArrayList<Joueur>(4); //max 4
	 private ArrayList<Artefact> artefact = new ArrayList<Artefact>(1);
	 private ArrayList<Heleco> heleco = new ArrayList<Heleco>(1);
	 
	
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
	
	 /*Pour avoir les coordonnees de toutes les cases adjacentes*/
	 public Case getCaseD(){return modele.getCas(x+1,y);}
	
	 public Case getCaseG(){return modele.getCas(x-1,y);}
	
	 public Case getCaseH(){return modele.getCas(x,y-1);}
	
	 public Case getCaseB(){return modele.getCas(x-1,y+1);}
	
	 public boolean CaseAdjacenteLibre(int x, int y){
		 if (getCaseB().etat == 2 && getCaseH().etat == 2 && getCaseD().etat == 2 && getCaseG().etat == 2 ){
			 return false;
		 }
		 return true;
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
		return !(artefact.isEmpty());
	}

	public ArrayList<Artefact> get_Artefacts() {
		return artefact;
	}

	public ArrayList<Joueur> get_joueurs() {
		return joueurs;
	}

	public void ajoute_artefact(Artefact a) {
		this.artefact.add(a);
		a.setC(this);
	}
	
	public void supprime_artefact() {
		artefact.get(0).setC(null);
		this.artefact.remove(0);
	}


	public void ajoute_heleco(Heleco h) {
		this.heleco.add(h);
	}
	
	public void supprime_heleco() {
		this.heleco.remove(0);
	}
	
	public String get_artefact_nom() {
		return artefact.get(0).getNom();
	}
	
	public boolean contient_artefact() {
		return !(artefact.isEmpty());
	}
	
	public boolean contient_heleco() {
		return !(heleco.isEmpty());
	}
	

}

class Joueur {
	
	private int nb_act;
	private Case c;
	private String nom_joueur;
	private boolean tour=false;
	private CModele modele;
	private ArrayList<Artefact> artefacts = new ArrayList<Artefact>(4);
	private ArrayList<Cle> cles = new ArrayList<Cle>();
	
	
	
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
			String nom_cle="";
			float y = (float) Math.random();
			if (y <= 0.25) {
				nom_cle="Eau";
			}else if (y > 0.25 && y <= 0.50) {
				nom_cle="Air";
			}else if (y > 0.50 && y <= 0.75) {
				nom_cle="Feu";
			} else if (y > 0.75 && y <= 1.00) {
				nom_cle="Terre";
			}
			Cle c =new Cle(modele, nom_cle);
			cles.add(c);
		}
	}

	public void ajoute_cle_terre(){
		Cle c = new Cle(modele,"Terre");
		cles.add(c);
	}

	public void ajoute_cle_feu(){
		Cle c = new Cle(modele,"Feu");
		cles.add(c);
	}
	public void ajoute_cle_eau(){
		Cle c = new Cle(modele,"Eau");
		cles.add(c);
	}
	public void ajoute_cle_air(){
		Cle c = new Cle(modele,"Air");
		cles.add(c);
	}

	public void supprime_cle_air(){
		Cle c = new Cle(modele,"Air");
		cles.remove(c);
	}

	
	public void supprimer_cle(Cle c) {
		System.out.println("cle avant"+this.cles);
		System.out.println(c);
		cles.remove(c);
		System.out.println("cle apres"+this.cles);
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
	
	public void ajouter_artefact(Artefact a) {
		artefacts.add(a);
	}
	
	public void supprimer_artefact(Artefact a) {
		artefacts.remove(a);
	}
	
	public String noms_artefacts_possession() {
		String s="";
		for (Artefact i : artefacts) {
			s=s+i.getNom();
		}
		return s;
	}
	
	public String noms_cles_possession() {
		String s="";
		for (Cle i : cles) {
			s=s+i.getNom();
		}
		return s;
	}

	public int getCleFeu(){
		int x = 0;
		for (Cle i : cles) {
			if(i.getNom() == "Feu") {
				x += 1;
			}
		}
		return x;
	}

	public int getCleAir(){
		int x = 0;
		for (Cle i : cles) {
			if(i.getNom() == "Air") {
				x += 1;
			}
		}
		return x;
	}

	public int getCleTerre(){
		int x = 0;
		for (Cle i : cles) {
			if(i.getNom() == "Terre") {
				x += 1;
			}
		}
		return x;
	}

	public int getCleEau(){
		int x = 0;
		for (Cle i : cles) {
			if(i.getNom() == "Eau") {
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
		return (this.getC().GetEtat() >1) &&(this.getC().getCaseD().GetEtat()>1)&&(this.getC().getCaseG().GetEtat()>1)&&(this.getC().getCaseH().GetEtat()>1)&&(this.getC().getCaseB().GetEtat()>1);
	}
	
}



abstract class items {
	private CModele modele;
	private String nom;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public items(CModele modele, String nom) {
		super();
		this.modele = modele;
		this.nom = nom;
	}
	
}

class Artefact extends items {

	private Case c;

	public Artefact(CModele modele, String nom, Case c) {
		super(modele, nom);
		this.c = c;
	}

	public Case EstDans(){
		return this.c;
	}

	public void setC(Case c) {
		this.c = c;
	}
	

}

class Cle extends items {

	public Cle(CModele modele, String nom) {
		super(modele, nom);
	}
}

abstract class Zone_speciale {
	private String nom;

	public Zone_speciale(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}

class Heleco extends Zone_speciale {

	private Case c;

	public Heleco(Case c) {
		super("helicoptere");
		this.c = c;
	}

	public Case getCaseHelico(){
		return this.c;
	}
	
}
