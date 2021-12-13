import java.awt.Color;

public class Ball extends Sprite {
	private final static int WIDTH = 25;
	private final static int HEIGHT = 25;
	private final static Color COLOR = Color.WHITE;
	
	public Ball(int panelWidth, int panelHeight) {
		setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setColor(COLOR);
		resetToInitialPosition();
	}
}
