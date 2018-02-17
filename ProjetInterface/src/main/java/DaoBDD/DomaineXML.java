package DaoBDD;

import java.io.File;
import java.io.IOException;
import java.util.List;

import beans.Produit;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;



import IDAO.IDomaine;
import beans.Categorie;
import beans.Categories;

public class DomaineXML implements IDomaine {
    private String PATH = "utils/";

	@Override
	public Categories getAllCategories(boolean b) {

		Categories returnArray = new Categories();
		try {
		    // Get all Categories & Products from XML file
			File catFile = new File(PATH + "Categorie.xml");
            File prodFile = new File(PATH + "Produit.xml");

			SAXBuilder catBuilder = new SAXBuilder();
            SAXBuilder prodBuilder = new SAXBuilder();

			Document catDoc = catBuilder.build(catFile);
			Document prodDoc = prodBuilder.build(prodFile);

			Element catElement = catDoc.getRootElement();
			Element prodElement = prodDoc.getRootElement();

			List<Element> listCategorie = catElement.getChildren();
            List<Element> listProduit = prodElement.getChildren();

			for (int temp = 0; temp < listCategorie.size(); temp++) {
				Element cat = listCategorie.get(temp);
                int intCat = Integer.parseInt(cat.getChild("codeCategorie").getText());
                String strCat = cat.getChild("nomCategorie").getText();
                Categorie tmpCat = new Categorie(intCat, strCat);
                returnArray.add(tmpCat);
			}

            for (int temp = 0; temp < listCategorie.size(); temp++) {
                Element cat = listProduit.get(temp);
                int intProd = Integer.parseInt(cat.getChild("codeProduit").getText());
                String strProd = cat.getChild("nomProduit").getText();
                float floatPrix = Float.parseFloat(cat.getChild("prixProduit").getText());
                int intprodCat = Integer.parseInt(cat.getChild("codeCategorie").getText());

                Produit p = new Produit(intProd, strProd, floatPrix, intprodCat);
                returnArray.get(intprodCat - 1).ajouteProduit(p);
            }
		} catch(JDOMException e) {
			e.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}

        return returnArray;
	}

	@Override
	public Categorie getCategorie(int i) {
	    try {
            File catFile = new File(PATH + "Categorie.xml");
            SAXBuilder catBuilder = new SAXBuilder();
            Document catDoc = catBuilder.build(catFile);
            Element catElement = catDoc.getRootElement();
            List<Element> listCategorie = catElement.getChildren();

            Element cat = listCategorie.get(i);
            int intCat = Integer.parseInt(cat.getChild("codeCategorie").getText());
            String strCat = cat.getChild("nomCategorie").getText();
            Categorie tmpCat = new Categorie(intCat, strCat);

            return  tmpCat;
        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
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
