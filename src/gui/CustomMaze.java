package gui;

import java.awt.EventQueue;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Component;

public class CustomMaze extends JFrame {

	public Icon clicked;
	char[][] result = new char[10][10];
	public ArrayList<ArrayList<JButton>> botoes = new ArrayList<ArrayList<JButton>>();
//	public int posEx, posEy;
	public int[] exit_pos;
	public int[] hero_pos;
	public int[] sword_pos;
	public int[] shield_pos;
	public int[] dragon_pos;
	public char[][] getLabirinto() {return result;}
	public ImageIcon iexitclosed = new ImageIcon("images/exitclosed.gif");
	public ImageIcon ishield = new ImageIcon("images/shield.gif");
	public ImageIcon ihero = new ImageIcon("images/hero.gif");
	public ImageIcon idragon = new ImageIcon("images/dragon.gif");
	public ImageIcon iwall = new ImageIcon("images/wall.gif");
	public ImageIcon isword = new ImageIcon("images/sword.gif");
	public ImageIcon inada = new ImageIcon("images/nothing.gif");



	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomMaze frame = new CustomMaze();
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
	public CustomMaze() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 1103, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		criaBotoes();

		///////////////////////////////////////////////////

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = btnNewButton.getIcon();
			}
		});
		btnNewButton.setIcon(ihero);
		btnNewButton.setBounds(976, 334, 97, 63);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = btnNewButton_1.getIcon();
			}
		});
		btnNewButton_1.setIcon(iexitclosed);
		btnNewButton_1.setBounds(976, 410, 97, 63);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = btnNewButton_2.getIcon();
			}
		});
		btnNewButton_2.setIcon(ishield);
		btnNewButton_2.setBounds(976, 253, 97, 68);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = btnNewButton_3.getIcon();
			}
		});
		btnNewButton_3.setIcon(iwall);
		btnNewButton_3.setBounds(976, 175, 97, 65);
		contentPane.add(btnNewButton_3);

		JButton button = new JButton("New button");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = button.getIcon();
			}
		});
		button.setIcon(idragon);
		button.setBounds(976, 97, 97, 65);
		contentPane.add(button);

		JButton button_1 = new JButton("New button");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = button_1.getIcon();
			}
		});
		button_1.setIcon(isword);
		button_1.setBounds(976, 486, 97, 65);
		contentPane.add(button_1);

		/////////////////////////////////////////////////////////////

		JButton btnOk = new JButton("Ok");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getCustomBoard();
				dispose();
			}
		});
		btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOk.setBounds(976, 564, 97, 25);
		contentPane.add(btnOk);
	}

	//////////////////////////////////////////////////////////

	public void criaBotoes(){
		ArrayList<JButton> colunas = null;
		for(int i = 0 ; i < 10 ; i++)
		{
			colunas = new ArrayList<JButton>();
			for(int j = 0 ; j < 10 ; j++)
			{
				String s = "b"+j+i;
				JButton button = new JButton(s);
				button.setIcon(inada);
				button.setActionCommand("");
				button.setBounds(contentPane.getWidth() / 10 + i * 95, contentPane.getHeight() / 10 + j * 60, 95, 60);
				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						button.setIcon(clicked);
					}
				});
				contentPane.add(button);
				colunas.add(button);
				button.setName(s);
			}
			botoes.add(colunas);
		}
	}


	public void getCustomBoard(){
		for(int i = 0 ; i < 10 ; i++)
		{
			for(int j = 0 ; j < 10 ; j++)
			{
				System.out.println(botoes.size());
				Icon icon = botoes.get(i).get(j).getIcon();
				System.out.println(i + ", " + j);
				
				if(icon.equals((Icon)iwall))
					result[i][j] = 'X';
				else if(icon.equals(iexitclosed)) {
					exit_pos[0] = i;
					exit_pos[1] = j;
					result[i][j] = ' ';					
				}
				else if(icon.equals(ihero)) {
					hero_pos[0] = i;
					hero_pos[1] = j;
					result[i][j] = ' ';					
			}
				else if(icon.equals(idragon)) {
					dragon_pos[0] = i;
					dragon_pos[1] = j;
					result[i][j] = ' ';					
				}
				else if(icon.equals(isword)) {
					sword_pos[0] = i;
					sword_pos[1] = j;
					result[i][j] = ' ';					
				}
				else if(icon.equals(ishield)) {
					shield_pos[0] = i;
					shield_pos[1] = j;
					result[i][j] = ' ';					
				}
				else {
					result[i][j] = ' ';					
				}
			}
		}
	}
}
