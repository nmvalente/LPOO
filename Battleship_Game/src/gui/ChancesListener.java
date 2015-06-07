package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving chances events.
 * The class that is interested in processing a chances
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addChancesListener method. When
 * the chances event occurs, that object's appropriate
 * method is invoked.
 */
public class ChancesListener extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The default background. */
	private Color defaultBackground = Color.GRAY; 
	
	/** The click2. */
	private boolean click2 = false;
	
	/**
	 * Instantiates a new chances listener.
	 */
	public ChancesListener()
	{
		{
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					defaultBackground = getBackground();
					setBackground(Color.GRAY);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setBackground(defaultBackground);
				}
 
				@Override
				public void mouseClicked(MouseEvent e) {
					if(click2 == false)
					{
						defaultBackground = Color.GRAY;
						setBackground(Color.GRAY);
						click2 = true;
					}
					else 
					{
						defaultBackground = Color.BLACK; 
						setBackground(Color.BLACK);
						click2 = false;
					}
				}
			});
		}
	}
}

