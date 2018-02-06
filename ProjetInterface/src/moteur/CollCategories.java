package moteur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Categorie;
import beans.Produit;
import connecteurs.MySqlB2B;

@SuppressWarnings("serial")
public class CollCategories extends ArrayList<Categorie>{
	private int curseur=0;
	public CollCategories() {
		// on instancie mysqlb2b
		MySqlB2B mb2b=new MySqlB2B("Produits");
		// on va chercher le resultset des catégories
		ResultSet rs=mb2b.requete("Categorie");
		// tant que
		try {
			while(rs.next()) {
				// on récupère les champs de l'enregistrement
				int code=rs.getInt("codeCategorie");
				String nom=rs.getString("nomCategorie");
				// on les ajoute à la collection
				Categorie cat=new Categorie(code, nom);
				this.add(cat);
			}
			// et maintenant on va dans la table des produits
			rs=mb2b.requete("Produit");
			while(rs.next()) {
				int codeP=rs.getInt("codeProduit");
				String nomP=rs.getString("nomProduit");
				float prixP=rs.getFloat("prixProduit");
				int codeC=rs.getInt("codeCategorie");
				Produit p=new Produit(codeP, nomP, prixP, codeC);
				getCategorie(codeC).ajouteProduit(p);
			}
		} catch (SQLException e) {
			System.err.println("Appelle macron");
		}
	}
	public void ajouteCategorie(String nom) {
		// on regarde tout d'abord quel sera le code de cette catégorie
		int code=0;
		for(Categorie c:this)
			if(c.getCodeCategorie()>code)
				code=c.getCodeCategorie();
		code++;
		// on ajoute l'enregistrement 
		this.add(new Categorie(code, nom));
	}

	public void ajouteProduit(String nom, float prix, int codeCategorie) {
		// code du produit : il faut parser tous les produits de toutes les catégorieq
		int code=0;
		for(Categorie c:this)
			for(Produit p:c.getListeProduits())
				if(p.getCodeProduit()>code)
					code=p.getCodeProduit();
		code++;
		getCategorie(codeCategorie).ajouteProduit(new Produit(code, nom, prix, codeCategorie));
	}
	
	private Categorie getCategorie(int code) {
		for(Categorie c:this)
			if(c.getCodeCategorie()==code)
				return c;
		return null;
	}
	public Categorie getCategorieCourante() {
		return this.get(curseur);
	}
	public void premier() {
		curseur=0;
	}
	public void precedent() {
		if(curseur>0)
			curseur--;
	}
	public void suivant() {
		if(curseur<this.size()-1)
			curseur++;
	}
	public void dernier() {
		curseur=this.size()-1;
	}
	public String infosCurseur() {
		return (curseur+1)+" / "+this.size();
	}
	public boolean estPremier() {
		return curseur==0;
	}
	public boolean estDernier() {
		return curseur==this.size()-1;
	}
}
