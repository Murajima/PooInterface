package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InsereProduitFrame extends JFrame implements ActionListener {
	private CategorieFrame parent;
	private int codeCategorie;
	private JTextField jtfNom,jtfPrix;
	private JButton jbAnnuler, jbAccepter;
	
	public InsereProduitFrame(CategorieFrame parent,int codeCategorie) {
		this.parent = parent;
		this.codeCategorie=codeCategorie;
		this.setSize(400, 300);
		this.setTitle("Ajout Produit ["+codeCategorie+"]");
		JPanel panel=new JPanel(new GridLayout(3, 2, 10, 10));
		panel.add(new JLabel("Nom : "));
		panel.add(jtfNom=new JTextField());
		panel.add(new JLabel("prix : "));
		panel.add(jtfPrix=new JTextField());
		panel.add(jbAnnuler=new JButton("Annuler"));
		panel.add(jbAccepter=new JButton("Accepter"));
		this.add(panel);
		jbAnnuler.addActionListener(this);
		jbAccepter.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbAccepter) {
			parent.getCc().ajouteProduit(jtfNom.getText(),Float.parseFloat(jtfPrix.getText()),codeCategorie);
			parent.refreshData();
		}
		this.setVisible(false);
	}

}
