package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PaintContext;
import javax.swing.JPanel;
import logic.*;

public class desenho extends JPanel {

	desenho()
	{
		//Image cell = new  
	}
	protected void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(10, 10, 100, 100);
		//g.drawImage(img, x, y, observer)
	}
	
}
