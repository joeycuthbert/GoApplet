import processing.core.PApplet;

public interface IStone {

}

class WhiteStone implements IStone{
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

class BlackStone implements IStone{
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

/*Represents a player in a game of GO*/
class Player1 {
	IStone white;
	boolean cur;
	int capStones;

	public Player1(IStone white, boolean cur, int capStones) {
		this.white = white;
		this.cur = cur;
		this.capStones = capStones;
	}

	public boolean curPlayer() {
		return this.cur = true;
	}

	public int getCapturedStones() {
		return capStones;
	}
	
	public void incrementCapturedStones(int count) {
		capStones += count;
	}
}

/*Represents a player in a game of GO*/
class Player2 {
	IStone black;
	boolean cur;
	int capStones;

	public Player2(IStone black, boolean cur, int capStones) {
		this.black = black;
		this.cur = cur;
		this.capStones = capStones;
	}

	public boolean curPlayer() {
		return this.cur = true;
	}

	public int getCapturedStones() {
		return capStones;
	}

	public void incrementCapturedStones(int count) {
		capStones += count;
	}
}
