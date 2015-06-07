package gui;

import javax.swing.JOptionPane;

/**
 * The Class ExitMenu.
 */
public class ExitMenu extends JOptionPane {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new exit menu to quit the game
	 */
	public ExitMenu(){
		int n = showConfirmDialog(null,"Exit Game", "Exiting Game",JOptionPane.YES_NO_OPTION);
		if(n==0)
			System.exit(0);		
	}	
}
