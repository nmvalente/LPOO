package gui;

import javax.swing.*;

import java.io.*;
 
public class LoadGameMenu extends JFileChooser
{
	private static final long serialVersionUID = 1L;
	private static File arquivoSelec = null;
	private static JFileChooser abreArquivo = new JFileChooser();
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

	public static void main (String[] args)
	{
		try {
			LoadGameMenu fr = new LoadGameMenu();
			fr.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File getArquivoSelec() {
		return arquivoSelec;
	}

	public static void setArquivoSelec(File arquivoSelec) {
		LoadGameMenu.arquivoSelec = arquivoSelec;
	}
}