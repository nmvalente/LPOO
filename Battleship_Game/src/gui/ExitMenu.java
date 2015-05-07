package gui;

import javax.swing.JOptionPane;

public class ExitMenu extends JOptionPane {
	
	private static final long serialVersionUID = 1L;
	public ExitMenu(){
		int n = showConfirmDialog(null,"Exit Game", "Exiting Game",JOptionPane.YES_NO_OPTION);
		if(n==0)
			System.exit(0);		
	}	
}
