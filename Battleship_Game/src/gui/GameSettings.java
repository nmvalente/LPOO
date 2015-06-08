package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import audio.IntroSound;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import cli.Setup;
import logic.Game;

/**
 * The Class GameSettings.
 */
public class GameSettings extends JDialog implements ActionListener, FocusListener{
	
	/** The width. */
	private static int width;
	
	/** The height. */
	private static int height;
	
	/** The choices pane. */
	private JPanel choicesPane;
	
	/** The against pc. */
	private static JRadioButton againstPC;
	
	/** The against human. */
	private static JRadioButton againstHuman;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The button group. */
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/** The t2. */
	private Thread t1,t2;
	
	/** The name p1. */
	private JTextField nameP1;
	
	/** The name p2. */
	private JTextField nameP2;
	
	/** The names of player1 and player2. */
	private String namePlayer1 = "", namePlayer2 = ""; 
	
	/** The check names. */
	boolean checkNames = false;
	
	/** The lbl player. */
	private JLabel lblPlayer;

	/** The lgm. */
	private LoadGameMenu lgm;

    /** The game. */
	private Game game;

    private Random random;
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
        Random random = new Random();
		try {
			JFrame frame = new JFrame();
			GameSettings dialog = new GameSettings(width, height, frame, null, random);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Instantiates a new game settings.
	 *
	 * @param w the width of the GameSettings JDialog
	 * @param h the height of the GameSettings JDialog
	 * @param frame the start frame
	 * @param audio the audio playing on the start frame
	 */
	public GameSettings(int w, int h, JFrame frame, IntroSound audio, Random newRandom) {
        random = newRandom;
		width = w;
		height = h;
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setUndecorated(false);
		setModal(true);
		setLocationRelativeTo(null);  
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setResizable(false);
		setSize(610,408);
		choicesPane = new JPanel();
		choicesPane.setBackground(Color.BLACK);
		getContentPane().setLayout(new BorderLayout());
		choicesPane.setBorder(null);
		getContentPane().add(choicesPane, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(Color.BLACK);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			ProgressBarDemo progressBarDemo = new ProgressBarDemo();
			progressBarDemo.setBackground(Color.BLACK);
			buttonPane.add(progressBarDemo);

			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						if((againstPC.isSelected()) || (againstHuman.isSelected()))
						{	
							if(!checkNames)
								JOptionPane.showMessageDialog(null, "Enter valid player names");
							else
							{
								t1 = new Thread(){
									public @Override void run() { 
										progressBarDemo.getStart();
										try {
											Thread.sleep(4000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								};

								t2 = new Thread(){
									public @Override void run() { 
										if (t1.getState() != State.TERMINATED)
											try {
												t1.join();

											} catch (InterruptedException e) {
												e.printStackTrace();
											}
										int n = JOptionPane.showConfirmDialog(null,"Confirm", "Configurations accepted",JOptionPane.YES_NO_OPTION);
										if(n==0)
										{

											dispose();
											frame.dispose();
											JFrame gameFrame;
											if((againstPC.isSelected()))
											{
												gameFrame = new GamePanel(namePlayer1, namePlayer2, frame); // game against pc
												gameFrame.setVisible(true);
												gameFrame.setLocationRelativeTo(frame);
											}
											else
											{
												gameFrame = new GamePanel(namePlayer1, namePlayer2, frame); // game against human
												gameFrame.setVisible(true);
												gameFrame.setLocationRelativeTo(frame);
											}
										}
									}
								};
								t1.start();
								t2.start();

							}
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");

				cancelButton.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
						frame.setVisible(true);
						if(audio.alreadyPlaying())
							audio.play();
						else audio.stop();
					}
				});
				buttonPane.add(cancelButton);	
			}
			choicesPane.setLayout(null);
		}

		againstPC = new JRadioButton("Against PC");
		againstPC.setSelected(true);
		buttonGroup.add(againstPC);
		againstPC.setBounds(36, 34, 127, 25);
		choicesPane.add(againstPC);
		againstPC.addActionListener(this);

		againstHuman = new JRadioButton("Against Human");
		buttonGroup.add(againstHuman);
		againstHuman.setBounds(36, 105, 127, 25);
		choicesPane.add(againstHuman);
		againstHuman.addActionListener(this);

		nameP1 = new JTextField();
		nameP1.setBounds(36, 220, 300, 22);
		choicesPane.add(nameP1);
		nameP1.setColumns(10);
		nameP1.addFocusListener(this);
		

		JLabel lblNewLabel = new JLabel("Player 1");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(38, 191, 155, 16);
		choicesPane.add(lblNewLabel);

		lblPlayer = new JLabel("Player 2");
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setBounds(265, 191, 167, 16);
		choicesPane.add(lblPlayer);
		lblPlayer.setVisible(false);

		nameP2 = new JTextField();
		nameP2.setColumns(10);
		nameP2.setBounds(263, 220, 169, 22);
		choicesPane.add(nameP2);

		JButton ConfigButton = new JButton("Load Config");
		ConfigButton.setFocusPainted(false);
		ConfigButton.setContentAreaFilled(false);
		ConfigButton.setVerifyInputWhenFocusTarget(false);
		ConfigButton.setOpaque(false);
		ConfigButton.setForeground(Color.WHITE);
		ConfigButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		ConfigButton.setBackground(Color.BLUE);
		ConfigButton.setSelectedIcon(null);
		ConfigButton.setRolloverSelectedIcon(null);
		ConfigButton.setBounds(479, 219, 97, 25);
		ConfigButton.addActionListener(this);
		choicesPane.add(ConfigButton);
		nameP2.setVisible(false);
		nameP2.addFocusListener(this);

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		namePlayer1 = nameP1.getText();
		namePlayer2 = nameP2.getText();
        game = Game.Instance();

		if(againstHuman.isSelected())
		{
			nameP1.setBounds(36, 220, 169, 22);
			nameP2.setVisible(true);
			lblPlayer.setVisible(true);
            checkNames = (namePlayer1.length() > 0) && (namePlayer2.length() > 0);
            game.setNumberPlayers(2);
            game.setPlayer1Name(namePlayer1);
            game.setPlayer2Name(namePlayer2);
		}
		else if(againstPC.isSelected())
		{
			nameP1.setBounds(36, 220, 300, 22);
			nameP2.setVisible(false);
			lblPlayer.setVisible(false);
			namePlayer2 = "";
            checkNames = namePlayer1.length() > 0;
            game.setPlayer1Name(namePlayer1);
            game.setPlayer2Name(namePlayer2);
		}

		

		if(arg0.getActionCommand().equals("Load Config"))
		{
			String configFile;
			lgm = new LoadGameMenu();
			configFile = lgm.getArquivoSelec().getName();
            game.setConfigFile(configFile);
            try {
                game.readConfig();
            }
            catch (IOException IOExcept) {
                game.loadConfig();
            }
            game.autoPlaceShips(random, 1);
            game.autoPlaceShips(random, 2);

		}
	}


	@Override
	public void focusGained(FocusEvent arg0) {}


	@Override
	public void focusLost(FocusEvent arg0) {
		namePlayer1 = nameP1.getText();
		namePlayer2 = nameP2.getText();
		if(againstHuman.isSelected())
		{
			 checkNames = (namePlayer1.length() > 0) && (namePlayer2.length() > 0);
		}
		else checkNames = namePlayer1.length() > 0;
	}


}
