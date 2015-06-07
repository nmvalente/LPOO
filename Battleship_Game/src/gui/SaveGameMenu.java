package gui;

import javax.swing.*;

import java.io.*;

/**
 * The Class SaveGameMenu to Save a game in a file
 */
public class SaveGameMenu extends JFileChooser
{	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new save game menu.
	 */
	public SaveGameMenu()
	{
		int retorno = 0;
		@SuppressWarnings("unused")
		File arquivoSelec = null;
		JFileChooser guardaArquivo = new JFileChooser();
		guardaArquivo.setCurrentDirectory(new File("."));
		guardaArquivo.setSelectedFile(new File("game.xml")); 
		retorno = guardaArquivo.showSaveDialog(this);

		if (retorno == JFileChooser.APPROVE_OPTION)
			arquivoSelec = guardaArquivo.getSelectedFile();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main (String[] args)
	{
		try {
			SaveGameMenu fr = new SaveGameMenu();
			fr.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}