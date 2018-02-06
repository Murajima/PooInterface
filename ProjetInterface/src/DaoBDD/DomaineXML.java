package DaoBDD;

import IDAO.IDomaine;
import beans.Categorie;
import beans.Categories;

public class DomaineXML implements IDomaine {
    @Override
    public Categories getAllCategories(boolean b) {
        return null;
    }

    @Override
    public Categorie getCategorie(int i) {
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
