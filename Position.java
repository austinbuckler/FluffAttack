

/**
 * Represents a position inside the game/applet.
 * @author 216517
 *
 */
public class Position {

	private final int x;
	private final int y;
	
	/**
	 * Creates a new position.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
}
