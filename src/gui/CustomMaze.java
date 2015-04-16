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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Insets;

public class CustomMaze extends JFrame {


	char[][] result = new char[10][10];
	public ArrayList<ArrayList<JButton>> botoes = new ArrayList<ArrayList<JButton>>();
	public int[] exit_pos={2,0};
	public int[] hero_pos={2,1};
	public int[] sword_pos={6,8};
	public int[] shield_pos={4,8};
	public int[] dragon_pos={2,8};
	public char[][] getLabirinto() {return result;}

	ImageIcon iwall = new ImageIcon("images/wall90.gif");
	ImageIcon ihero = new ImageIcon("images/hero90.gif");
	ImageIcon idragon = new ImageIcon("images/dragon90.gif");
	ImageIcon isword = new ImageIcon("images/sword90.gif");
	ImageIcon ishield = new ImageIcon("images/shield90.gif");
	ImageIcon iexitclosed = new ImageIcon("images/exitclosed90.gif");
	ImageIcon inada = new ImageIcon("images/nothing.gif");

	public Icon clicked = inada;
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
		setBounds(400, 50, 750, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		criaBotoes();

		///////////////////////////////////////////////////

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIconTextGap(0);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = btnNewButton.getIcon();
			}
		});
		btnNewButton.setIcon(ihero);
		btnNewButton.setBounds(650, 400, 60, 60);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setIconTextGap(0);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = btnNewButton_1.getIcon();
			}
		});
		btnNewButton_1.setIcon(iexitclosed);
		btnNewButton_1.setBounds(650, 320, 60, 60);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setIconTextGap(0);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = btnNewButton_2.getIcon();
			}
		});
		btnNewButton_2.setIcon(ishield);
		btnNewButton_2.setBounds(650, 240, 60, 60);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setIconTextGap(0);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = btnNewButton_3.getIcon();
			}
		});
		btnNewButton_3.setIcon(iwall);
		btnNewButton_3.setBounds(650, 160, 60, 60);
		contentPane.add(btnNewButton_3);

		JButton button = new JButton("New button");
		button.setIconTextGap(0);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = button.getIcon();
			}
		});
		button.setIcon(idragon);
		button.setBounds(650, 90, 60, 60);
		contentPane.add(button);

		JButton button_1 = new JButton("New button");
		button_1.setIconTextGap(0);
		button_1.setMaximumSize(new Dimension(90, 25));
		button_1.setMinimumSize(new Dimension(90, 25));
		button_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = button_1.getIcon();
			}
		});
		button_1.setIcon(isword);
		button_1.setBounds(650, 480, 60, 60);
		contentPane.add(button_1);

		/////////////////////////////////////////////////////////////

		JButton btnOk = new JButton("OK");
		btnOk.setVerifyInputWhenFocusTarget(false);
		btnOk.setMaximumSize(new Dimension(60, 30));
		btnOk.setPreferredSize(new Dimension(60, 30));
		btnOk.setMargin(new Insets(0, 0, 0, 0));
		btnOk.setMinimumSize(new Dimension(60, 30));
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getCustomBoard();
				dispose();
			}
		});
		btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOk.setBounds(642, 560, 74, 30);
		contentPane.add(btnOk);

		JButton button_2 = new JButton("blank");
		button_2.setIconTextGap(0);
		button_2.setIcon(inada);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clicked = button_2.getIcon();
			}
		});
		button_2.setBounds(650, 20, 60, 60);
		contentPane.add(button_2);
	}

	//////////////////////////////////////////////////////////

	public void criaBotoes(){
		ArrayList<JButton> colunas = null;
		for(int i = 0 ; i < 10 ; i++)
		{
			colunas = new ArrayList<JButton>();
			for(int j = 0 ; j < 10 ; j++)
			{
				String s = "b"+i+j;
				JButton button = new JButton(s);
				button.setIcon(inada);
				button.setBorder(new LineBorder(Color.LIGHT_GRAY));
				button.setActionCommand("");
				button.setBounds(contentPane.getWidth() / 10 + i * 61, contentPane.getHeight() / 10 + j * 61, 60, 60);
				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						if (e.getModifiers() == MouseEvent.BUTTON1_MASK)
							button.setIcon(clicked);
					}
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
				Icon icon = botoes.get(j).get(i).getIcon();

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
