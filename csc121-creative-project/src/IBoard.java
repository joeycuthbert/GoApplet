
public interface IBoard {

}

class NineBoard implements IBoard{
	int points = 9;
	IStone stone;
	boolean curBoard;

	NineBoard(int points, IStone stone, boolean curBoard){
		this.points = points;
		this.stone = stone;
		this.curBoard = curBoard;
	}
	
	public boolean currentBoard() {
		return this.curBoard = true;
	}
}

class ThirteenBoard implements IBoard{
	int points = 13;
	IStone stone;
	boolean curBoard;

	ThirteenBoard(int points, IStone stone, boolean curBoard){
		this.points = points;
		this.stone = stone;
		this.curBoard = curBoard;
	}
	
	public boolean currentBoard() {
		return this.curBoard = true;
	}
}

class NineteenBoard implements IBoard{
	int points = 19;
	IStone stone;
	boolean curBoard;

	NineteenBoard(int points, IStone stone, boolean curBoard){
		this.points = points;
		this.stone = stone;
		this.curBoard = curBoard;
	}
	
	public boolean currentBoard() {
		return this.curBoard = true;
	}
}
