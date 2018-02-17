package moteur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoBDD.DomaineBDD;
import DaoBDD.DomaineXML;
import IDAO.IDomaine;
import beans.Categorie;
import beans.Categories;
import beans.Produit;
import connecteurs.MySqlB2B;

@SuppressWarnings("serial")
public class CollCategories {
	private int curseur=0;
	private IDomaine domaine;
	private Categories categories;
	public CollCategories() {
		// domaine=new DomaineBDD();
		domaine=new DomaineXML();
		categories=domaine.getAllCategories(true);
	}
	public void ajouteCategorie(String nom) {
		domaine.addCategorie(nom);
		categories=domaine.getAllCategories(true);
	}

	public void ajouteProduit(String nom, float prix, int codeCategorie) {
		domaine.addProduit(nom, prix, codeCategorie);
		categories=domaine.getAllCategories(true);
	}
	
	
	public Categorie getCategorieCourante() {
		return categories.get(curseur);
	}
	public void premier() {
		curseur=0;
	}
	public void precedent() {
		if(curseur>0)
			curseur--;
	}
	public void suivant() {
		if(curseur<categories.size()-1)
			curseur++;
	}
	public void dernier() {
		curseur=categories.size()-1;
	}
	public String infosCurseur() {
		return (curseur+1)+" / "+categories.size();
	}
	public boolean estPremier() {
		return curseur==0;
	}
	public boolean estDernier() {
		return curseur==categories.size()-1;
	}
}
