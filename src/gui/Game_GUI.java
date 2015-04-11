package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.SpinnerListModel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.FlowLayout;

public class Game_GUI extends JFrame{

	public int type, size, nr_of_dragons, dragon_type, nr_of_darts;
	
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
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Botao 1");
			}
		});
		btnNewButton.setBounds(0, 429, 100, 25);
		panel.add(btnNewButton);
		
		JSpinner spinner = new JSpinner();
		spinner.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.equals("Static"))
					type = 0;
				else type = 1;
			}
		});
		spinner.setModel(new SpinnerListModel(new String[] {"Static", "Random"}));
		spinner.setBounds(0, 25, 97, 25);
		panel.add(spinner);
		
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
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(9, 9, 31, 2));
		spinner_1.setBounds(0, 101, 97, 25);
		panel.add(spinner_1);
		
		JTextArea txtrNumberOfDragons = new JTextArea();
		txtrNumberOfDragons.setBackground(Color.BLACK);
		txtrNumberOfDragons.setForeground(Color.WHITE);
		txtrNumberOfDragons.setText("No. of dragons?");
		txtrNumberOfDragons.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrNumberOfDragons.setEditable(false);
		txtrNumberOfDragons.setAlignmentX(1.5f);
		txtrNumberOfDragons.setBounds(0, 162, 100, 25);
		panel.add(txtrNumberOfDragons);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 6, 1));
		spinner_2.setBounds(0, 189, 97, 25);
		panel.add(spinner_2);
		
		JTextArea txtrTypeOfDragons = new JTextArea();
		txtrTypeOfDragons.setEditable(false);
		txtrTypeOfDragons.setBackground(Color.BLACK);
		txtrTypeOfDragons.setForeground(Color.WHITE);
		txtrTypeOfDragons.setText("Dragon type?");
		txtrTypeOfDragons.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrTypeOfDragons.setAlignmentX(1.0f);
		txtrTypeOfDragons.setBounds(0, 243, 100, 25);
		panel.add(txtrTypeOfDragons);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerListModel(new String[] {"Static", "Moving", "Sleeping"}));
		spinner_3.setBounds(0, 269, 97, 25);
		panel.add(spinner_3);
		
		JTextArea txtrNrOfDarts = new JTextArea();
		txtrNrOfDarts.setBackground(Color.BLACK);
		txtrNrOfDarts.setForeground(Color.WHITE);
		txtrNrOfDarts.setText("No. of darts?");
		txtrNrOfDarts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrNrOfDarts.setEditable(false);
		txtrNrOfDarts.setAlignmentX(1.0f);
		txtrNrOfDarts.setBounds(0, 323, 100, 25);
		panel.add(txtrNrOfDarts);
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(0, 0, 7, 1));
		spinner_4.setBounds(0, 350, 97, 25);
		panel.add(spinner_4);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.setBounds(0, 461, 100, 25);
		panel.add(btnExitGame);
		
		JPanel panel_1 = new desenho(1, 17, 3, 2, 2);
		getContentPane().add(panel_1, BorderLayout.CENTER);
//		panel_1.setLayout(null);
	}
}
