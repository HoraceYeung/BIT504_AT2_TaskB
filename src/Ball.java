import java.awt.Color;

public class Ball extends Sprite {
	private final static int WIDTH = 25;
	private final static int HEIGHT = 25;
	private final static Color COLOR = Color.RED;
	
	public Ball(int panelWidth, int panelHeight) {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setColor(COLOR);
		setInitialPosition((panelWidth - getWidth())/2, (panelHeight - getHeight())/2);
		resetToInitialPosition();
	}
}
