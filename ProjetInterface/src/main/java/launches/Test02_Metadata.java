package launches;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Test02_Metadata {

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
			ResultSetMetaData rsmd=rs.getMetaData();
			// Accès aux méta-données
			int nbChamps=rsmd.getColumnCount();
			System.out.println("Trouvé "+nbChamps+" champs :");
			for(int i=1;i<=nbChamps;i++) {
				String nc=rsmd.getColumnLabel(i);
				String tc=rsmd.getColumnTypeName(i);
				int tac=rsmd.getColumnDisplaySize(i);
				boolean ai=rsmd.isAutoIncrement(i);
				System.out.println("- "+nc+" de type "+tc+"("+tac+") "+(ai?"A-I":""));
			}
		} catch (SQLException exc) {
			System.err.println(exc.getMessage());
		}
	}

}
