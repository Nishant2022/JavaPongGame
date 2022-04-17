import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This is the main class for the pong game. Inherits from JFrame, to create
 * frame for program.
 * 
 * @author Nishant Dash
 * @version 1.0
 * @since 2020-16-13
 */

@SuppressWarnings("serial")
public class Pong extends JFrame {
	private static final int WIDTH = 1400, HEIGHT = 900;
	private PongPanel panel;

	public Pong() {
		setSize(WIDTH, HEIGHT);
		setTitle("Nishant's Pong Game");
		setResizable(true);
		setMinimumSize(new Dimension(700, 450));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new PongPanel(this);
		add(panel);

	}

	public static void main(String[] args) {
		new Pong();
	}
}
