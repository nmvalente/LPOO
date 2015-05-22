package extras;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class JavaApplication117 {

	Toolkit toolkit = Toolkit.getDefaultToolkit();  
	Dimension scrnsize = toolkit.getScreenSize();   
	int screenH = (int)scrnsize.getHeight();
	int screenW = (int)scrnsize.getWidth();
    
	//change this to your own
	
    static String filename="images/BattleshipStartScreen.gif";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new JavaApplication117().createAndShowUI();
            }
        });
    }

    private void createAndShowUI() {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(frame);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents(JFrame frame) {
        final BufferedImage img = new ImgUtils().scaleImage(screenW, screenH, filename);
        //create label with image as background
        JLabel label = new JLabel(new ImageIcon((Image) img));

        frame.getContentPane().add(label, BorderLayout.CENTER);
    }
}

class ImgUtils {

    public BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) {
        BufferedImage bi = null;
        try {
            ImageIcon ii = new ImageIcon(filename);//path to image
            bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bi;
    }
}
