package audio;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javafx.scene.media.AudioClip;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class IntroSound extends JButton implements ActionListener {

	private static Clip clip;
	private static ImageIcon imageOn, imageOff;
	private static boolean playTouch = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public void init(String musicFilename) throws Exception{
		File file = new File(musicFilename);
		clip = AudioSystem.getClip();
		AudioInputStream ais = AudioSystem.getAudioInputStream(file);
		clip.open(ais);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		setSize(new Dimension(25, 25));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setOpaque(false);
		setIcon(imageOn);
		setName("Play");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (playTouch == false){
			clip.stop();
			setIcon(imageOff);
			setFocusable(false);
		} 
		else{
			setIcon(imageOn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			setFocusable(false);
		}
		playTouch = !playTouch;
	}
	
	public IntroSound(String musicFilename) throws Exception{
		imageOn = new ImageIcon("images/SoundOn.png");
		imageOff = new ImageIcon("images/SoundOff.jpeg");
		init(musicFilename);	
	}
}

