package audio;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IntroSound extends JButton implements ActionListener {

	private static Clip clip;
	private static ImageIcon imageOn, imageOff;
	private static boolean playTouch = false;
	private static final long serialVersionUID = 1L;
	private static final String filenameOn = "images/SoundOn.png";
	private static final String filenameOff = "images/SoundOff.jpeg";
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
		imageOn = new ImageIcon(filenameOn);
		imageOff = new ImageIcon(filenameOff);
		init(musicFilename);	
	}
	
	public void stop(){
		clip.stop();
		setIcon(imageOff);
		playTouch = !playTouch;
	}
	
	public void play(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		setIcon(imageOn);
		playTouch = !playTouch;
	}
	
	public boolean alreadyPlaying(){
		return playTouch;
	}
}

