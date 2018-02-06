package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import beans.Produit;

@SuppressWarnings("serial")
public class TableProduitAbstraite extends AbstractTableModel {
	private ArrayList<Produit> produits;
	private String[] headers={"Code","Nom","Prix"};
	

	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return produits.size();
	}

	@Override
	public Object getValueAt(int ligne, int colonne) {
		switch(colonne) {
		case 0 : return produits.get(ligne).getCodeProduit();
		case 1 : return produits.get(ligne).getNomProduit();
		case 2 : return produits.get(ligne).getPrixProduit();
		default : return null;
		}
	}

	@Override
	public String getColumnName(int index) {
		return headers[index];
	}

}
