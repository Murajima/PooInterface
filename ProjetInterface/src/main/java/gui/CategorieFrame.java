package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import moteur.CollCategories;
import beans.Categorie;

@SuppressWarnings("serial")
public class CategorieFrame extends JFrame implements ActionListener{
	private JTextField jtf_Code, jtf_Nom;
	private JButton jb_Premier, jb_Precedent, jb_Suivant, jb_Dernier, jb_InsereProduit, jb_InsereCategorie, jb_Quitter;
	//private JTextArea jta_Produits;
	private JTable tableProduits;
	private TableProduitAbstraite tpa;
	private CollCategories cc;
	
	public CategorieFrame() {
		this.setSize(600, 300);
		
		JPanel jp_Data=new JPanel(new GridLayout(2, 2, 10, 10));
		jp_Data.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jp_Data.add(new JLabel("Code : "));
		jp_Data.add(jtf_Code=new JTextField());
		jtf_Code.setEnabled(false);
		jp_Data.add(new JLabel("Nom : "));
		jp_Data.add(jtf_Nom=new JTextField());
		this.add(jp_Data,BorderLayout.NORTH);
		
		JPanel jp_Produits=new JPanel();
		tpa=new TableProduitAbstraite();
		tableProduits=new JTable(tpa);
		jp_Produits.add(new JScrollPane(tableProduits));
		jp_Produits.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		/*jp_Produits.add(jta_Produits=new JTextArea(3, 30));
		jta_Produits.setBackground(Color.lightGray);
		jta_Produits.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));*/
		add(jp_Produits);
		
		JPanel jp_Boutons=new JPanel(new GridLayout(1, 7, 10, 10));
		jp_Boutons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jp_Boutons.add(jb_Premier=new JButton(new ImageIcon("images/First.png")));
		jp_Boutons.add(jb_Precedent=new JButton(new ImageIcon("images/Previous.png")));
		jp_Boutons.add(jb_Suivant=new JButton(new ImageIcon("images/Next.png")));
		jp_Boutons.add(jb_Dernier=new JButton(new ImageIcon("images/Last.png")));
		jp_Boutons.add(jb_InsereCategorie=new JButton(new ImageIcon("images/InsereCategorie.png")));
		jp_Boutons.add(jb_InsereProduit=new JButton(new ImageIcon("images/InsereProduit.png")));
		jp_Boutons.add(jb_Quitter=new JButton(new ImageIcon("images/Quit.png")));
		this.add(jp_Boutons,BorderLayout.SOUTH);
		
		jb_Premier.addActionListener(this);
		jb_Dernier.addActionListener(this);
		jb_Suivant.addActionListener(this);
		jb_Precedent.addActionListener(this);
		jb_InsereCategorie.addActionListener(this);
		jb_InsereProduit.addActionListener(this);
		jb_Quitter.addActionListener(this);
		cc=new CollCategories();
		refreshData();
	}
	public void refreshData() {
		Categorie c=cc.getCategorieCourante();
		jtf_Code.setText(String.valueOf(c.getCodeCategorie()));
		jtf_Nom.setText(c.getNomCategorie());
		//jta_Produits.setText(c.strListeProduits());
		tpa.setProduits(c.getListeProduits());
		tpa.fireTableDataChanged();
		this.setTitle(cc.infosCurseur());
		refreshButtons();
	}
	private void refreshButtons() {
		jb_Premier.setEnabled(!cc.estPremier());
		jb_Precedent.setEnabled(!cc.estPremier());
		jb_Suivant.setEnabled(!cc.estDernier());
		jb_Dernier.setEnabled(!cc.estDernier());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb_Quitter)
			System.exit(0);
		else if(e.getSource()==jb_Premier) {
			cc.premier();
			refreshData();
		}
		else if(e.getSource()==jb_Precedent) {
			cc.precedent();
			refreshData();
		}
		else if(e.getSource()==jb_Suivant) {
			cc.suivant();
			refreshData();
		}
		else if(e.getSource()==jb_Dernier) {
			cc.dernier();
			refreshData();
		}
		else if(e.getSource()==jb_InsereCategorie) {
			InsereCategorieFrame icf=new InsereCategorieFrame(this);
			icf.setVisible(true);
		}
		else if(e.getSource()==jb_InsereProduit) {
			InsereProduitFrame ipf=new InsereProduitFrame(this, cc.getCategorieCourante().getCodeCategorie());
			ipf.setVisible(true);
		}
	}
	public CollCategories getCc() {
		return cc;
	}
	
}
