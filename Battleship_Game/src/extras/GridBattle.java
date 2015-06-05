package extras;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class GridBattle  {

	private static final Dimension PREF_SIZE = new Dimension(50, 50);
	private JPanel ui;
	private JPanel gameGrid;
	private final int row;
	private final int col;
	public GridBattle(int row, int col) {
		this.row = row;
		this.col = col;
		initUI();
	}

	private final void initUI() {
		ui = new JPanel(new BorderLayout(3, 3));
		createMap(row, col);
		ui.add(gameGrid);
	}

	public void createMap(int maxX, int maxY) {
		gameGrid = new JPanel(new GridLayout(maxX, maxY, 4, 4));
		gameGrid.setBackground(Color.GRAY);

		for (int i = 0; i < maxX; i++) {

			for (int j = 0; j < maxY; j++) {
				CellPane cell = new CellPane();
				cell.setPreferredSize(PREF_SIZE);
				gameGrid.add(cell);
			}
		}
	}

	public class CellPane extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Color defaultBackground;

		public CellPane() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					defaultBackground = getBackground();
					setBackground(Color.BLUE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setBackground(defaultBackground);
				}
			});

		}
	}

	private static void createAndShowGui() {
		JFrame frame = new JFrame("name");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GridBattle(10,10).getUi());
		// this was being called at the wrong time, but ..
		// frame.setLocationRelativeTo(null);
		// ..better to..
		frame.setLocationByPlatform(true);
		frame.pack();
		frame.setVisible(true);  // should be last.
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGui();
			}
		});
	}

	public JPanel getUi() {
		return ui;
	}
}