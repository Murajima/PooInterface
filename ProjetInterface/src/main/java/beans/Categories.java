package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connecteurs.MySqlB2B;

@SuppressWarnings({"serial", "unused"})
public class Categories extends ArrayList<Categorie>{

	private Categorie getCategorie(int code) {
		for(Categorie c:this)
			if(c.getCodeCategorie()==code)
				return c;
		return null;
	}
}
