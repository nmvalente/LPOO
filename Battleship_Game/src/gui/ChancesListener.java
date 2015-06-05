package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import javax.swing.JPanel;

public class ChancesListener extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color defaultBackground = Color.GRAY; 
	private boolean click2 = false;
	public ChancesListener()
	{
		{
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					defaultBackground = getBackground();
					setBackground(Color.BLUE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setBackground(defaultBackground);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if(click2 == false)
					{
						defaultBackground = Color.blue;
						setBackground(Color.BLUE);
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

	/*

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("1");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setBackground(Color.BLUE);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setBackground(defaultBackground);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}*/
}

