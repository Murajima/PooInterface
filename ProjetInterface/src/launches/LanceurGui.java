package launches;

import javax.swing.JFrame;

import gui.CategorieFrame;

public class LanceurGui {

	public static void main(String[] args) {
		CategorieFrame frame=new CategorieFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
