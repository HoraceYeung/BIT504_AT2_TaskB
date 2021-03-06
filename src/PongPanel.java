import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.BasicStroke;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;

public class PongPanel extends JPanel implements ActionListener, KeyListener {
	private final static Color BACKGROUND_COLOUR = Color.BLACK;
	private final static int TIMER_DELAY = 5;
	private static int BALL_MOVEMENT_SPEED = 3;
	private static int PADDLE_MOVEMENT_SPEED = 5;
	private GameState gameState = GameState.Initialising;
	private Ball ball;
	private Paddle paddleOne;
	private Paddle paddleTwo;
	private final static int POINTS_TO_WIN = 3;
	private int player1Score = 0, player2Score = 0;
	private Player gameWinner;

	public PongPanel() {
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();

		addKeyListener(this);
		setFocusable(true);
	}

	private void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddleOne = new Paddle(Player.One, getWidth(), getHeight());
		paddleTwo = new Paddle(Player.Two, getWidth(), getHeight());
	}

	private void moveObject(Sprite sprite) {
		sprite.setXPosition(sprite.getXPosition() + sprite.getXVelocity(), getWidth());
		sprite.setYPosition(sprite.getYPosition() + sprite.getYVelocity(), getHeight());
	}

	private void resetBall() {
		ball.resetToInitialPosition();
	}

	private void addScore(Player player) {
		if (player == Player.One) {
			player1Score += 1;
		} else if (player == Player.Two) {
			player2Score += 1;
		}
	}

	private void checkWin() {
		if (player1Score >= POINTS_TO_WIN) {
			gameWinner = Player.One;
			gameState = GameState.GameOver;
		} else if (player2Score >= POINTS_TO_WIN) {
			gameWinner = Player.Two;
			gameState = GameState.GameOver;
		}
	}

	private void checkWallBounce() {
		if (ball.getYPosition() <= 0 || (ball.getYPosition() + ball.getHeight()) >= getHeight()) {
			ball.setYVelocity(-ball.getYVelocity());
		}

		if (ball.getXPosition() <= 0 || (ball.getXPosition() + ball.getWidth()) >= getWidth()) {
			if (ball.getXPosition() <= 0) {
				addScore(Player.Two);
			} else {
				addScore(Player.One);
			}
			resetBall();
		}
	}

	private void checkPaddleBounce() {
		if (ball.getXVelocity() < 0 && ball.getRectangle().intersects(paddleOne.getRectangle())) {
			ball.setXVelocity(BALL_MOVEMENT_SPEED);
		}

		if (ball.getXVelocity() > 0 && ball.getRectangle().intersects(paddleTwo.getRectangle())) {
			ball.setXVelocity(-BALL_MOVEMENT_SPEED);
		}
	}

	private void update() {
		switch (gameState) {
		case Initialising: {
			createObjects();
			ball.setXVelocity(1 * BALL_MOVEMENT_SPEED);
			ball.setYVelocity(1 * BALL_MOVEMENT_SPEED);
			gameState = GameState.Playing;
			break;
		}
		case Playing: {
			moveObject(paddleOne);
			moveObject(paddleTwo);
			moveObject(ball);
			checkWallBounce();
			checkPaddleBounce();
			checkWin();
			break;
		}
		case GameOver: {
			break;
		}
		}
	}

	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColor());
		g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
	}

	private void paintScores(Graphics g) {
		int xPadding = 100;
		int yPadding = 100;
		int fontSize = 50;
		Font scoreFont = new Font("Serif", Font.BOLD, fontSize);
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, xPadding, yPadding);
		g.drawString(rightScore, getWidth() - xPadding, yPadding);
		
		if (gameWinner == Player.One) {
			g.drawString("Win!", xPadding, yPadding * 2);
		} else if (gameWinner == Player.Two) {
			g.drawString("Win!", getWidth() - (xPadding * 2), yPadding * 2);
		} 
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if (gameState != GameState.Initialising) {
			paintSprite(g, ball);
			paintSprite(g, paddleOne);
			paintSprite(g, paddleTwo);
			paintScores(g);
		}
	}

	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.WHITE);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			paddleTwo.setYVelocity(-PADDLE_MOVEMENT_SPEED);
		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddleTwo.setYVelocity(PADDLE_MOVEMENT_SPEED);
		} else if (event.getKeyCode() == KeyEvent.VK_W) {
			paddleOne.setYVelocity(-PADDLE_MOVEMENT_SPEED);
		} else if (event.getKeyCode() == KeyEvent.VK_S) {
			paddleOne.setYVelocity(PADDLE_MOVEMENT_SPEED);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddleTwo.setYVelocity(0);
		} else if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
			paddleOne.setYVelocity(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
	}
}