import processing.core.PApplet;

enum Intersection {
	EMPTY, WHITE, BLACK
}

class Board {
	Intersection[] pts; // the intersection points on the board
	int rows;
	int cols;

	Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		pts = new Intersection[(1 + rows) * (1 + cols)];
		for (int i = 0; i < this.pts.length; i++) {
			this.pts[i] = Intersection.EMPTY;
		}
	}
	
	/* return the piece at the given row and col */
	public Intersection get(int row, int col) {
		return pts[(row * this.cols) + col];
	}

	public void set(int row, int col, Intersection pc) {
		pts[(row * this.cols) + col] = pc;
	}

	public PApplet draw(PApplet c) {
		
		return c;
	}

}

//class NineBoard implements IBoard{
//	int rows;
//	int columns;
//	boolean curBoard;
//
//	NineBoard(int rows, int columns , boolean curBoard){
//		this.rows = rows;
//		this.columns = columns;
//		this.curBoard = curBoard;
//	}
//
//	public boolean currentBoard() {
//		return this.curBoard = true;
//	}
//}
//
//class ThirteenBoard implements IBoard{
//	int rows;
//	int columns;
//	boolean curBoard;
//
//	ThirteenBoard(int rows, int columns , boolean curBoard){
//		this.rows = rows;
//		this.columns = columns;
//		this.curBoard = curBoard;
//	}
//
//	public boolean currentBoard() {
//		return this.curBoard = true;
//	}
//}
//
//class NineteenBoard implements IBoard{
//	int rows;
//	int columns;
//	boolean curBoard;
//
//	NineteenBoard(int rows, int columns , boolean curBoard){
//		this.rows = rows;
//		this.columns = columns;
//		this.curBoard = curBoard;
//	}
//
//	public boolean currentBoard() {
//		return this.curBoard = true;
//	}
//}
