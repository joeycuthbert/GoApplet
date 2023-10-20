import processing.core.PApplet;
import processing.core.PVector;

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

class Board {
	Intersection[] pts; // the intersection points on the board
	int rows;
	int cols;
	int player; 

	/*
	 * constructor for the Board class. Creates a 1d array of length(number of intersections)  and all intersections as empty
	 */
	Board(int rows, int cols, int player) {
		this.rows = rows;
		this.cols = cols;
		this.player = player; 
		pts = new Intersection[(rows) * (cols)];
		for (int i = 0; i < this.pts.length; i++) {
			this.pts[i] = Intersection.EMPTY;
		}
	}

	/* return the piece at the given row and col */
	public Intersection get(int row, int col) {
		return pts[(row * this.cols) + col];
	}

	/*
	 *  places a piece at row, column in the points list 
	 */

	public void set(int row, int col, Intersection pc) {
		pts[(row * this.cols) + col] = pc; 
	}

	/*
	 * given a row and col, return the positions respective location in the pts[] array
	 */
	public int getLoc(int row, int col) {
		return (row * this.cols) + col; 
	}

	/*
	 * draws a 9x9 board
	 */
	public PApplet draw(PApplet c) {
<<<<<<< HEAD
        int borderWidth;
        int borderHeight; 
        int sideLength;
        
        c.background(220, 196, 149); 
        
        borderWidth = 30;
        borderHeight = 30; 
        sideLength = 80;
        while(borderWidth < 700) {
        	c.stroke(0);
            c.line(borderWidth, borderHeight, borderWidth, 670); 
            borderWidth = borderWidth + sideLength; 
        }
        borderWidth = GoWorld.GRID_MARGIN;
        borderHeight = GoWorld.GRID_MARGIN; 
        sideLength = GoWorld.GRID_SIZE;
        while(borderHeight < 700) {
        	c.stroke(0);
            c.line(borderWidth, borderHeight, 670, borderHeight); 
            borderHeight = borderHeight + sideLength; 
        }
        
        for (int row = 0; row <= this.rows -1; row++) {
        	for (int col = 0; col <= this.cols -1; col++) {
        		int x = GoWorld.physicalX(col);
        		int y = GoWorld.physicalX(row);
        		get(row, col).draw(c, x, y);
        	}
        }
        
        return c;
    }
	
	
=======
		int borderWidth;
		int borderHeight; 
		int sideLength;

		c.background(220, 196, 149); 

		borderWidth = 30;
		borderHeight = 30; 
		sideLength = 80;
		while(borderWidth < 700) {
			c.stroke(0);
			c.line(borderWidth, borderHeight, borderWidth, 670); 
			borderWidth = borderWidth + sideLength; 
		}

		borderWidth = GoWorld.GRID_MARGIN;
		borderHeight = GoWorld.GRID_MARGIN; 
		sideLength = GoWorld.GRID_SIZE;
		while(borderHeight < 700) {
			c.stroke(0);
			c.line(borderWidth, borderHeight, 670, borderHeight); 
			borderHeight = borderHeight + sideLength; 
		}

		for (int row = 0; row <= 8; row++) {
			for (int col = 0; col <= 8; col++) {
				int x = GoWorld.physicalX(col);
				int y = GoWorld.physicalX(row);
				get(row, col).draw(c, x, y);
			}
		}

		return c;
	}

>>>>>>> d16831037d358e9d9315f4f2f991389160e15b4b
	public Intersection getColor() {
		if(this.player == 0) {
			return Intersection.BLACK; 
		}

		else if(this.player == 1) {
			return Intersection.WHITE; 
		}

		return Intersection.EMPTY;  // if player is either 1 or 0 then when we want to return empty it never will
	}

	/*
	 * Switches between player 1 and 2
	 */
	public void rotatePlayer() {
		this.player = 1 - this.player; 
	}

	/*
	 * given a location for a piece in the pts[] array, a vertical and horizontal direction to check
	 * and a piece color, determine if the piece is touching a piece of opposite color in the given direction
	 * WHEN USNG THIS METHOD:
	 *			vDir and hDir should be either -1, 0, or 1
	 *			if vDir != 0, then hDir should equal 0 and vice versa
	 */
	public boolean checkSurrInDir(int loc, int vDir, int hDir, Intersection color) {
		int listPos = loc + (vDir * this.cols) + hDir; 

		if (this.offBoard(loc, hDir, vDir)){
			return true; 
		}
		if( pts[listPos] == color ) {
			return checkSurrInDir(listPos, vDir, hDir, color);
		}
		else if( pts[listPos] == Intersection.EMPTY) {
			return false;
		}
		else {

			return true;
		}
	}

	/*
	 * HELPER METHOD FOR checkSurrInDir
	 * Because we represent our board as a 1-d array of points, it is hard to 
	 * represent when something is off the board to the right or left.
	 * This helper method determines if a vertical horizontal move to the left or right or up or down is off the board
	 */
	public boolean offBoard(int loc, int hDir, int vDir) {
		if( ((loc + hDir) < 0) || ((loc + hDir) > this.pts.length - 1) ) {
			return true;
		}
		else if( ((loc + (vDir * this.cols)) < 0) || ((loc + (vDir * this.cols)) > this.pts.length - 1) ) {
			return true;
		}
		else if( (hDir < 0) && (loc % this.cols == 0) ) { 

			// case where loc is in first column and trying to move horizontally to the left
			return true;
		}
<<<<<<< HEAD
		else if( (hDir > 0) && (loc % this.cols == this.cols -1) ) { 
=======
		else if( (hDir > 0) && (loc % this.cols == 8) ) { 

>>>>>>> d16831037d358e9d9315f4f2f991389160e15b4b
			// case where loc is in the last column and trying to move horizontally to the right
			return true;
		}

		return false;
	}

	public boolean checkSurr(int loc, Intersection color) {
		return 
				this.checkSurrInDir(loc, 1, 0, color) && 
				this.checkSurrInDir(loc, -1, 0, color) &&
				this.checkSurrInDir(loc, 0, 1, color) &&
				this.checkSurrInDir(loc, 0, -1, color); 


	}

}

