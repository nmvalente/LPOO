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
	private static File arquivoSelec = null;
	
	/** The abre arquivo. */
	private static JFileChooser abreArquivo = new JFileChooser();
	
	/**
	 * Instantiates a new load game menu
	 */
	public LoadGameMenu()
	{
		int retorno = 0;

		abreArquivo.setCurrentDirectory(new File("."));
		abreArquivo.setSelectedFile(new File("game.xml")); 
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
			LoadGameMenu fr = new LoadGameMenu();
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
	public static void setArquivoSelec(File arquivoSelec) {
		LoadGameMenu.arquivoSelec = arquivoSelec;
	}
}