package DaoBDD;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import IDAO.IDomaine;
import beans.Categorie;
import beans.Categories;
import beans.Produit;

public class DomaineXML implements IDomaine {

	@Override
	public Categories getAllCategories(boolean b) { // bool ?????
		Categories ret = new Categories();
		try {

			File XmlFile = new File("./utils/tester.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);

			// facultatif
			doc.getDocumentElement().normalize();

			// System.out.println("Root element :" +
			// doc.getDocumentElement().getNodeName()); // Company

			// NodeList nList = doc.getElementsByTagName("staff"); // tableau de staff

			NodeList nList = doc.getElementsByTagName("categorie"); // tableau de cat

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("codeCategorie : " + eElement.getAttribute("codeCategorie"));
					int codeCategorie = Integer.parseInt(eElement.getAttribute("codeCategorie"));
					System.out.println("nomCategorie : " + eElement.getAttribute("nomCategorie"));
					String nomCategorie = eElement.getAttribute("nomCategorie");
					// System.out.println("First Name : " +
					// eElement.getElementsByTagName("firstname").item(0).getTextContent());

					Categorie c = new Categorie(codeCategorie, nomCategorie);

					ret.add(c);
				}
			}
			// Produit p = new Produit(codeP, nomP, prixP, codeC);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public Categorie getCategorie(int i) {

		File XmlFile = new File("./utils/tester.xml");
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);

			// facultatif
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("categorie"); // tableau de categorie
			
			Node nNode = nList.item(i); // categorie à l'index i
			Element eElement = (Element) nNode;
			// récuperation des champs :
			int codeCategorie = Integer.parseInt(eElement.getAttribute("codeCategorie"));
			String nomCategorie = eElement.getAttribute("nomCategorie");
			// le retour :
			return new Categorie(codeCategorie, nomCategorie);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

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
