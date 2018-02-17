 package beans;

import java.util.ArrayList;

public class Categorie {
	private int codeCategorie;
	private String nomCategorie;
	private ArrayList<Produit> listeProduits;
	
	public Categorie(int codeCategorie, String nomCategorie) {
		this.codeCategorie = codeCategorie;
		this.nomCategorie = nomCategorie;
		listeProduits=new ArrayList<Produit>();
	}
	
	public void ajouteProduit(Produit p) {
		listeProduits.add(p);
	}
	
	public String strListeProduits() {
		String res=listeProduits.size()+" produits :";
		for(Produit p:listeProduits)
			res+="\n"+p;
		return res;
	}

	public int getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(int codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public String toString() {
		return "["+getClass().getSimpleName()+"] ("+
				"code : "+codeCategorie+
				" ; nom : "+nomCategorie+" ; nb produits : "+listeProduits.size()+")";
	}

	public ArrayList<Produit> getListeProduits() {
		return listeProduits;
	}
}
