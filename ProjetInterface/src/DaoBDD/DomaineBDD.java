package DaoBDD;

import IDAO.IDomaine;
import beans.Categorie;
import beans.Categories;
import beans.Produit;
import connecteurs.MySqlB2B;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DomaineBDD implements IDomaine {
    @Override
    public Categories getAllCategories(boolean b) {
        // on instancie mysqlb2b
        Categories returnArray = new Categories();
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
                returnArray.add(cat);
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
        if (!returnArray.isEmpty()){
            return returnArray;
        } else {
            return null;
        }
    }

    @Override
    public Categorie getCategorie(int i) {
        return null;
    }

    @Override
    public boolean addCategorie(String s) {
        return false;
    }

    @Override
    public boolean addProduit(String s, float f, int i) {
        return false;
    }

    @Override
    public boolean deleteCategorie(int i) {
        return false;
    }

    @Override
    public boolean deleteProduit(int i) {
        return false;
    }
}
