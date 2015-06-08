package gui;

import javax.swing.*;

import java.io.*;
 
/**
 * The Class LoadGameMenu.
 */
public class LoadGameMenu extends JFileChooser
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The arquivo selec. */
	private File arquivoSelec = null;
	
	/** The abre arquivo. */
	private JFileChooser abreArquivo = new JFileChooser();
	
	/**
	 * Instantiates a new load game menu
	 */
	public LoadGameMenu(String currDir, String selectedFile)
	{
		int retorno = 0;

		abreArquivo.setCurrentDirectory(new File(currDir));
		abreArquivo.setSelectedFile(new File(selectedFile)); 
		retorno = abreArquivo.showOpenDialog(this);

		if (retorno == JFileChooser.APPROVE_OPTION)
		{
			setArquivoSelec(abreArquivo.getSelectedFile());
			System.out.println(arquivoSelec);
		}  
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main (String[] args)
	{
		try {
			LoadGameMenu fr = new LoadGameMenu("txt/.", "config.txt");
			fr.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the arquivo selec.
	 *
	 * @return the arquivo selec
	 */
	public File getArquivoSelec() {
		return arquivoSelec;
	}

	/**
	 * Sets the arquivo selec.
	 *
	 * @param arquivoSelec the new arquivo selec
	 */
	public void setArquivoSelec(File arquivoSelec) {
		this.arquivoSelec = arquivoSelec;
	}
 
}