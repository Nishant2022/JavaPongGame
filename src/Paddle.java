import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This is the paddle object for the pong game.
 * 
 * @author Nishant Dash
 * @version 1.0
 * @since 2020-16-13
 */

public class Paddle {
	private int score = 0;
	private int x, y;
	private int PADDLE_WIDTH = 10, PADDLE_HEIGHT = 60;
	private Ball ball;
	private Pong game;
	private Paddle playerPaddle;
	private boolean computer = false;
	private int paddleSpeed;
	private int randomPaddleSpeed;

	public Paddle(Pong game) {
		this.game = game;
		y = game.getHeight() / 2;
		x = 10;
		PADDLE_WIDTH = game.getHeight() / 40;
		PADDLE_HEIGHT = game.getHeight() / (40 / 6);
	}

	public Paddle(Pong game, Paddle playerPaddle, boolean computer) {
		this.game = game;
		this.playerPaddle = playerPaddle;
		this.computer = computer;
		paddleSpeed = 3;
		randomPaddleSpeed = paddleSpeed;
		PADDLE_WIDTH = game.getHeight() / 40;
		PADDLE_HEIGHT = game.getHeight() / (40 / 6);
		y = game.getHeight() / 2;
		x = game.getWidth() - 2 * PADDLE_WIDTH - 10;
	}

	public void addBall(Ball ball) {
		this.ball = ball;
	}

	public void setY(int i) {
		y = i;
		resize();
	}

	public void update(int y) {
		resize();

		int tempY = y - PADDLE_HEIGHT / 2;
		if (ball.getX() >= game.getWidth() / 4) {
			if (this.y > tempY) {
				this.y -= randomPaddleSpeed;
			} else {
				this.y += randomPaddleSpeed;
			}
		}
	}

	public void resize() {
		PADDLE_WIDTH = game.getHeight() / 40;
		PADDLE_HEIGHT = game.getHeight() / (40 / 6);
		if (computer) {
			x = game.getWidth() - 2 * PADDLE_WIDTH - 10;
		} else {
			x = 10;
		}
	}

	public void addScore() {
		score++;
		if (computer) {
			paddleSpeed = playerPaddle.getScore() - getScore() + 3;
			if (paddleSpeed < 3) {
				paddleSpeed = 3;
			}
			randomPaddleSpeed = (int) (3 * Math.random() - 1) + paddleSpeed;
		}
	}

	public Rectangle[] getBounds() {
		Rectangle[] bounds = new Rectangle[2];
		bounds[0] = new Rectangle(x, y, PADDLE_WIDTH, PADDLE_HEIGHT / 2);
		bounds[1] = new Rectangle(x, y + PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT / 2);
		return bounds;
	}

	public int getScore() {
		return score;
	}

	public int getHeight() {
		return PADDLE_HEIGHT;
	}

	public int getY() {
		return y;
	}

	public void paint(Graphics g) {
		g.setColor(new Color(29, 53, 87));
		g.fillRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
	}

}
