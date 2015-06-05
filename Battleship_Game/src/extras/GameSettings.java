package extras;

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

public class GameSettings extends JDialog {
	private static int width;
	private static int height;
	private JPanel contentPanel;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int type = 1, size = 17, nr_of_dragons = 1, dragon_type = 1, nr_of_darts = 1;
	public int getmType(){return type;}
	public int getmSize(){return size;}
	public int getNrDragons(){return nr_of_dragons;}
	public int getDragonType(){return dragon_type;}
	public int getNrDarts(){return nr_of_darts;}
	public JPanel getPanel(){return contentPanel;}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GameSettings dialog = new GameSettings(width, height, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setVisible(true);
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
		
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setResizable(false);
		setSize(width,height);
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.BLACK);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(Color.BLACK);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			JTextArea txtrMazeSize = new JTextArea();
			txtrMazeSize.setBackground(Color.BLACK);
			txtrMazeSize.setForeground(Color.WHITE);
			txtrMazeSize.setText("Maze size?");
			txtrMazeSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrMazeSize.setEditable(false);
			txtrMazeSize.setAlignmentX(1.0f);
			txtrMazeSize.setBounds(65, 86, 94, 20);
			contentPanel.add(txtrMazeSize);

			JSpinner maze_size = new JSpinner();
			maze_size.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					size = (int)maze_size.getValue();
				}
			});
			maze_size.setModel(new SpinnerNumberModel(7, 7, 31, 2));
			maze_size.setBounds(65, 103, 94, 36);
			contentPanel.add(maze_size);


			JTextArea txtrNumberOfDragons = new JTextArea();
			txtrNumberOfDragons.setBackground(Color.BLACK);
			txtrNumberOfDragons.setForeground(Color.WHITE);
			txtrNumberOfDragons.setText("No. of dragons?");
			txtrNumberOfDragons.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrNumberOfDragons.setEditable(false);
			txtrNumberOfDragons.setAlignmentX(1.5f);
			txtrNumberOfDragons.setBounds(65, 155, 94, 20);
			contentPanel.add(txtrNumberOfDragons);

			JSpinner nr_of_dragons_1 = new JSpinner();
			nr_of_dragons_1.setModel(new SpinnerNumberModel(1, 1, 6, 1));
			nr_of_dragons_1.setBounds(65, 174, 94, 33);
			nr_of_dragons_1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					nr_of_dragons = (int)nr_of_dragons_1.getValue();
				}
			});
			contentPanel.add(nr_of_dragons_1);


			JTextArea txtrTypeOfDragons = new JTextArea();
			txtrTypeOfDragons.setEditable(false);
			txtrTypeOfDragons.setBackground(Color.BLACK);
			txtrTypeOfDragons.setForeground(Color.WHITE);
			txtrTypeOfDragons.setText("Dragon type?");
			txtrTypeOfDragons.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrTypeOfDragons.setAlignmentX(1.0f);
			txtrTypeOfDragons.setBounds(266, 29, 100, 25);
			contentPanel.add(txtrTypeOfDragons);

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
			dragon_type_1.setBounds(266, 51, 100, 42);
			contentPanel.add(dragon_type_1);

			JTextArea txtrNrOfDarts = new JTextArea();
			txtrNrOfDarts.setBackground(Color.BLACK);
			txtrNrOfDarts.setForeground(Color.WHITE);
			txtrNrOfDarts.setText("No. of darts?");
			txtrNrOfDarts.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrNrOfDarts.setEditable(false);
			txtrNrOfDarts.setAlignmentX(1.0f);
			txtrNrOfDarts.setBounds(266, 113, 100, 36);
			contentPanel.add(txtrNrOfDarts);

			JSpinner nr_of_darts_1 = new JSpinner();
			nr_of_darts_1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					nr_of_darts = (int)nr_of_darts_1.getValue();
				}
			});
			nr_of_darts_1.setModel(new SpinnerNumberModel(0, 0, 7, 1));
			nr_of_darts_1.setBounds(266, 142, 100, 43);
			contentPanel.add(nr_of_darts_1);

			JTextArea txtrMazeType = new JTextArea();
			txtrMazeType.setForeground(Color.WHITE);
			txtrMazeType.setCaretColor(Color.BLACK);
			txtrMazeType.setBackground(Color.BLACK);
			txtrMazeType.setAlignmentX(Component.CENTER_ALIGNMENT);
			txtrMazeType.setAlignmentY(Component.CENTER_ALIGNMENT);
			txtrMazeType.setEditable(false);
			txtrMazeType.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrMazeType.setText("Maze type?");
			txtrMazeType.setBounds(65, 13, 94, 20);
			contentPanel.add(txtrMazeType);

			JSpinner maze_type_spinner = new JSpinner();

			initial_state_spinnners(maze_size, nr_of_dragons_1, dragon_type_1, nr_of_darts_1);

			maze_type_spinner.addChangeListener(new ChangeListener() {


				public void stateChanged(ChangeEvent e) {
					String typeString = (String)maze_type_spinner.getValue();
					if(typeString == "Static")
					{
						initial_state_spinnners(maze_size, nr_of_dragons_1,
								dragon_type_1, nr_of_darts_1);
						type = 0;
					}
					else 
					{
						nr_of_dragons_1.setEnabled(true);	
						dragon_type_1.setEnabled(true);
						nr_of_darts_1.setEnabled(true);
						maze_size.setEnabled(true);	
						type = 1;
					}
				}
			});
			maze_type_spinner.setModel(new SpinnerListModel(new String[] {"Static", "Random"}));
			maze_type_spinner.setBounds(65, 32, 94, 33);
			contentPanel.add(maze_type_spinner);

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
						int n = JOptionPane.showConfirmDialog(null,"Confirm", "Configurations accepted",JOptionPane.YES_NO_OPTION);
						if(n==0)
						{
							nr_of_darts = (int) nr_of_darts_1.getValue();

							if("Static" == maze_type_spinner.getValue())
								type = 0;
							else type = 1;

							size = (int) maze_size.getValue();

							nr_of_dragons = (int) nr_of_dragons_1.getValue();

							if("Static" == dragon_type_1.getValue())
								dragon_type = 0;
							else if("Sleeping" == maze_type_spinner.getValue())
								dragon_type = 1;
							else dragon_type = 2; 
							dispose();
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


			contentPanel.setLayout(null);
		}
	}
	private void initial_state_spinnners(JSpinner maze_size, JSpinner nr_of_dragons_1, JSpinner dragon_type_1, JSpinner nr_of_darts_1) 
	{
		nr_of_dragons_1.setEnabled(false);	
		dragon_type_1.setEnabled(false);
		nr_of_darts_1.setEnabled(false);
		maze_size.setEnabled(false);
	}

}
