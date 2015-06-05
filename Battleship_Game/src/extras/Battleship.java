package extras;
import gui.Grid;
import gui.HitMissPanel;

import javax.swing.*;

import java.awt.*;

public class Battleship extends JFrame
{
	private static final int NUM_ROWS = 10;
	private static final int NUM_COLS = 10;
	private static final int PANEL_WIDTH = 601;
	private static final int PANEL_HEIGHT = 601;

	private BattleshipPanel battleshipPanel;
	private HitMissPanel hitMissPanel;
	private Grid choices;
	
	public Battleship()
	{
		hitMissPanel = new HitMissPanel(PANEL_WIDTH, 50);
		battleshipPanel = new BattleshipPanel(PANEL_WIDTH, PANEL_HEIGHT, new BattleshipGrid(NUM_ROWS, NUM_COLS), null);
		//choices = new Grid(10,10);
		init();
	}

	public void init()
	{
		setBackground(Color.black);
		getContentPane().setLayout(new BorderLayout());
		add(battleshipPanel, BorderLayout.WEST);
		add(hitMissPanel, BorderLayout.PAGE_END);
		//choices.setBackground(Color.BLACK);
		//add(choices, BorderLayout.EAST);
	}

	public static void main(String[] args)
	{

		JFrame frame = new Battleship();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);
	}
}