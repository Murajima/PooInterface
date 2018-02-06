package DaoBDD;

import IDAO.IDomaine;
import beans.Categorie;
import beans.Categories;
import beans.Produit;
import connecteurs.MySqlB2B;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DomaineBDD implements IDomaine {
    @Override
    public Categories getAllCategories(boolean b) {
        // on instancie mysqlb2b
        Categories returnArray = new Categories();
        MySqlB2B mb2b = new MySqlB2B("Produits");
        // on va chercher le resultset des catégories
        ResultSet rs = mb2b.requete("Categorie");
        // tant que
        try {
            while(rs.next()) {
                // on récupère les champs de l'enregistrement
                int code = rs.getInt("codeCategorie");
                String nom = rs.getString("nomCategorie");
                // on les ajoute à la collection
                Categorie cat = new Categorie(code, nom);
                returnArray.add(cat);
            }
            // et maintenant on va dans la table des produits
            rs = mb2b.requete("Produit");
            while(rs.next()) {
                int codeP = rs.getInt("codeProduit");
                String nomP = rs.getString("nomProduit");
                float prixP = rs.getFloat("prixProduit");
                int codeC = rs.getInt("codeCategorie");
                Produit p = new Produit(codeP, nomP, prixP, codeC);
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
    public Categorie getCategorie(int i) { // Select * From Categorie where codeCategorie = i
        ResultSet res=null;
        MySqlB2B m_Con;
        m_Con = new MySqlB2B("Produits");
        String sql="Categorie where codeCategorie = " + String.valueOf(i);
        res = m_Con.requete(sql);
        try {
            // on récupère les champs de l'enregistrement
            int code = res.getInt("codeCategorie");
            String nom = res.getString("nomCategorie");
            // on les ajoute à la collection
            Categorie cat = new Categorie(code, nom);
            return cat;
        } catch (SQLException e) {
            System.err.println("Appelle macron");
        }
        return null;
    }

    @Override
    public boolean addCategorie(String s) {
        MySqlB2B m_Con = new MySqlB2B("Produits");
        Connection co = m_Con.getM_Con();
        // détecter si sql contient une table OU une requête SELECT...

        String sql="INSERT INTO CATEGORIE VALUES (null,  "+ s + ")";

        Statement stmt;
        try {
            stmt = co.createStatement();
            stmt.executeQuery(sql);
            return true;
        } catch (SQLException exc) {
            System.err.println(getClass().getSimpleName()+
                    "requete("+sql+") : "+exc.getMessage());
        }
        return false;
    }

    @Override
    public boolean addProduit(String s, float f, int i) {
        MySqlB2B m_Con = new MySqlB2B("Produits");
        Connection co = m_Con.getM_Con();
        // détecter si sql contient une table OU une requête SELECT...

        String sql="INSERT INTO Produit VALUES (null,  "+ s + "," + String.valueOf(f) + "," + String.valueOf(i) + ")";

        Statement stmt;
        try {
            stmt = co.createStatement();
            stmt.executeQuery(sql);
            return true;
        } catch (SQLException exc) {
            System.err.println(getClass().getSimpleName()+
                    "requete("+sql+") : "+exc.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCategorie(int i) {
        MySqlB2B m_Con = new MySqlB2B("Produits");
        Connection co = m_Con.getM_Con();
        // détecter si sql contient une table OU une requête SELECT...

        String sql="DELETE FROM Categorie WHERE codeCategorie = " + String.valueOf(i);

        Statement stmt;
        try {
            stmt = co.createStatement();
            stmt.executeQuery(sql);
            return true;
        } catch (SQLException exc) {
            System.err.println(getClass().getSimpleName()+
                    "requete("+sql+") : "+exc.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteProduit(int i) {
        MySqlB2B m_Con = new MySqlB2B("Produits");
        Connection co = m_Con.getM_Con();
        // détecter si sql contient une table OU une requête SELECT...

        String sql="DELETE FROM Produit WHERE codeProduit = " + String.valueOf(i);

        Statement stmt;
        try {
            stmt = co.createStatement();
            stmt.executeQuery(sql);
            return true;
        } catch (SQLException exc) {
            System.err.println(getClass().getSimpleName()+
                    "requete("+sql+") : "+exc.getMessage());
        }
        return false;
    }
}
