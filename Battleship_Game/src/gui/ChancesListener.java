package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logic.Game;
import logic.Player;
import logic.Position;


/**
 * The listener interface for receiving chances events.
 * The class that is interested in processing a chances
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addChancesListener method. When
 * the chances event occurs, that object's appropriate
 * method is invoked.
 */
public class ChancesListener extends JPanel implements MouseListener {
	int posX;
	int posY;
	private int numberHits = 0;
	public int getPosX(){return posX;}
	public int getPosY(){return posY;}
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The default background. */
	private Color defaultBackground = Color.GRAY; 
	
	/** The click2. */
	private boolean click2 = false;
	private Player player1, player2;
	
	private Game game;
	/**
	 * Instantiates a new chances listener.
	 */
	public ChancesListener()
	{
		game = game.Instance();
		addMouseListener(this);
	}
		
	public int getNrHits(){return numberHits;}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		{
			posX=getY()/60-1;
			posY=getX()/60-1;
			
			Position position = new Position(posX,posY);
			//game.attackPosition(player1, player2, position);
			numberHits++;
			System.out.println("dfhgfhfh");
			//System.out.println(game.attackPosition(player1, player2, position));
			//DriveGame dg = new DriveGame(player1, player2, position);
			
			if(click2 == false)
			{
				
				System.out.println(posX + " " + posY);
				
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
		
	}
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

