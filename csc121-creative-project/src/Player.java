
/*Represents a player in a game of GO*/
class Player1 {
	Intersection color;
	boolean cur;
	int capStones;

	public Player1(Intersection color, boolean cur, int capStones) {
		this.color = color;
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
	Intersection color;
	boolean cur;
	int capStones;

	public Player2(Intersection color, boolean cur, int capStones) {
		this.color = color;
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