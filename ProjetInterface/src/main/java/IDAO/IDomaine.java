package IDAO;

import beans.Categorie;
import beans.Categories;

@SuppressWarnings("unused")
public interface IDomaine {
    Categories getAllCategories(boolean b);
    Categorie getCategorie(int i);
    boolean addCategorie(String s);
    boolean addProduit(String s,float f, int i);
    boolean deleteCategorie(int i);
    boolean deleteProduit(int i);
}
