package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import java.util.Random;
import java.util.Scanner;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import cli.Setup;

public class GameSettings extends JDialog implements ActionListener{
	private static int width;
	private static int height;
	private JPanel choicesPane;
	private static JRadioButton againstPC;
	private static JRadioButton againstHuman;
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Thread t1,t2;
	private JTextField nameP1;
	private JTextField nameP2;
	private String namePlayer1 = "", namePlayer2 = ""; 
	boolean checkNames = false;
	private JLabel lblPlayer;
	private int nrPlayers = 0;
	private LoadGameMenu lgm; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame frame = new JFrame();
			GameSettings dialog = new GameSettings(width, height, frame, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public GameSettings(int w, int h, JFrame frame, IntroSound audio) {
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

						if((againstPC.isSelected() == true) || (againstHuman.isSelected() == true))
						{	
							if(checkNames == false)
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
											if((againstPC.isSelected() == true))		
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
		nameP1.addActionListener(this);
		

		JLabel lblNewLabel = new JLabel("Player 1 - Press Enter");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(38, 191, 155, 16);
		choicesPane.add(lblNewLabel);

		lblPlayer = new JLabel("Player 2 - Press Enter");
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
		nameP2.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		namePlayer1 = nameP1.getText();
		namePlayer2 = nameP2.getText();

		if(againstHuman.isSelected())
		{
			nameP1.setBounds(36, 220, 169, 22);
			nameP2.setVisible(true);
			lblPlayer.setVisible(true);
			setNrPlayers(2);
			if((namePlayer1.length() > 0) && (namePlayer2.length() > 0))
				checkNames = true;
			else checkNames = false;
		}
		else if(againstPC.isSelected())
		{
			nameP1.setBounds(36, 220, 300, 22);
			nameP2.setVisible(false);
			lblPlayer.setVisible(false);
			namePlayer2 = "";
			setNrPlayers(1);
			if(namePlayer1.length() > 0)
				checkNames = true;
			else checkNames = false;
		}

		

		if(arg0.getActionCommand().equals("Load Config"))
		{
			Random random = new Random();
			Scanner scan = new Scanner(System.in);
			String file1, file2;
			lgm = new LoadGameMenu();
			file1 = lgm.getArquivoSelec().getName();
			lgm = new LoadGameMenu();
			file2 = lgm.getArquivoSelec().getName();
			new Setup(scan, file1, file2, random);
		}
	}


	public int getNrPlayers() {
		return nrPlayers;
	}


	public void setNrPlayers(int nrPlayers) {
		this.nrPlayers = nrPlayers;
	}



}
