package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.glass.ui.Window;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ButtonGroup buttonGroup = new ButtonGroup();



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

	/**
	 * Create the dialog.
	 * @param audio 
	 */
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
						if((againstPC.isSelected()) == true)
						{
							int n = JOptionPane.showConfirmDialog(null,"Confirm", "Configurations accepted",JOptionPane.YES_NO_OPTION);
							if(n==0)
							{
								
								dispose();
								frame.dispose();
								JFrame game = new Game(); // game against pc
								game.setVisible(true);
								game.setLocationRelativeTo(null);
							}
						}
						else if((againstHuman.isSelected()) == true) new Game(); // game against human
						else JOptionPane.showMessageDialog(null, "Please, select a opponent");
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
