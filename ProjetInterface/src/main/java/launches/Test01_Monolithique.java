package launches;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test01_Monolithique {

	public static void main(String[] args) {
		// créer un connecteur
		try {
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://localhost/produitsB?useSSL=false", "b2b", "b2b");
			// requête
			Statement stmt=con.createStatement();
			String sql="SELECT codeProduit, nomProduit, prixProduit, nomCategorie "
					+ "FROM Categorie JOIN Produit USING(codeCategorie)";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				int c=rs.getInt("codeProduit");
				String np=rs.getString("nomProduit");
				String nc=rs.getString("nomCategorie");
				float pp=rs.getFloat("prixProduit");
				System.out.println("- "+c+" : "+np+" : "+pp+" € ("+nc+")");
			}
		} catch (SQLException exc) {
			System.err.println(exc.getMessage());
		}
	}

}
