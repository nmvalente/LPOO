package audio;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LoopSound {

    public LoopSound() throws Exception{
    //static void main(String[] args) throws Exception {
    	File file = new File("musics/KissesinParadise.wav");
       /* URL url = new URL(
                          "https://ouvirmusica.com.br/mosaicwav/1049194/"); */
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread 
                // from terminating at the end of the main()
               // JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        });
    }
}