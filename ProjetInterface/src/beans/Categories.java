package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connecteurs.MySqlB2B;

@SuppressWarnings("serial")
public class Categories extends ArrayList<Categorie>{

	public Categories() {//TODO passage de int
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
		//return null;
	}
	private Categorie getCategorie(int code) {
		for(Categorie c:this)
			if(c.getCodeCategorie()==code)
				return c;
		return null;
	}
}
