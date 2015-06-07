package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * The listener interface for receiving hits events.
 * The class that is interested in processing a hits
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addHitsListener method. When
 * the hits event occurs, that object's appropriate
 * method is invoked.
 */
public class HitsListener extends JPanel{
	
	private int numberShots = 0;
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The default background. */
	private Color defaultBackground = Color.GRAY; 
	
	/** The shot. */
	private boolean shot = false;
	
	/**
	 * Instantiates a new hits listener.
	 */
	public HitsListener()
	{
		{
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					if(shot == false)
					{
						defaultBackground = getBackground();
						setBackground(Color.GRAY);
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if(shot == false)
					{
						setBackground(defaultBackground);
					}
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if(shot == false)
					{// avaliar tiro
						defaultBackground = Color.GRAY;
						setBackground(Color.GRAY);
						shot = true;
						numberShots++;
					}
				}
			});
		}
	}
	public int getShot(){return numberShots;}
}

