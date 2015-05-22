package extras;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class AbasRotuladas extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel Abas;

	private JButton CDArquivo1;
	private JLabel LabelArquivo1;
	private JLabel LabelArquivo2;

	public AbasRotuladas()
	{
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
		setSize(450,150);
		setLocation(300,200);
		setTitle("Caixas de Diálogo de...");
		Abas = new JPanel();
		JPanel PCxDlgArquivo = new JPanel();
		CDArquivo1 = new JButton("Arquivo 1");
		LabelArquivo1 = new JLabel("Retorno:");
		LabelArquivo2 = new JLabel("Seleção:");
		PCxDlgArquivo.add(CDArquivo1);
		PCxDlgArquivo.add(LabelArquivo1);
		PCxDlgArquivo.add(LabelArquivo2);
		Abas.add("Arquivo",PCxDlgArquivo);

		add(Abas);

		CDArquivo1.addActionListener(this);

	}
	public void actionPerformed(ActionEvent evt)
	{
		Object origem = evt.getSource();
		int retorno = 0;

		if (origem == CDArquivo1)
		{
			JFileChooser abreArquivo = new JFileChooser();
			abreArquivo.setCurrentDirectory(new File("."));
			abreArquivo.setSelectedFile(new File("AbasRotuladas.java"));
			retorno = abreArquivo.showOpenDialog(this);
			LabelArquivo1.setText("Retorno: " + retorno);
			if (retorno == JFileChooser.APPROVE_OPTION)
			{
				File arquivoSelec = abreArquivo.getSelectedFile();
				LabelArquivo2.setText("Seleção: " + arquivoSelec.getName());
			}
			else
				LabelArquivo2.setText("Seleção: Nenhum arquivo");
		}
	}
	public static void main (String[] args)
	{
		AbasRotuladas fr = new AbasRotuladas();
		fr.setVisible(true);
	}
}