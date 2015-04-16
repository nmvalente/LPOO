package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.Component;

public class CustomMaze extends JFrame {

	public Icon clicked;

	public int posEx, posEy; 
	public int[] getPos() {int p[]={posEx,posEy}; return p;}
	public ImageIcon iexitclosed = new ImageIcon("images/exitclosed.gif");
	public ImageIcon ishield = new ImageIcon("images/shield.gif");
	public ImageIcon ihero = new ImageIcon("images/hero.gif");
	public ImageIcon idragon = new ImageIcon("images/dragon.gif");
	public ImageIcon iwall = new ImageIcon("images/wall.gif");
	public ImageIcon isword = new ImageIcon("images/sword.gif");

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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

	@SuppressWarnings("null")
	public char[][] getCustomBoard(){
		char[][] result = null;
		Enumeration<AbstractButton> tmp = buttonGroup.getElements();
		for(int i = 0 ; i < 10 ; i++)
		{
			for(int j = 0 ; j < 10 ; j++)
			{
				System.out.println(10);
				if(tmp.hasMoreElements())
				{
					Icon icon = tmp.nextElement().getIcon();
					if(icon.equals(iwall))
						result[i][j] = 'X';
					else result[i][j] = ' ';

					//				if(icon.equals(ihero))
					//					result[i][j] = 'h';
					if(icon.equals(iexitclosed))
					{posEx = i; posEy =j;}
					//				else if(icon.equals(ishield))
					//					result[i][j] = 'V';
					//				else if(icon.equals(idragon))
					//					result[i][j] = 'd';
					//				else if(icon.equals(iwall))
					//					result[i][j] = 'X';
					//				else if(icon.equals(isword))
					//					result[i][j] = 'S';
				}
			}
		}
		return result;
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


		ImageIcon iexitclosed = new ImageIcon("images/exitclosed.gif");
		ImageIcon ishield = new ImageIcon("images/shield.gif");
		ImageIcon ihero = new ImageIcon("images/hero.gif");
		ImageIcon idragon = new ImageIcon("images/dragon.gif");
		ImageIcon iwall = new ImageIcon("images/wall.gif");
		ImageIcon isword = new ImageIcon("images/sword.gif");

		



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


		/////////////////////////////////////////////////////////////////////

		JButton b00 = new JButton("New button");
		buttonGroup.add(b00);
		b00.setActionCommand("");
		b00.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b00.setIcon(clicked);
			}
		});
		b00.setBounds(0, 0, 97, 63);
		contentPane.add(b00);

		JButton b10 = new JButton("New button");
		buttonGroup.add(b10);
		b10.setActionCommand("");
		b10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b10.setIcon(clicked);
			}
		});
		b10.setBounds(0, 56, 97, 63);
		contentPane.add(b10);

		JButton b20 = new JButton("New button");
		buttonGroup.add(b20);
		b20.setActionCommand("");
		b20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b20.setIcon(clicked);
			}
		});
		b20.setBounds(0, 118, 97, 63);
		contentPane.add(b20);

		JButton b30 = new JButton("New button");
		buttonGroup.add(b30);
		b30.setActionCommand("");
		b30.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b30.setIcon(clicked);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		b30.setBounds(0, 178, 97, 63);
		contentPane.add(b30);

		JButton b40 = new JButton("New button");
		buttonGroup.add(b40);
		b40.setActionCommand("");
		b40.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b40.setIcon(clicked);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		b40.setBounds(0, 238, 97, 63);
		contentPane.add(b40);

		JButton b50 = new JButton("New button");
		buttonGroup.add(b50);
		b50.setActionCommand("");
		b50.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b50.setIcon(clicked);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		b50.setBounds(0, 297, 97, 63);
		contentPane.add(b50);

		JButton b60 = new JButton("New button");
		buttonGroup.add(b60);
		b60.setActionCommand("");
		b60.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b60.setIcon(clicked);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		b60.setBounds(0, 357, 97, 63);
		contentPane.add(b60);

		JButton b70 = new JButton("New button");
		buttonGroup.add(b70);
		b70.setActionCommand("");
		b70.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b70.setIcon(clicked);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		b70.setBounds(0, 416, 97, 63);
		contentPane.add(b70);

		JButton b80 = new JButton("New button");
		buttonGroup.add(b80);
		b80.setActionCommand("");
		b80.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b80.setIcon(clicked);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		b80.setBounds(0, 474, 97, 63);
		contentPane.add(b80);

		JButton b90 = new JButton("New button");
		buttonGroup.add(b90);
		b90.setActionCommand("");
		b90.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b90.setIcon(clicked);
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		b90.setBounds(0, 534, 97, 63);
		contentPane.add(b90);

		///////////////////////////////////////////////////////////

		JButton b01 = new JButton("New button");
		buttonGroup.add(b01);
		b01.setActionCommand("");
		b01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b01.setIcon(clicked);
			}
		});
		b01.setBounds(95, 0, 97, 63);
		contentPane.add(b01);

		JButton b11 = new JButton("New button");
		buttonGroup.add(b11);
		b11.setActionCommand("");
		b11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b11.setIcon(clicked);
			}
		});
		b11.setBounds(95, 56, 97, 63);
		contentPane.add(b11);

		JButton b21 = new JButton("New button");
		buttonGroup.add(b21);
		b21.setActionCommand("");
		b21.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b21.setIcon(clicked);
			}
		});
		b21.setBounds(95, 118, 97, 63);
		contentPane.add(b21);

		JButton b41 = new JButton("New button");
		buttonGroup.add(b41);
		b41.setActionCommand("");
		b41.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b41.setIcon(clicked);
			}
		});
		b41.setBounds(95, 238, 97, 63);
		contentPane.add(b41);

		JButton b31 = new JButton("New button");
		buttonGroup.add(b31);
		b31.setActionCommand("");
		b31.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b31.setIcon(clicked);
			}
		});
		b31.setBounds(95, 178, 97, 63);
		contentPane.add(b31);

		JButton b51 = new JButton("New button");
		buttonGroup.add(b51);
		b51.setActionCommand("");
		b51.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b51.setIcon(clicked);
			}
		});
		b51.setBounds(95, 297, 97, 63);
		contentPane.add(b51);

		JButton b61 = new JButton("New button");
		buttonGroup.add(b61);
		b61.setActionCommand("");
		b61.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b61.setIcon(clicked);
			}
		});
		b61.setBounds(95, 357, 97, 63);
		contentPane.add(b61);

		JButton b71 = new JButton("New button");
		buttonGroup.add(b71);
		b71.setActionCommand("");
		b71.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b71.setIcon(clicked);
			}
		});
		b71.setBounds(95, 416, 97, 63);
		contentPane.add(b71);

		JButton b81 = new JButton("New button");
		buttonGroup.add(b81);
		b81.setActionCommand("");
		b81.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b81.setIcon(clicked);
			}
		});
		b81.setBounds(95, 474, 97, 63);
		contentPane.add(b81);

		JButton b91 = new JButton("New button");
		buttonGroup.add(b91);
		b91.setActionCommand("");
		b91.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b91.setIcon(clicked);
			}
		});
		b91.setBounds(95, 534, 97, 63);
		contentPane.add(b91);

		JButton b02 = new JButton("New button");
		buttonGroup.add(b02);
		b02.setActionCommand("");
		b02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b02.setIcon(clicked);
			}
		});
		b02.setBounds(191, 0, 97, 63);
		contentPane.add(b02);

		JButton b12 = new JButton("New button");
		buttonGroup.add(b12);
		b12.setActionCommand("");
		b12.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b12.setIcon(clicked);
			}
		});
		b12.setBounds(191, 56, 97, 63);
		contentPane.add(b12);

		JButton b22 = new JButton("New button");
		buttonGroup.add(b22);
		b22.setActionCommand("");
		b22.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b22.setIcon(clicked);
			}
		});
		b22.setBounds(191, 118, 97, 63);
		contentPane.add(b22);

		JButton b42 = new JButton("New button");
		buttonGroup.add(b42);
		b42.setActionCommand("");
		b42.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b42.setIcon(clicked);
			}
		});
		b42.setBounds(191, 238, 97, 63);
		contentPane.add(b42);

		JButton b32 = new JButton("New button");
		buttonGroup.add(b32);
		b32.setActionCommand("");
		b32.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b32.setIcon(clicked);
			}
		});
		b32.setBounds(191, 178, 97, 63);
		contentPane.add(b32);

		JButton b52 = new JButton("New button");
		buttonGroup.add(b52);
		b52.setActionCommand("");
		b52.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b52.setIcon(clicked);
			}
		});
		b52.setBounds(191, 297, 97, 63);
		contentPane.add(b52);

		JButton b62 = new JButton("New button");
		buttonGroup.add(b62);
		b62.setActionCommand("");
		b62.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b62.setIcon(clicked);
			}
		});
		b62.setBounds(191, 357, 97, 63);
		contentPane.add(b62);

		JButton b72 = new JButton("New button");
		buttonGroup.add(b72);
		b72.setActionCommand("");
		b72.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b72.setIcon(clicked);
			}
		});
		b72.setBounds(191, 416, 97, 63);
		contentPane.add(b72);

		JButton b82 = new JButton("New button");
		buttonGroup.add(b82);
		b82.setActionCommand("");
		b82.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b82.setIcon(clicked);
			}
		});
		b82.setBounds(191, 474, 97, 63);
		contentPane.add(b82);

		JButton b92 = new JButton("New button");
		buttonGroup.add(b92);
		b92.setActionCommand("");
		b92.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b92.setIcon(clicked);
			}
		});
		b92.setBounds(191, 534, 97, 63);
		contentPane.add(b92);

		JButton b03 = new JButton("New button");
		buttonGroup.add(b03);
		b03.setActionCommand("");
		b03.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b03.setIcon(clicked);
			}
		});
		b03.setBounds(286, 0, 97, 63);
		contentPane.add(b03);

		JButton b13 = new JButton("New button");
		buttonGroup.add(b13);
		b13.setActionCommand("");
		b13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b13.setIcon(clicked);
			}
		});
		b13.setBounds(286, 56, 97, 63);
		contentPane.add(b13);

		JButton b23 = new JButton("New button");
		buttonGroup.add(b23);
		b23.setActionCommand("");
		b23.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b23.setIcon(clicked);
			}
		});
		b23.setBounds(286, 118, 97, 63);
		contentPane.add(b23);

		JButton b43 = new JButton("New button");
		buttonGroup.add(b43);
		b43.setActionCommand("");
		b43.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b43.setIcon(clicked);
			}
		});
		b43.setBounds(286, 238, 97, 63);
		contentPane.add(b43);

		JButton b33 = new JButton("New button");
		buttonGroup.add(b33);
		b33.setActionCommand("");
		b33.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b33.setIcon(clicked);
			}
		});
		b33.setBounds(286, 178, 97, 63);
		contentPane.add(b33);

		JButton b53 = new JButton("New button");
		buttonGroup.add(b53);
		b53.setActionCommand("");
		b53.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b53.setIcon(clicked);
			}
		});
		b53.setBounds(286, 297, 97, 63);
		contentPane.add(b53);

		JButton b63 = new JButton("New button");
		buttonGroup.add(b63);
		b63.setActionCommand("");
		b63.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b63.setIcon(clicked);
			}
		});
		b63.setBounds(286, 357, 97, 63);
		contentPane.add(b63);

		JButton b73 = new JButton("New button");
		buttonGroup.add(b73);
		b73.setActionCommand("");
		b73.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b73.setIcon(clicked);
			}
		});
		b73.setBounds(286, 416, 97, 63);
		contentPane.add(b73);

		JButton b83 = new JButton("New button");
		buttonGroup.add(b83);
		b83.setActionCommand("");
		b83.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b83.setIcon(clicked);
			}
		});
		b83.setBounds(286, 474, 97, 63);
		contentPane.add(b83);

		JButton b93 = new JButton("New button");
		buttonGroup.add(b93);
		b93.setActionCommand("");
		b93.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b93.setIcon(clicked);
			}
		});
		b93.setBounds(286, 534, 97, 63);
		contentPane.add(b93);

		JButton b04 = new JButton("New button");
		buttonGroup.add(b04);
		b04.setActionCommand("");
		b04.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b04.setIcon(clicked);
			}
		});
		b04.setBounds(381, 0, 97, 63);
		contentPane.add(b04);

		JButton b14 = new JButton("New button");
		buttonGroup.add(b14);
		b14.setActionCommand("");
		b14.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b14.setIcon(clicked);
			}
		});
		b14.setBounds(381, 56, 97, 63);
		contentPane.add(b14);

		JButton b24 = new JButton("New button");
		buttonGroup.add(b24);
		b24.setActionCommand("");
		b24.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b24.setIcon(clicked);
			}
		});
		b24.setBounds(381, 118, 97, 63);
		contentPane.add(b24);

		JButton b44 = new JButton("New button");
		buttonGroup.add(b44);
		b44.setActionCommand("");
		b44.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b44.setIcon(clicked);
			}
		});
		b44.setBounds(381, 238, 97, 63);
		contentPane.add(b44);

		JButton b34 = new JButton("New button");
		buttonGroup.add(b34);
		b34.setActionCommand("");
		b34.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b34.setIcon(clicked);
			}
		});
		b34.setBounds(381, 178, 97, 63);
		contentPane.add(b34);

		JButton b54 = new JButton("New button");
		buttonGroup.add(b54);
		b54.setActionCommand("");
		b54.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b54.setIcon(clicked);
			}
		});
		b54.setBounds(381, 297, 97, 63);
		contentPane.add(b54);

		JButton b64 = new JButton("New button");
		buttonGroup.add(b64);
		b64.setActionCommand("");
		b64.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b64.setIcon(clicked);
			}
		});
		b64.setBounds(381, 357, 97, 63);
		contentPane.add(b64);

		JButton b74 = new JButton("New button");
		buttonGroup.add(b74);
		b74.setActionCommand("");
		b74.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b74.setIcon(clicked);
			}
		});
		b74.setBounds(381, 416, 97, 63);
		contentPane.add(b74);

		JButton b84 = new JButton("New button");
		buttonGroup.add(b84);
		b84.setActionCommand("");
		b84.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b84.setIcon(clicked);
			}
		});
		b84.setBounds(381, 474, 97, 63);
		contentPane.add(b84);

		JButton b94 = new JButton("New button");
		buttonGroup.add(b94);
		b94.setActionCommand("");
		b94.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b94.setIcon(clicked);
			}
		});
		b94.setBounds(381, 534, 97, 63);
		contentPane.add(b94);

		JButton b05 = new JButton("New button");
		buttonGroup.add(b05);
		b05.setActionCommand("");
		b05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b05.setIcon(clicked);
			}
		});
		b05.setBounds(477, 0, 97, 63);
		contentPane.add(b05);

		JButton b15 = new JButton("New button");
		buttonGroup.add(b15);
		b15.setActionCommand("");
		b15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b15.setIcon(clicked);
			}
		});
		b15.setBounds(477, 56, 97, 63);
		contentPane.add(b15);

		JButton b25 = new JButton("New button");
		buttonGroup.add(b25);
		b25.setActionCommand("");
		b25.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b25.setIcon(clicked);
			}
		});
		b25.setBounds(477, 118, 97, 63);
		contentPane.add(b25);

		JButton b45 = new JButton("New button");
		buttonGroup.add(b45);
		b45.setActionCommand("");
		b45.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b45.setIcon(clicked);
			}
		});
		b45.setBounds(477, 238, 97, 63);
		contentPane.add(b45);

		JButton b35 = new JButton("New button");
		buttonGroup.add(b35);
		b35.setActionCommand("");
		b35.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b35.setIcon(clicked);
			}
		});
		b35.setBounds(477, 178, 97, 63);
		contentPane.add(b35);

		JButton b55 = new JButton("New button");
		buttonGroup.add(b55);
		b55.setActionCommand("");
		b55.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b55.setIcon(clicked);
			}
		});
		b55.setBounds(477, 297, 97, 63);
		contentPane.add(b55);

		JButton b65 = new JButton("New button");
		buttonGroup.add(b65);
		b65.setActionCommand("");
		b65.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b65.setIcon(clicked);
			}
		});
		b65.setBounds(477, 357, 97, 63);
		contentPane.add(b65);

		JButton b75 = new JButton("New button");
		buttonGroup.add(b75);
		b75.setActionCommand("");
		b75.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b75.setIcon(clicked);
			}
		});
		b75.setBounds(477, 416, 97, 63);
		contentPane.add(b75);

		JButton b85 = new JButton("New button");
		buttonGroup.add(b85);
		b85.setActionCommand("");
		b85.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b85.setIcon(clicked);
			}
		});
		b85.setBounds(477, 474, 97, 63);
		contentPane.add(b85);

		JButton b95 = new JButton("New button");
		buttonGroup.add(b95);
		b95.setActionCommand("");
		b95.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b95.setIcon(clicked);
			}
		});
		b95.setBounds(477, 534, 97, 63);
		contentPane.add(b95);

		JButton b06 = new JButton("New button");
		buttonGroup.add(b06);
		b06.setActionCommand("");
		b06.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b06.setIcon(clicked);
			}
		});
		b06.setBounds(572, 0, 97, 63);
		contentPane.add(b06);

		JButton b16 = new JButton("New button");
		buttonGroup.add(b16);
		b16.setActionCommand("");
		b16.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b16.setIcon(clicked);
			}
		});
		b16.setBounds(572, 56, 97, 63);
		contentPane.add(b16);

		JButton b26 = new JButton("New button");
		buttonGroup.add(b26);
		b26.setActionCommand("");
		b26.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b26.setIcon(clicked);
			}
		});
		b26.setBounds(572, 118, 97, 63);
		contentPane.add(b26);

		JButton b46 = new JButton("New button");
		buttonGroup.add(b46);
		b46.setActionCommand("");
		b46.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b46.setIcon(clicked);
			}
		});
		b46.setBounds(572, 238, 97, 63);
		contentPane.add(b46);

		JButton b36 = new JButton("New button");
		buttonGroup.add(b36);
		b36.setActionCommand("");
		b36.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b36.setIcon(clicked);
			}
		});
		b36.setBounds(572, 176, 97, 63);
		contentPane.add(b36);

		JButton b56 = new JButton("New button");
		buttonGroup.add(b56);
		b56.setActionCommand("");
		b56.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b56.setIcon(clicked);
			}
		});
		b56.setBounds(572, 297, 97, 63);
		contentPane.add(b56);

		JButton b66 = new JButton("New button");
		buttonGroup.add(b66);
		b66.setActionCommand("");
		b66.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b66.setIcon(clicked);
			}
		});
		b66.setBounds(572, 357, 97, 63);
		contentPane.add(b66);

		JButton b76 = new JButton("New button");
		buttonGroup.add(b76);
		b76.setActionCommand("");
		b76.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b76.setIcon(clicked);
			}
		});
		b76.setBounds(572, 416, 97, 63);
		contentPane.add(b76);

		JButton b86 = new JButton("New button");
		buttonGroup.add(b86);
		b86.setActionCommand("");
		b86.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b86.setIcon(clicked);
			}
		});
		b86.setBounds(572, 474, 97, 63);
		contentPane.add(b86);

		JButton b96 = new JButton("New button");
		buttonGroup.add(b96);
		b96.setActionCommand("");
		b96.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b96.setIcon(clicked);
			}
		});
		b96.setBounds(572, 534, 97, 63);
		contentPane.add(b96);

		JButton b07 = new JButton("New button");
		buttonGroup.add(b07);
		b07.setActionCommand("");
		b07.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b07.setIcon(clicked);
			}
		});
		b07.setBounds(666, 0, 97, 63);
		contentPane.add(b07);

		JButton b17 = new JButton("New button");
		buttonGroup.add(b17);
		b17.setActionCommand("");
		b17.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b17.setIcon(clicked);
			}
		});
		b17.setBounds(666, 56, 97, 63);
		contentPane.add(b17);

		JButton b27 = new JButton("New button");
		buttonGroup.add(b27);
		b27.setActionCommand("");
		b27.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b27.setIcon(clicked);
			}
		});
		b27.setBounds(666, 118, 97, 63);
		contentPane.add(b27);

		JButton b47 = new JButton("New button");
		buttonGroup.add(b47);
		b47.setActionCommand("");
		b47.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b47.setIcon(clicked);
			}
		});
		b47.setBounds(666, 238, 97, 63);
		contentPane.add(b47);

		JButton b37 = new JButton("New button");
		buttonGroup.add(b37);
		b37.setActionCommand("");
		b37.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b37.setIcon(clicked);
			}
		});
		b37.setBounds(666, 178, 97, 63);
		contentPane.add(b37);

		JButton b57 = new JButton("New button");
		buttonGroup.add(b57);
		b57.setActionCommand("");
		b57.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b57.setIcon(clicked);
			}
		});
		b57.setBounds(666, 297, 97, 63);
		contentPane.add(b57);

		JButton b67 = new JButton("New button");
		buttonGroup.add(b67);
		b67.setActionCommand("");
		b67.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b67.setIcon(clicked);
			}
		});
		b67.setBounds(666, 357, 97, 63);
		contentPane.add(b67);

		JButton b77 = new JButton("New button");
		buttonGroup.add(b77);
		b77.setActionCommand("");
		b77.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b77.setIcon(clicked);
			}
		});
		b77.setBounds(666, 416, 97, 63);
		contentPane.add(b77);

		JButton b87 = new JButton("New button");
		buttonGroup.add(b87);
		b87.setActionCommand("");
		b87.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b87.setIcon(clicked);
			}
		});
		b87.setBounds(666, 474, 97, 63);
		contentPane.add(b87);

		JButton b97 = new JButton("New button");
		buttonGroup.add(b97);
		b97.setActionCommand("");
		b97.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b97.setIcon(clicked);
			}
		});
		b97.setBounds(666, 534, 97, 63);
		contentPane.add(b97);

		JButton b08 = new JButton("New button");
		buttonGroup.add(b08);
		b08.setActionCommand("");
		b08.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b08.setIcon(clicked);
			}
		});
		b08.setBounds(761, 0, 97, 63);
		contentPane.add(b08);

		JButton b18 = new JButton("New button");
		buttonGroup.add(b18);
		b18.setActionCommand("");
		b18.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b18.setIcon(clicked);
			}
		});
		b18.setBounds(761, 56, 97, 63);
		contentPane.add(b18);

		JButton b28 = new JButton("New button");
		buttonGroup.add(b28);
		b28.setActionCommand("");
		b28.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b28.setIcon(clicked);
			}
		});
		b28.setBounds(761, 118, 97, 63);
		contentPane.add(b28);

		JButton b48 = new JButton("New button");
		buttonGroup.add(b48);
		b48.setActionCommand("");
		b48.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b48.setIcon(clicked);
			}
		});
		b48.setBounds(761, 238, 97, 63);
		contentPane.add(b48);

		JButton b38 = new JButton("New button");
		buttonGroup.add(b38);
		b38.setActionCommand("");
		b38.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b38.setIcon(clicked);
			}
		});
		b38.setBounds(761, 178, 97, 63);
		contentPane.add(b38);

		JButton b58 = new JButton("New button");
		buttonGroup.add(b58);
		b58.setActionCommand("");
		b58.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b58.setIcon(clicked);
			}
		});
		b58.setBounds(761, 297, 97, 63);
		contentPane.add(b58);

		JButton b68 = new JButton("New button");
		buttonGroup.add(b68);
		b68.setActionCommand("");
		b68.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b68.setIcon(clicked);
			}
		});
		b68.setBounds(761, 357, 97, 63);
		contentPane.add(b68);

		JButton b78 = new JButton("New button");
		buttonGroup.add(b78);
		b78.setActionCommand("");
		b78.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b78.setIcon(clicked);
			}
		});
		b78.setBounds(761, 416, 97, 63);
		contentPane.add(b78);

		JButton b88 = new JButton("New button");
		buttonGroup.add(b88);
		b88.setActionCommand("");
		b88.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b88.setIcon(clicked);
			}
		});
		b88.setBounds(761, 474, 97, 63);
		contentPane.add(b88);

		JButton b98 = new JButton("New button");
		buttonGroup.add(b98);
		b98.setActionCommand("");
		b98.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b98.setIcon(clicked);
			}
		});
		b98.setBounds(761, 534, 97, 63);
		contentPane.add(b98);

		JButton b09 = new JButton("New button");
		buttonGroup.add(b09);
		b09.setActionCommand("");
		b09.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b09.setIcon(clicked);
			}
		});
		b09.setBounds(855, 0, 97, 63);
		contentPane.add(b09);

		JButton b19 = new JButton("New button");
		buttonGroup.add(b19);
		b19.setActionCommand("");
		b19.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b19.setIcon(clicked);
			}
		});
		b19.setBounds(855, 56, 97, 63);
		contentPane.add(b19);

		JButton b29 = new JButton("New button");
		buttonGroup.add(b29);
		b29.setActionCommand("");
		b29.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b29.setIcon(clicked);
			}
		});
		b29.setBounds(855, 118, 97, 63);
		contentPane.add(b29);

		JButton b49 = new JButton("New button");
		buttonGroup.add(b49);
		b49.setActionCommand("");
		b49.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b49.setIcon(clicked);
			}
		});
		b49.setBounds(855, 238, 97, 63);
		contentPane.add(b49);

		JButton b39 = new JButton("New button");
		buttonGroup.add(b39);
		b39.setActionCommand("");
		b39.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b39.setIcon(clicked);
			}
		});
		b39.setBounds(855, 178, 97, 63);
		contentPane.add(b39);

		JButton b59 = new JButton("New button");
		buttonGroup.add(b59);
		b59.setActionCommand("");
		b59.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b59.setIcon(clicked);
			}
		});
		b59.setBounds(855, 297, 97, 63);
		contentPane.add(b59);

		JButton b69 = new JButton("New button");
		buttonGroup.add(b69);
		b69.setActionCommand("");
		b69.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b69.setIcon(clicked);
			}
		});
		b69.setBounds(855, 357, 97, 63);
		contentPane.add(b69);

		JButton b79 = new JButton("New button");
		buttonGroup.add(b79);
		b79.setActionCommand("");
		b79.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b79.setIcon(clicked);
			}
		});
		b79.setBounds(855, 416, 97, 63);
		contentPane.add(b79);

		JButton b89 = new JButton("New button");
		buttonGroup.add(b89);
		b89.setActionCommand("");
		b89.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b89.setIcon(clicked);
			}
		});
		b89.setBounds(855, 474, 97, 63);
		contentPane.add(b89);

		JButton b99 = new JButton("New button");
		buttonGroup.add(b99);
		b99.setActionCommand("");
		b99.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				b99.setIcon(clicked);
			}
		});
		b99.setBounds(855, 534, 97, 63);
		contentPane.add(b99);



		JButton btnOk = new JButton("Ok");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getCustomBoard();
				System.out.println(posEx + " " + posEy);
				dispose();
			}
		});
		btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOk.setBounds(976, 564, 97, 25);
		contentPane.add(btnOk);
	}
}
