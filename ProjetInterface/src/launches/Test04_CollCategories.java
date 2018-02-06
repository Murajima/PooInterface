package launches;

import moteur.CollCategories;

public class Test04_CollCategories {
	public static void entete(String libelle) {
		System.out.println("\n*******************************");
		System.out.println(libelle);
		System.out.println("-------------------------------");
	}
	public static void main(String[] args) {
		CollCategories cc=new CollCategories();
		entete("Affichage initial");
		System.out.println(cc.getCategorieCourante());
		System.out.println(cc.infosCurseur());
		entete("On tente le précédent");
		cc.precedent();
		System.out.println(cc.getCategorieCourante());
		System.out.println(cc.infosCurseur());
		entete("On tente le suivant");
		cc.suivant();
		System.out.println(cc.getCategorieCourante());
		System.out.println(cc.infosCurseur());
		entete("On tente le dernier");
		cc.dernier();
		System.out.println(cc.getCategorieCourante());
		System.out.println(cc.infosCurseur());
		entete("On tente le suivant");
		cc.suivant();
		System.out.println(cc.getCategorieCourante());
		System.out.println(cc.infosCurseur());
		entete("On tente le précédent");
		cc.precedent();
		System.out.println(cc.getCategorieCourante());
		System.out.println(cc.infosCurseur());
	}

}
