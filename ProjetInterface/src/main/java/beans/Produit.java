package beans;

public class Produit {
	int codeProduit;
	String nomProduit;
	float prixProduit;
	int codeCategorie;
	public Produit(int codeProduit, String nomProduit, float prixProduit, int codeCategorie) {
		this.codeProduit = codeProduit;
		this.nomProduit = nomProduit;
		this.prixProduit = prixProduit;
		this.codeCategorie = codeCategorie;
	}
	public int getCodeProduit() {
		return codeProduit;
	}
	public void setCodeProduit(int codeProduit) {
		this.codeProduit = codeProduit;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public float getPrixProduit() {
		return prixProduit;
	}
	public void setPrixProduit(float prixProduit) {
		this.prixProduit = prixProduit;
	}
	public int getCodeCategorie() {
		return codeCategorie;
	}
	public void setCodeCategorie(int codeCategorie) {
		this.codeCategorie = codeCategorie;
	}
	public String toString() {
		return String.format("%02d : %-25s\t%5.1f€", 
				codeProduit,nomProduit,prixProduit);
	}
}
