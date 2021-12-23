import java.awt.Color;

public class Paddle extends Sprite {
	private final static int WIDTH = 10;
	private final static int HEIGHT = 100;
	private final static Color COLOR = Color.WHITE;
	private final static int DISTANCE_FROM_EDGE = 40;
	
	public Paddle(Player player, int PanelWidth, int PanelHeight) {
		int initialXPosition;
		int initialYPosition = PanelHeight + HEIGHT/2;
		
		if (player == Player.One) {
			initialXPosition = DISTANCE_FROM_EDGE;
		} else {
			initialXPosition = PanelWidth - DISTANCE_FROM_EDGE;
		}
		
		setInitialPosition(initialXPosition, initialYPosition);
		resetToInitialPosition();
	}
}
