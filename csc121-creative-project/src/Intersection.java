import processing.core.PApplet;

enum Intersection {
	EMPTY, WHITE, BLACK;

	public PApplet draw(PApplet c, int x, int y) {
		if (this != EMPTY) {
			if (this == WHITE) {
				c.stroke(225);
				c.fill(255);

			} else if (this == BLACK) {
				c.stroke(0);
				c.fill(0);
			}

			c.circle(x, y, GoWorld.STONE_SIZE );
		}

		return c;
	}

}