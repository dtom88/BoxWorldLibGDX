package boxWorld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoxWorld {

	public static void main(String[] args) {

		final Color[] colors = { Color.lightGray, Color.orange, Color.yellow,
				Color.red, Color.gray, Color.darkGray };
		MyFileReader reader = new MyFileReader();

		final Model model = new Model(reader);

		final View view = new View();

		final Controller controller = new Controller();

		model.addListener(controller);

		controller.setView(view);
		controller.setModel(model);

		JFrame frame = new JFrame("Box World");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(600, 700));

		JLabel label1 = new JLabel("To start press 'Enter'");
		label1.setPreferredSize(new Dimension(600, 20));
		JLabel label2 = new JLabel("To undo press 'z'");
		label2.setPreferredSize(new Dimension(600, 20));
		JLabel label3 = new JLabel("To restart press 'r'");
		label3.setPreferredSize(new Dimension(600, 20));
		JLabel label4 = new JLabel("To go to next level press 'Space'");
		label4.setPreferredSize(new Dimension(600, 20));

		frame.add(panel);
		frame.pack();
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		frame.setVisible(true);

		final Graphics2D graphics = (Graphics2D) panel.getGraphics();
		view.setGraphics(new Graphics() {

			@Override
			public void fillRect(int x, int y, int width, int height,
					int colorIndex) {
				graphics.setColor(colors[colorIndex]);
				graphics.fillRect(x, y, width, height);
			}

			@Override
			public void clearField() {
				if (model.getFieldMatrix() != null
						&& model.getLevel() != Field.FINISH_LEVEL) {
					graphics.clearRect(View.ORIGIN_X, View.ORIGIN_Y,
							View.CELL_SIZE * model.getFieldMatrix().length,
							View.CELL_SIZE * model.getFieldMatrix()[0].length);
				}
				if (model.getLevel() >= Field.FINISH_LEVEL) {
					JOptionPane.showMessageDialog(panel,
							"It is the finish level");
				}
			}

			@Override
			public void showMessage() {
				JOptionPane.showMessageDialog(panel,
						"Well done! Go to next level!");
			}

		});

		frame.addKeyListener(new KeyAdapter() {
			private int flag;

			@Override
			public void keyPressed(final KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					controller.setUp();
					flag = 1;
					break;
				case KeyEvent.VK_LEFT:
					controller.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					controller.moveRight();
					break;
				case KeyEvent.VK_DOWN:
					controller.moveDown();
					break;
				case KeyEvent.VK_UP:
					controller.moveUp();
					break;
				case KeyEvent.VK_Z:
					controller.undo();
					break;
				case KeyEvent.VK_R:
					controller.restart();
					break;
				case KeyEvent.VK_SPACE:
					if (flag > 0) {
						view.clear();
						controller.goToNextLevel();
					}
					break;
				}
			}
		});
	}
}
