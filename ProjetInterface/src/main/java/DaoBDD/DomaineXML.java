package DaoBDD;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import org.jdom2.output.XMLOutputter;

public class DomaineXML implements IDomaine {
    private String PATH = "utils/";

	@Override
	public Categories getAllCategories(boolean b) {

		Categories returnArray = new Categories();
		try {
		    // Get all Categories & Products from XML file
			File catFile = new File(PATH + "Categorie.xml");
            FileReader catReader = new FileReader(catFile);
            File prodFile = new File(PATH + "Produit.xml");
            FileReader prodReader = new FileReader(prodFile);

			SAXBuilder catBuilder = new SAXBuilder();
            SAXBuilder prodBuilder = new SAXBuilder();

			Document catDoc = catBuilder.build(catReader);
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

            for (int temp = 0; temp < listProduit.size(); temp++) {
                Element cat = listProduit.get(temp);
                int intProd = Integer.parseInt(cat.getChild("codeProduit").getText());
                String strProd = cat.getChild("nomProduit").getText();
                float floatPrix = Float.parseFloat(cat.getChild("prixProduit").getText());
                int intprodCat = Integer.parseInt(cat.getChild("codeCategorie").getText());

                Produit p = new Produit(intProd, strProd, floatPrix, intprodCat);
                returnArray.get(intprodCat - 1).ajouteProduit(p);
            }
            catReader.close();
			prodReader.close();
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
            FileReader catReader = new FileReader(catFile);
            SAXBuilder catBuilder = new SAXBuilder();
            Document catDoc = catBuilder.build(catReader);
            Element catElement = catDoc.getRootElement();
            List<Element> listCategorie = catElement.getChildren();

            Element cat = listCategorie.get(i);
            int intCat = Integer.parseInt(cat.getChild("codeCategorie").getText());
            String strCat = cat.getChild("nomCategorie").getText();
            Categorie tmpCat = new Categorie(intCat, strCat);
            catReader.close();
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
        try {
            XMLOutputter out = new XMLOutputter();

            File catFile = new File(PATH + "Categorie.xml");
            FileReader catReader = new FileReader(catFile);

            SAXBuilder catBuilder = new SAXBuilder();
            Document CategorieDoc = catBuilder.build(catReader);

            Element catElement = CategorieDoc.getRootElement();
            List<Element> listCategorie = catElement.getChildren();

            Element e_categorie = new Element("Categorie");
            Element e_code = new Element("codeCategorie");
            Element e_nom = new Element("nomCategorie");

            e_code.setText(String.valueOf(listCategorie.size() + 1));
            e_nom.setText(s);

            e_categorie.addContent(e_code);
            e_categorie.addContent(e_nom);

            catElement.addContent(e_categorie);

            catReader.close();
            FileWriter writer = new FileWriter(catFile);

            out.output(catElement, writer);
            writer.close();
            return true;

        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean addProduit(String s, float f, int i) {
        try {
            XMLOutputter out = new XMLOutputter();

            File prodFile = new File(PATH + "Produit.xml");
            FileReader prodReader = new FileReader(prodFile);

            SAXBuilder catBuilder = new SAXBuilder();
            Document ProduitDoc = catBuilder.build(prodReader);

            Element prodElement = ProduitDoc.getRootElement();
            List<Element> listProduit = prodElement.getChildren();

            Element e_categorie = new Element("Produit");
            Element e_code = new Element("codeProduit");
            Element e_nom = new Element("nomProduit");
            Element e_prix = new Element("prixProduit");
            Element e_codeCategorie = new Element("codeCategorie");

            e_code.setText(String.valueOf(listProduit.size() + 1));
            e_nom.setText(s);
            e_prix.setText(String.valueOf(f));
            e_codeCategorie.setText(String.valueOf(i));

            e_categorie.addContent(e_code);
            e_categorie.addContent(e_nom);
            e_categorie.addContent(e_prix);
            e_categorie.addContent(e_codeCategorie);

            prodElement.addContent(e_categorie);

            prodReader.close();
            FileWriter writer = new FileWriter(prodFile);

            out.output(prodElement, writer);
            writer.close();
            return true;

        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
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
