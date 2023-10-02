import processing.core.PApplet;

class WhiteStone {
	int row;
	int col;

	public WhiteStone(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public PApplet draw(PApplet c) {
		c.fill(255);
		c.circle(col * 60, row * 60, 60);
		return c;
	}

}

class BlackStone{
	int row;
	int col;

	public BlackStone(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public PApplet draw(PApplet c) {
		c.fill(0);
		c.circle(col * 60, row * 60, 60);
		return c;
	}

}


