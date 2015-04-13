package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

//import javafx.geometry.Insets;



import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.awt.Color;

public class Game_GUI extends JFrame{

	public JFrame aux = new JFrame();
	public Configurations c = null;
	public int type = 1, size = 17, nr_of_dragons = 1, dragon_type = 1, nr_of_darts = 1;

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

				if(c != null)
				{
					type = c.getmType();
					size = c.getmSize();
					nr_of_dragons = c.getNrDragons();
					dragon_type = c.getDragonType();
					nr_of_darts = c.getNrDarts();
				}

				JPanel panel_1 = new desenho(type, size, nr_of_dragons, dragon_type, nr_of_darts);
				getContentPane().add(panel_1);
				getContentPane().setPreferredSize(new Dimension(size * 50 + 100, size * 50));
				pack();
				setVisible(true);
			}
		});
		Start.setBounds(0, 429, 100, 25);
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
		Configurations.setBounds(0, 499, 100, 25);
		panel.add(Configurations);
	}
}
