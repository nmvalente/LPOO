package gui;

import javax.swing.*;

import java.io.*;

public class SaveGameMenu extends JFileChooser
{
	private static final long serialVersionUID = 1L;

	public SaveGameMenu()
	{
		int retorno = 0;
		@SuppressWarnings("unused")
		File arquivoSelec = null;
		JFileChooser guardaArquivo = new JFileChooser();
		guardaArquivo.setCurrentDirectory(new File("."));
		guardaArquivo.setSelectedFile(new File("load_game.xml")); 
		retorno = guardaArquivo.showSaveDialog(this);

		if (retorno == JFileChooser.APPROVE_OPTION)
			arquivoSelec = guardaArquivo.getSelectedFile();
	}

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