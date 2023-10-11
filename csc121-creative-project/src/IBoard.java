import processing.core.PApplet;
import processing.core.PVector;

 enum Intersection {
	EMPTY, WHITE, BLACK;
	
	public PApplet draw(PApplet c, int x, int y) {
		if (this != EMPTY) {
			if (this == WHITE) {
				c.fill(255);
			} else if (this == BLACK) {
				c.fill(0);
			}
			c.stroke(0);
			c.circle(x, y, GoWorld.GRID_MARGIN);
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
		pts = new Intersection[(1 + rows) * (1 + cols)];
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
	 * draws a 9x9 board
	 */
	public PApplet draw(PApplet c) {
        int borderWidth;
        int borderHeight; 
        int sideLength;
        c.background(255); 
        
        borderWidth = 30;
        borderHeight = 30; 
        sideLength = 80;
        while(borderWidth < 700) {
            c.line(borderWidth, borderHeight, borderWidth, 670); 
            borderWidth = borderWidth + sideLength; 
        }
        borderWidth = GoWorld.GRID_MARGIN;
        borderHeight = GoWorld.GRID_MARGIN; 
        sideLength = GoWorld.GRID_SIZE;
        while(borderHeight < 700) {
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
	
	
	public Intersection getColor() {
		if(this.player == 0) {
			return Intersection.BLACK; 
		}
		else if(this.player == 1) {
			return Intersection.WHITE; 
		}
		return Intersection.EMPTY;  // if player is either 1 or 0 then when we want to return empty it never will
	}
	
	public void rotatePlayer() {
		this.player = 1 - this.player; 
	}
	

	public int getIntersectionSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}

