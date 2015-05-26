package gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

public abstract class FrameTools extends JFrame{
	private static final long serialVersionUID = 1L;
	public int width, height;	
	
	public void setCaracteristics() {
		setAlwaysOnTop(true);
		setUndecorated(true);
		setResizable(false);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setMaximizedWindow(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = ge.getDefaultScreenDevice();       
		screen.setFullScreenWindow(this);
		width = (int)getBounds().getWidth();
		height = (int)getBounds().getHeight();
		setSize(width, height);
	}
	
	public JFrame getFrame(){
		return this;
	}
}