package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javafx.geometry.Insets;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;

public class Game_GUI extends JFrame{

	public int type = 1, size = 17, nr_of_dragons = 1
			, dragon_type = 1, nr_of_darts = 1;

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
		setBounds(20, 20, 960, 880);

		setName("Maze Game");
		       
        
		// ativar este e apagar temporario, no final - setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));


		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setSize(new Dimension(100, 100));
		getContentPane().add(panel, BorderLayout.EAST);
		panel.setLayout(null);

		
		///////////////////////////spinners///////////////////////////////////////////

/*
		JSpinner maze_type_spinner = new JSpinner();
		maze_type_spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String typeString = (String)maze_type_spinner.getValue();
				if(typeString == "Static")
					type = 0;
				else type = 1;
			}
		});

		maze_type_spinner.setModel(new SpinnerListModel(new String[] {"Static", "Random"}));
		maze_type_spinner.setBounds(0, 25, 97, 25);
		panel.add(maze_type_spinner);

		JTextArea txtrMazeType = new JTextArea();
		txtrMazeType.setForeground(Color.WHITE);
		txtrMazeType.setCaretColor(Color.BLACK);
		txtrMazeType.setBackground(Color.BLACK);
		txtrMazeType.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtrMazeType.setAlignmentY(Component.CENTER_ALIGNMENT);
		txtrMazeType.setEditable(false);
		txtrMazeType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrMazeType.setText("Maze type?");
		txtrMazeType.setBounds(0, 0, 100, 25);
		panel.add(txtrMazeType);

		JTextArea txtrMazeSize = new JTextArea();
		txtrMazeSize.setBackground(Color.BLACK);
		txtrMazeSize.setForeground(Color.WHITE);
		txtrMazeSize.setText("Maze size?");
		txtrMazeSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrMazeSize.setEditable(false);
		txtrMazeSize.setAlignmentX(1.0f);
		txtrMazeSize.setBounds(0, 75, 100, 25);
		panel.add(txtrMazeSize);

		JSpinner maze_size = new JSpinner();
		maze_size.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				size = (int)maze_size.getValue();
			}
		});
		maze_size.setModel(new SpinnerNumberModel(7, 7, 31, 2));
		maze_size.setBounds(0, 101, 97, 25);
		panel.add(maze_size);

		JTextArea txtrNumberOfDragons = new JTextArea();
		txtrNumberOfDragons.setBackground(Color.BLACK);
		txtrNumberOfDragons.setForeground(Color.WHITE);
		txtrNumberOfDragons.setText("No. of dragons?");
		txtrNumberOfDragons.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrNumberOfDragons.setEditable(false);
		txtrNumberOfDragons.setAlignmentX(1.5f);
		txtrNumberOfDragons.setBounds(0, 162, 100, 25);
		panel.add(txtrNumberOfDragons);

		JSpinner nr_of_dragons_1 = new JSpinner();
		nr_of_dragons_1.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		nr_of_dragons_1.setBounds(0, 189, 97, 25);
		nr_of_dragons_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				nr_of_dragons = (int)nr_of_dragons_1.getValue();
			}
		});
		panel.add(nr_of_dragons_1);

		JTextArea txtrTypeOfDragons = new JTextArea();
		txtrTypeOfDragons.setEditable(false);
		txtrTypeOfDragons.setBackground(Color.BLACK);
		txtrTypeOfDragons.setForeground(Color.WHITE);
		txtrTypeOfDragons.setText("Dragon type?");
		txtrTypeOfDragons.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrTypeOfDragons.setAlignmentX(1.0f);
		txtrTypeOfDragons.setBounds(0, 243, 100, 25);
		panel.add(txtrTypeOfDragons);

		JSpinner dragon_type_1 = new JSpinner();
		dragon_type_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String dragon_typeString = (String)dragon_type_1.getValue();
				if(dragon_typeString == "Static")
					dragon_type = 0;
				else if(dragon_typeString == "Moving")
					dragon_type = 1;
				else dragon_type = 2;
			}
		});
		dragon_type_1.setModel(new SpinnerListModel(new String[] {"Static", "Moving", "Sleeping"}));
		dragon_type_1.setBounds(0, 269, 97, 25);
		panel.add(dragon_type_1);

		JTextArea txtrNrOfDarts = new JTextArea();
		txtrNrOfDarts.setBackground(Color.BLACK);
		txtrNrOfDarts.setForeground(Color.WHITE);
		txtrNrOfDarts.setText("No. of darts?");
		txtrNrOfDarts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrNrOfDarts.setEditable(false);
		txtrNrOfDarts.setAlignmentX(1.0f);
		txtrNrOfDarts.setBounds(0, 323, 100, 25);
		panel.add(txtrNrOfDarts);

		JSpinner nr_of_darts_1 = new JSpinner();
		nr_of_darts_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				nr_of_darts = (int)nr_of_darts_1.getValue();
			}
		});
		nr_of_darts_1.setModel(new SpinnerNumberModel(0, 0, 7, 1));
		nr_of_darts_1.setBounds(0, 350, 97, 25);
		panel.add(nr_of_darts_1);
*/

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
		Exit.setBounds(0, 563, 100, 25);
		panel.add(Exit);


		//Start button///////////////////////////////////////////////////
		JButton Start = new JButton("Start");
		Start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel panel_1 = new desenho(type, size, nr_of_dragons, dragon_type, nr_of_darts);
				getContentPane().add(panel_1);
			    getContentPane().setPreferredSize(new Dimension(size * 50 + 100, size * 50));
			    pack();
				setVisible(true);
			}
		});
		Start.setBounds(0, 429, 100, 25);
		panel.add(Start);
		//////////////////////////////////////////////////////////////////


		//Button configurations///////////////////////////////////////////
		JButton Configurations = new JButton("Configure");
		Configurations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Configurations c = new Configurations();
				type = c.getmType();
				size = c.getmSize();
				nr_of_dragons = c.getNrDragons();
				dragon_type = c.getDragonType();
				nr_of_darts = c.getNrDarts();
			}
		});
		Configurations.setAlignmentX(0.5f);
		Configurations.setBounds(0, 499, 100, 25);
		panel.add(Configurations);

		////////////////////desenho////////////////////////////////////////

		//		JPanel panel_1 = new desenho(1, 17, 3, 2, 2);
		//		getContentPane().add(panel_1, BorderLayout.CENTER);
		//panel_1.setLayout(null);



	}
}
