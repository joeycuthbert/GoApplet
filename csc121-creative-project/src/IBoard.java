import processing.core.PApplet;
import processing.core.PVector;

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
        borderWidth = 30;
        borderHeight = 30; 
        sideLength = 80;
        while(borderHeight < 700) {
            c.line(borderWidth, borderHeight, 670, borderHeight); 
            borderHeight = borderHeight + sideLength; 
        }
        
        
        
        return c;
    }


}

