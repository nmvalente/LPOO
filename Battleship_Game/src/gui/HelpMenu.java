package gui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * The Class HelpMenu to show help on the start frame.
 */
public class HelpMenu extends JDialog{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new help menu.
	 */
	public HelpMenu() {
		JOptionPane.showMessageDialog(null, "H to hit, ...");
	}
}