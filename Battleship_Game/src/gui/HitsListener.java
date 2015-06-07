package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class HitsListener extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color defaultBackground = Color.GRAY; 
	private boolean shot = false;
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
					}
				}
			});
		}
	}
}

