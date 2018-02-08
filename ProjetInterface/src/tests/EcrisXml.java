package tests;

import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class EcrisXml {
	private Document doc;
	public EcrisXml() throws IOException {
		// création de la racine et construction du document
		Element racine=new Element("Table_Categorie");
		doc=new Document(racine);
		// balise "data" accrochée à la racine
		Element e_data=new Element("data");
		racine.addContent(e_data);
		
		// ajout d'une catégorie - version char d'assaut
		Element e_categorie,e_nom;
		Attribute a_code;
		
		e_categorie=new Element("categorie");
		e_nom=new Element("nom");
		a_code=new Attribute("code","1");
		e_nom.setText("Alcools");
		
		e_data.addContent(e_categorie);
		e_categorie.setAttribute(a_code);
		e_categorie.addContent(e_nom);
		
		// ajout d'une catégorie - méthode geek
		e_data.addContent(new Element("categorie").
				setAttribute(new Attribute("code", "2")).
				addContent(new Element("nom").setText("Gâteaux salés")));
		
		affiche(System.out);
	}
	public void affiche(OutputStream out) throws IOException
	{
		XMLOutputter sortie=new XMLOutputter(Format.getPrettyFormat());
		sortie.output(doc, out);
	}
	public static void main(String[] args) throws IOException {
		new EcrisXml();
	}

}