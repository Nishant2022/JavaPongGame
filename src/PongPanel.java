import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;
import javax.swing.JPanel;

/**
 * This is the panel of the pong game. Inherits from JPanel to create panel.
 * Implements MouseMotionListener and ActionListener to control player paddle.
 * 
 * @author Nishant Dash
 * @version 1.0
 * @since 2020-16-13
 */

@SuppressWarnings("serial")
public class PongPanel extends JPanel implements MouseMotionListener, ActionListener {
	private Ball ball;
	private Paddle playerPaddle, computerPaddle;
	private Pong game;
	private Timer timer;
	private WinStatus status = WinStatus.NONE;

	public PongPanel(Pong game) {
		this.game = game;
		setBackground(new Color(241, 250, 238));
		playerPaddle = new Paddle(game);
		computerPaddle = new Paddle(game, playerPaddle, true);
		ball = new Ball(game, playerPaddle, computerPaddle);
		computerPaddle.addBall(ball);
		addMouseMotionListener(this);
		timer = new Timer(5, this);
		timer.start();

	}

	private enum WinStatus {
		WIN, LOSS, NONE
	}

	/*
	 * Returns 1 if player wins. Returns 2 if computer wins. Returns -1 if no one
	 * wins.
	 */
	public void checkWin() {
		int playerScore = playerPaddle.getScore();
		int computerScore = computerPaddle.getScore();
		if (playerScore >= 5 || computerScore >= 5) {
			if (playerScore - computerScore >= 2) {
				status = WinStatus.WIN;
			} else if (playerScore - computerScore <= -2) {
				status = WinStatus.LOSS;
			}
		} else {
			status = WinStatus.NONE;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		;

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		playerPaddle.setY(e.getY() - playerPaddle.getHeight() / 2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ball.update();
		computerPaddle.update(ball.getY());
		repaint();
		checkWin();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (status == WinStatus.NONE) {
			g.setFont(new Font("Arial", Font.BOLD, (int) (0.0625 * game.getHeight())));
			drawCenteredString(playerPaddle.getScore() + " : " + computerPaddle.getScore(), game.getWidth() / 2,
					game.getHeight() / 9, g);

			playerPaddle.paint(g);
			computerPaddle.paint(g);
			ball.paint(g);
		} else {
			drawWin(g);
			timer.stop();
		}

	}

	public void drawCenteredString(String s, int w, int y, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s) / 2);
		g.drawString(s, x, y);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(game.getWidth() / 2, fm.getHeight() + 2 * y / 3, game.getWidth() / 2, game.getHeight() - 60);
	}

	public void drawWin(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, game.getWidth() / 20));
		FontMetrics fm = g.getFontMetrics();
		String score = playerPaddle.getScore() + " : " + computerPaddle.getScore();
		String winner = "";
		String name = "Thanks for playing Nishant's Pong game";
		if (status == WinStatus.WIN) {
			winner = "YOU WIN!";
		} else if (status == WinStatus.LOSS) {
			winner = "You Lose";
		}
		int height = fm.getHeight() / 2;
		int xScore = (game.getWidth() / 2 - fm.stringWidth(score) / 2);
		int xWinner = (game.getWidth() / 2 - fm.stringWidth(winner) / 2);

		g.drawString(score, xScore, game.getHeight() / 2 - height);
		g.drawString(winner, xWinner, game.getHeight() / 2 + height);

		g.setFont(new Font("Arial", Font.PLAIN, game.getWidth() / 60));
		g.setColor(Color.LIGHT_GRAY);
		fm = g.getFontMetrics();
		int xName = (game.getWidth() / 2 - fm.stringWidth(name) / 2);
		g.drawString(name, xName, 9 * game.getHeight() / 10);

	}

}
