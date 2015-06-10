package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Game;
import logic.Player;

public class GameDriver {

	
	private JPanel mypanel1, myopponentsPanel1, mypanel2, myopponentsPanel2;
	private JFrame frame; 
	GamePanel gm1, gm2;

	public GameDriver(String namePlayer1, String namePlayer2, Player player1, Player player2, JFrame startFrame) {
		Game game = Game.Instance();
		frame = startFrame;

		/*	gm1 = new GamePanel(namePlayer1, namePlayer2, game.getPlayer1(), frame);
		gm1.setVisible(false);
		mypanel1 = gm1.getMyPanel();
		myopponentsPanel1 = gm1.getMyOpponentsPanel();

		gm2 = new GamePanel(namePlayer2, namePlayer1, game.getPlayer2(), frame);
		gm2.setVisible(false);
		mypanel2 = gm2.getMyPanel();
		myopponentsPanel2 = gm2.getMyOpponentsPanel();
		turn = game.getStartingPlayer();
		initGame();
	}
		 */
		/*public void initGame(){
		if(turn == 1)
		{
			mypanel1.setVisible(false);
			myopponentsPanel1.setVisible(false);
			gm1.setVisible(true);
		}
		else
		{
			mypanel2.setVisible(false);
			myopponentsPanel2.setVisible(false);
			gm2.setVisible(true);
		}
	};*/
	}
}
