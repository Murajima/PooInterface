package DaoBDD;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import IDAO.IDomaine;
import beans.Categorie;
import beans.Categories;

public class DomaineXML implements IDomaine {

	@Override
	public Categories getAllCategories(boolean b) { // bool ?????

		// http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/

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
		return null;
	}

	@Override
	public boolean addCategorie(String s) {
		// https://www.mkyong.com/java/how-to-modify-xml-file-in-java-dom-parser/

		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			// domFactory.setIgnoringComments(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(new File("./utils/tester.xml"));

			NodeList nodes = doc.getElementsByTagName("bordeaux");
			//NodeList nodes = doc.getElementsByTagName("staff");

			Text a = doc.createTextNode("val");
			Element p = doc.createElement("categorie");
			p.setAttribute("codeCategorie", "3");
			p.setAttribute("nomCategorie", "cucurbitacées");
			p.appendChild(a);

			nodes.item(0).appendChild(p); // insert avant la fin de la liste staff
			// nodes.item(0).getParentNode().insertBefore(p, nodes.item(0)); // insert avant le début de la liste staff

			// append a new node to staff n° 1
			Element age = doc.createElement("age");
			age.appendChild(doc.createTextNode("28"));
			// nodes.item(0).appendChild(age);

			// update staff attribute
			NamedNodeMap attr = nodes.item(0).getAttributes();
			Node nodeAttr = attr.getNamedItem("id");
			// nodeAttr.setTextContent("2");

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("./utils/tester.xml"));
			transformer.transform(source, result);

			System.out.println("Done");

		} catch (Exception e) {
			// TODO: handle exception
		}

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
