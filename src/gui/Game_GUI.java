package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Game_GUI extends JFrame{

	public JFrame aux = new JFrame();
	public Configurations c = null;
	public desenho d = null;
	public ChangeButtons dialog = null;
	public CustomMaze customMaze = null;
	public int type = 1, size = 17, nr_of_dragons = 1, dragon_type = 1, nr_of_darts = 1;
	char left='a', right='d', up='w', down='s';
	JPanel panel_game = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game_GUI window = new Game_GUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Game_GUI() {
		super("Maze Game");
		setBackground(Color.LIGHT_GRAY);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//temporario
		setBounds(500, 20, 960, 880);
		setBackground(Color.BLACK);
		setName("Maze Game");


		// ativar este e apagar temporario, no final - setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(Color.BLACK);


		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setSize(new Dimension(100, 100));
		getContentPane().add(panel, BorderLayout.EAST);
		panel.setLayout(null);

		//Exit button/////////////////////////////////////

		JButton Exit = new JButton("Exit");
		Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null,"Exit Game", "Exiting Game",JOptionPane.YES_NO_OPTION);
				if(n==0)
					System.exit(0);
			}
		});
		Exit.setBounds(0, 450, 100, 25);
		panel.add(Exit);

		//Start button///////////////////////////////////////////////////
		JButton Start = new JButton("Start");
		Start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(dialog != null){
					left = dialog.getLeft();
					right = dialog.getRight();
					up = dialog.getUp();
					down = dialog.getDown();
				}

				if(customMaze != null)
				{
					type = 0;
					size = 10;
					//nr_of_dragons = customMaze.getNrDragons();
					int[] exit = customMaze.getPos();
//					char[][] b = new char[][]{
//							{'X','X','X','X','X','X','X','X','X','X'},
//							{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
//							{'X',' ','X','X',' ','X',' ','X',' ','X'},
//							{'X',' ','X','X',' ','X',' ','X',' ','X'},
//							{'X',' ','X','X',' ','X',' ','X',' ','X'},
//							{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
//							{'X',' ','X','X',' ','X',' ','X',' ','X'},
//							{'X',' ','X','X',' ','X',' ','X',' ','X'},
//							{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
//							{'X','X','X','X','X','X','X','X','X','X'}};

					char[][] b = customMaze.getLabirinto();
					d = new desenho(0, 10, exit, b, left, right, up, down);
					customMaze = null;
					c = null;
					panel_game = d;
					getContentPane().add(panel_game);
					getContentPane().setPreferredSize(new Dimension(d.game.get_maze_size() * 50 + 100, d.game.get_maze_size() * 50));
					pack();
					panel_game.setVisible(true);
				}

				else 
					if(c != null) 
					{
						type = c.getmType();
						size = c.getmSize();
						nr_of_dragons = c.getNrDragons();
						dragon_type = c.getDragonType();
						nr_of_darts = c.getNrDarts();
						c = null;
						d = new desenho(type, size, nr_of_dragons, dragon_type, nr_of_darts, left, right, up, down);
						panel_game = d;
						getContentPane().add(panel_game);
						getContentPane().setPreferredSize(new Dimension(d.game.get_maze_size() * 50 + 100, d.game.get_maze_size() * 50));
						pack();
						panel_game.setVisible(true);
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Please, configure game first");
					}
			}
		});
		Start.setBounds(0, 50, 100, 25);
		panel.add(Start);

		//Configuration Button////////////////////////////////////////////

		JButton Configurations = new JButton("Configure");
		Configurations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					c = new Configurations();
					c.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					c.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Configurations.setAlignmentX(0.5f);
		Configurations.setBounds(0, 100, 100, 25);
		panel.add(Configurations);

		//Save button////////////////////////////////////////////////////

		JButton Save = new JButton("Save");
		Save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("savegame.txt"), "utf-8"))) {
					writer.write(d.game.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		Save.setBounds(0, 150, 100, 25);
		panel.add(Save);

		//Reset button////////////////////////////////////////////

		JButton Load = new JButton("Load");
		Load.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					d = new desenho("savegame.txt", left, right, up, down);
					panel_game = d;
					getContentPane().add(panel_game);
					getContentPane().setPreferredSize(new Dimension(d.game.get_maze_size() * 50 + 100, d.game.get_maze_size() * 50));
					pack();
					d.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		Load.setBounds(0, 200, 100, 25);
		panel.add(Load);

		//Reset Button////////////////////////////////////////////

		JButton reset = new JButton("Reset");
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int j = JOptionPane.showConfirmDialog(null,"Reset Game", "Maze Game",JOptionPane.YES_NO_OPTION);
				JOptionPane.showMessageDialog(null, "Please, configure new game");
				if(j==0)
				{
					c = null;
					panel_game.setVisible(false);
					pack();

				}
			}
		});
		reset.setBounds(0, 250, 100, 25);
		panel.add(reset);

		//Keys Button////////////////////////////////////////////

		JButton ChangeKeys = new JButton("Keys");
		ChangeKeys.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ChangeKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					dialog = new ChangeButtons();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		ChangeKeys.setBounds(0, 300, 100, 25);
		panel.add(ChangeKeys);

		//Help Button////////////////////////////////////////////

		JButton help = new JButton("Help");
		help.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "To move hero: w - up, a - left, s - down, d - right \nTo throw dart: i - up, j - left, k - down, l - right");
			}
		});
		help.setFont(new Font("Tahoma", Font.PLAIN, 13));
		help.setBounds(0, 400, 100, 25);
		panel.add(help);

		//Custom Maze////////////////////////////////////////////////

		JButton custom = new JButton("Custom Maze");
		custom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					customMaze = new CustomMaze();
					customMaze.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		custom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		custom.setBounds(0, 349, 100, 25);
		panel.add(custom);

	}
}
