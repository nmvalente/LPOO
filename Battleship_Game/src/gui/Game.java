package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.JLabel;

public class Game extends JFrame {
	private static final int NUM_ROWS = 10;
	private static final int NUM_COLS = 10;
	private static final int PANEL_WIDTH = 600;
	private static final int PANEL_HEIGHT = 600;
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
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		getContentPane().setBackground(SystemColor.activeCaptionBorder);
		setBackground(Color.BLACK);
		setName("Battleship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1243, 622);
		setMinimumSize(new Dimension(1300, 800));
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 35, 35));
		
		JPanel panel_1 = new Grid(10,10);
		getContentPane().add(panel_1);
		
		JPanel panel = new Grid(10,10);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("YOUR TURN");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setPreferredSize(new Dimension(100, 20));
		lblNewLabel.setSize(new Dimension(50, 10));
		getContentPane().add(lblNewLabel);
		
		JPanel panel_2 = new HitMissPanel(900,50);
		getContentPane().add(panel_2);
		
		
		
		
			}
}
