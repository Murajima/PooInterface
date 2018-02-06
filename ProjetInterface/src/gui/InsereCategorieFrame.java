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
public class InsereCategorieFrame extends JFrame implements ActionListener {
	private CategorieFrame parent;
	private JTextField jtfNom;
	private JButton jbAnnuler, jbAccepter;
	
	public InsereCategorieFrame(CategorieFrame parent) {
		this.parent = parent;
		this.setSize(200, 100);
		this.setTitle("Ajout Catégorie");
		JPanel panel=new JPanel(new GridLayout(2, 2, 10, 10));
		panel.add(new JLabel("Nom : "));
		panel.add(jtfNom=new JTextField());
		panel.add(jbAnnuler=new JButton("Annuler"));
		panel.add(jbAccepter=new JButton("Accepter"));
		this.add(panel);
		jbAnnuler.addActionListener(this);
		jbAccepter.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbAccepter) {
			parent.getCc().ajouteCategorie(jtfNom.getText());
			parent.getCc().dernier();
			parent.refreshData();
		}
		this.setVisible(false);
	}

}
