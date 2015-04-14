package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

public class ChangeButtons extends JDialog implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public char left='a', right='d', up='s', down='w';
	public char getLeft(){return left;}
	public char getRight(){return right;}
	public char getUp(){return up;}
	public char getDown(){return down;}

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChangeButtons dialog = new ChangeButtons();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChangeButtons() {
		this.addKeyListener(this);

		setBounds(700, 300, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			JButton Up = new JButton("Up");
			Color c = Up.getBackground();
			Up.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					up = e.getKeyChar();
					String j = new String(up+"");
					Up.setText(j);
				}
			});
			Up.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Up.setBackground(Color.GRAY);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Up.setBackground(c);
				}
			});
			Up.setBounds(159, 13, 110, 70);
			contentPanel.add(Up);
		}
		{
			JButton Down = new JButton("Down");
			Color c = Down.getBackground();
			Down.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					down = e.getKeyChar();
					String j = new String(down+"");
					Down.setText(j);
				}
			});
			Down.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Down.setBackground(Color.GRAY);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Down.setBackground(c);
				}
			});
			Down.setBounds(159, 123, 110, 70);
			contentPanel.add(Down);
		}
		{
			JButton Right = new JButton("Right");
			Color c = Right.getBackground();
			Right.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					right = e.getKeyChar();
					String j = new String(right+"");
					Right.setText(j);
				}
			});
			Right.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Right.setBackground(Color.GRAY);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Right.setBackground(c);
				}
			});
			Right.setBounds(310, 74, 110, 70);
			contentPanel.add(Right);
		}
		{
			JButton Left = new JButton("Left");
			Color c = Left.getBackground();
			Left.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Left.setBackground(Color.GRAY);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Left.setBackground(c);
				}
			});
			Left.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					left = e.getKeyChar();
					String j = new String(left+"");
					Left.setText(j);
				}
			});
			Left.setBounds(12, 74, 110, 70);
			contentPanel.add(Left);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						boolean b = verificaTeclas();
						if(b == true)
							dispose();
						else{
							JOptionPane.showMessageDialog(null, "Please, enter new keys because you have repeated");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						left='a'; right='d'; up='s'; down='w';
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean verificaTeclas(){
		ArrayList<Character> lista = new ArrayList();
		ArrayList<Character> aux = new ArrayList();

		lista.add(right);
		lista.add(left);
		lista.add(up);
		lista.add(down);
		aux.add(right);
		for(int i = 0 ; i < lista.size(); i++)
		{
			if(! aux.contains(lista.get(i)))
				aux.add(lista.get(i));
		}
		if(aux.size() == 4)
			return true;
		else return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
