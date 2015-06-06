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
import javax.swing.border.EmptyBorder;

import audio.IntroSound;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class GameSettings extends JDialog{
	private static int width;
	private static int height;
	private JPanel choicesPane;
	private static JRadioButton againstPC;
	private static JRadioButton againstHuman;
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Thread t1,t2;

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
		choicesPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
							t1 = new Thread(){
								public @Override void run() { 
									progressBarDemo.getStart();
									try {
										Thread.sleep(4000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
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
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									int n = JOptionPane.showConfirmDialog(null,"Confirm", "Configurations accepted",JOptionPane.YES_NO_OPTION);
									if(n==0)
									{

										dispose();
										frame.dispose();
										JFrame game;
										if((againstPC.isSelected() == true))		
										{
											game = new Game(frame); // game against pc
											game.setVisible(true);
											//game.setLocationRelativeTo(null);
										}
										else
										{
											game = new Game(frame); // game against human
											game.setVisible(true);
											//game.setLocationRelativeTo(null);
										}

									}

								}
							};
							t1.start();
							t2.start();

							
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
		againstPC.setBounds(67, 144, 127, 25);
		choicesPane.add(againstPC);

		againstHuman = new JRadioButton("Against Human");
		buttonGroup.add(againstHuman);
		againstHuman.setBounds(67, 215, 127, 25);
		choicesPane.add(againstHuman);
	}
}
