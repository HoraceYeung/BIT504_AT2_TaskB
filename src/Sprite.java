import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
	private int initialXPosition;
	private int initialYPosition;
	private int xPosition;
	private int yPosition;
	private int xVelocity;
	private int yVelocity;
	private int width;
	private int height;
	private Color color;

	public void setInitialPosition(int initialXPosition, int initialYPosition) {
		this.initialXPosition = initialXPosition;
		this.initialYPosition = initialYPosition;
	}

	public void resetToInitialPosition() {
		xPosition = initialXPosition;
		yPosition = initialYPosition;
	}

	public int getXPosition() {
		return xPosition;
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setXPosition(int xPosition, int panelWidth) {
		this.xPosition = xPosition;
		if (xPosition < 0) {
			this.xPosition = 0;
		} else if (xPosition > panelWidth) {
			this.xPosition = panelWidth;
		} else {
			this.xPosition = xPosition;
		}
	}

	public int getYPosition() {
		return yPosition;
	}

	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public void setYPosition(int yPosition, int panelHeight) {
		if (yPosition < 0) {
			this.yPosition = 0;
		} else if (yPosition > panelHeight) {
			this.yPosition = panelHeight;
		} else {
			this.yPosition = yPosition;
		}
	}

	public int getXVelocity() {
		return xVelocity;
	}

	public void setXVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}

	public int getYVelocity() {
		return yVelocity;
	}

	public void setYVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Rectangle getRectangle() {
		return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
	}
}
