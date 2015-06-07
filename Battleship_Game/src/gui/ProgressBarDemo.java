package gui;

import java.awt.*;

//import java.awt.event.*;
import javax.swing.*;

import java.beans.*;
import java.util.Random;
 
/**
 * The Class ProgressBarDemo will show a progress bar of 0% to 100%.
 */
public class ProgressBarDemo extends JPanel implements PropertyChangeListener {
 
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The progress bar. */
	private JProgressBar progressBar;

    /** The task. */
    private Task task;
    
    /**
     * Gets the progress.
     *
     * @return the progress in percentage
     */
    public int getProgress() {return progress;}
    
    /** The progress. */
    public int progress = 0;
    
    /**
     * The Class Task.
     */
    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        /* (non-Javadoc)
         * @see javax.swing.SwingWorker#doInBackground()
         */
        @Override
        public Void doInBackground() {
        	setBackground(Color.black);
            Random random = new Random();
            progress = 0;
            //Initialize progress property.
            setProgress(0);
            while (progress < 100) {
                //Sleep for up to one second.
                try {
                    Thread.sleep(random.nextInt(200));
                } catch (InterruptedException ignore) {}
                //Make random progress.
                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));
            }
            return null;
        }
 
        /*
         * Executed in event dispatching thread
         */
        /* (non-Javadoc)
         * @see javax.swing.SwingWorker#done()
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
           // startButton.setEnabled(true);
            setCursor(null); //turn off the wait cursor
            //taskOutput.append("Done!\n");
        }
    }
 
    /**
     * Instantiates a new progress bar demo.
     */
    public ProgressBarDemo() {
        super(new BorderLayout());
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.black);

        JPanel panel = new JPanel();
        panel.add(progressBar);
        panel.setBackground(Color.black);
 
        add(panel, BorderLayout.PAGE_START);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
 
    /**
     * Invoked when the getStart() method was initialized
     *
     */
    public void getStart(){
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        
        	 task = new Task();
             task.addPropertyChangeListener(this);
             task.execute();
    }
 
    /**
     * Invoked when task's progress property changes.
     *
     * @param evt the evt that listens a change on progress bar
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
        } 
    }
 
 
    /**
     * Create the GUI and show it. As with all GUI code, this must run
     * on the event-dispatching thread.
     */
    private static void createAndShowGUI() {

        //Create and set up the content pane.
        JComponent newContentPane = new ProgressBarDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
    }
 
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}