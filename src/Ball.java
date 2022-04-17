import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This is the ball object for the pong game.
 * 
 * @author Nishant Dash
 * @version 1.0
 * @since 2020-16-13
 */

public class Ball {
	private int x, y;
	private int xDirection, yDirection;
	private int radius;
	private Pong game;
	private Paddle player, computer;
	private int hits;

	public Ball(Pong game, Paddle player, Paddle computer) {
		this.game = game;
		this.player = player;
		this.computer = computer;
		resetBall();
	}

	public void resetBall() {
		hits = 0;
		x = game.getWidth() / 2 - radius;
		y = game.getHeight() / 2 - radius;
		xDirection = (int) (2 * (game.getHeight() / 100) * Math.random() - (game.getHeight() / 100));
		if (xDirection == 0)
			xDirection++;
		if (2 * Math.random() > 1) {
			yDirection = (int) ((game.getHeight() / 80) - Math.abs(xDirection) * -1);
		} else {
			yDirection = (int) ((game.getHeight() / 80) - Math.abs(xDirection));
		}
		yDirection = (int) (1);
		if (xDirection == 0)
			xDirection++;
		if (yDirection == 0)
			yDirection++;
	}

	public void update() {
		radius = game.getHeight() / 16;
		Rectangle[] playerPaddleBounds = player.getBounds();
		Rectangle[] computerPaddleBounds = computer.getBounds();
		if (y < 0 || y > game.getHeight() - getBounds().height - 35) {
			yDirection = -yDirection;
		}
		if (x < 0) {
			computer.addScore();
			resetBall();
		}
		if (x > game.getWidth()) {
			player.addScore();
			resetBall();
		}
		if (playerPaddleBounds[0].intersects(getBounds()) || computerPaddleBounds[0].intersects(getBounds())) {
			hits++;
			if (yDirection >= 0) {
				yDirection = -yDirection + (int) (3 * Math.random() - 1);
				xDirection = -xDirection;
			} else {
				yDirection = yDirection + (int) (3 * Math.random() - 1);
				xDirection = -xDirection;
			}
			System.out.println(hits);
			increaseSpeed();
		} else if (playerPaddleBounds[1].intersects(getBounds()) || computerPaddleBounds[1].intersects(getBounds())) {
			hits++;
			if (yDirection >= 0) {
				yDirection = yDirection + (int) (3 * Math.random() - 1);
				xDirection = -xDirection;
			} else {
				yDirection = -yDirection + (int) (3 * Math.random() - 1);
				xDirection = -xDirection;
			}
			System.out.println(hits);
			increaseSpeed();
		}
		if (xDirection == 0) {
			xDirection = (int) (3 * Math.random() - 1);
		}
		if (yDirection == 0) {
			yDirection = (int) (3 * Math.random() - 1);
		}

		x += xDirection;
		y += yDirection;
	}

	public void increaseSpeed() {
		if (hits % 5 == 0) {
			xDirection = (int) (Math.round(Math.pow(1.05, hits - 5) * xDirection));
			yDirection = (int) (Math.round(Math.pow(1.05, hits - 5) * yDirection));
		}
	}

	public Rectangle getBounds() {
		Rectangle bound = new Rectangle(x, y, radius, radius);
		return bound;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void paint(Graphics g) {
		g.setColor(new Color(230, 57, 70));
		g.fillOval(x, y, radius, radius);
	}
}
