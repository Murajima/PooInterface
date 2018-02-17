package launches;

import java.sql.ResultSet;
import java.sql.SQLException;

import connecteurs.MySqlB2B;

public class Test03_MySqlB2B {

	public static void main(String[] args) {
		MySqlB2B mb2b=new MySqlB2B("produitsB");
		// test d'insertion
		int ai=mb2b.insereAI("INSERT INTO Categorie VALUES(NULL,'Charcuterie')");
		System.out.println("La catégorie a été créée avec l'AI "+ai);
		// test de requête
		ResultSet rs=mb2b.requete("Categorie");
		try {
			while(rs.next()) {
				System.out.println("- "+rs.getInt("codeCategorie")+" "+
						rs.getString("nomCategorie"));
			}
		} catch (SQLException e) {}
		// test de suppression
		int retsup=mb2b.update("DELETE FROM Categorie WHERE codeCategorie="+ai);
		if(retsup==1)
			System.out.println("Suppression OK.");
	}

}
