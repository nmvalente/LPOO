package gui;

import javax.swing.*;

import java.io.*;

public class LoadGameMenu extends JFileChooser
{
	private static final long serialVersionUID = 1L;

	public LoadGameMenu()
	{
		int retorno = 0;
		@SuppressWarnings("unused")
		File arquivoSelec = null;
		JFileChooser abreArquivo = new JFileChooser();
		abreArquivo.setCurrentDirectory(new File("."));
		abreArquivo.setSelectedFile(new File("load_game.xml")); 
		retorno = abreArquivo.showOpenDialog(this);

		if (retorno == JFileChooser.APPROVE_OPTION)
			arquivoSelec = abreArquivo.getSelectedFile();
	}

	public static void main (String[] args)
	{
		try {
			LoadGameMenu fr = new LoadGameMenu();
			fr.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}