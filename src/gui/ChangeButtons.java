package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;

public class ChangeButtons extends JDialog implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	char left='a', right='d', up='s', down='w';
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
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton Up = new JButton("Up");
			Up.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					up = e.getKeyChar();
				}
			});
			Up.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					Up.setBackground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Up.setBackground(Color.white);
				}
			});
			Up.setBounds(159, 13, 110, 70);
			contentPanel.add(Up);

		}
		{
			JButton Down = new JButton("Down");
			Down.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					down = e.getKeyChar();
				}
			});
			Down.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					Down.setBackground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Down.setBackground(Color.white);
				}
			});
			Down.setBounds(159, 123, 110, 70);
			contentPanel.add(Down);
		}
		{
			JButton Right = new JButton("Right");
			Right.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					right = e.getKeyChar();
				}
			});
			Right.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					Right.setBackground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Right.setBackground(Color.white);
				}
			});
			Right.setBounds(310, 74, 110, 70);
			contentPanel.add(Right);
		}
		{
			JButton Left = new JButton("Left");
			Left.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					Left.setBackground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Left.setBackground(Color.white);
				}
			});
			Left.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					left = e.getKeyChar();
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
						dispose();
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

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
