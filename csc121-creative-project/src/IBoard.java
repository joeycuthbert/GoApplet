import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

import processing.core.PApplet;


class Board {
	private Intersection[] pts; // the intersection points on the board
	private int rows;
	private int cols;
	private int player; 
	private int blackScore; 
	private int whiteScore;

	
	public Board(Scanner sc) {
        this.rows = sc.nextInt();
        this.cols = sc.nextInt();
        this.player = sc.nextInt();
        
        setPts(new Intersection[(this.rows) * (this.cols)]);  
        for (int i = 0; i < this.getPts().length; i++) {
			this.getPts()[i] = Intersection.valueOf(sc.next());
		}   
    }
	/*
	 * constructor for the Board class. Creates a 1d array of length(number of intersections)  and all intersections as empty
	 */
	Board(int rows, int cols, int player) {
		this.rows = rows;
		this.cols = cols;
		this.player = player; 
		setPts(new Intersection[(rows) * (cols)]);
		for (int i = 0; i < this.getPts().length; i++) {
			this.getPts()[i] = Intersection.EMPTY;
		}
		
		this.blackScore = 0;
		this.whiteScore = 0; 
	}

	/* return the piece at the given row and col */
	public Intersection get(int row, int col) {
		return getPts()[(row * this.cols) + col];
	}

	/*
	 *  places a piece at row, column in the points list 
	 */

	public void set(int row, int col, Intersection pc) {
		getPts()[(row * this.cols) + col] = pc; 
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
	
	
	public Intersection getColor() {
		if(this.player == 0) {
			return Intersection.BLACK; 
		}

		else if(this.player == 1) {
			return Intersection.WHITE; 
		}

		return Intersection.EMPTY;  // if player is either 1 or 0 then when we want to return empty it never will
	}
	
	public Intersection getOppColor() {
		if(this.getColor() == Intersection.BLACK) {
			return Intersection.WHITE; 
		}
		else if(this.getColor() == Intersection.WHITE) {
			return Intersection.BLACK; 
		}
		
		return Intersection.EMPTY; 
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
	 *
	 * original is true if it is the top-level, very first call from checkSurr() itself
	 */
	public boolean checkSurrInDir(int loc, int vDir, int hDir, Intersection color, boolean[] checked) {
		int listPos = loc + (vDir * this.cols) + hDir; 

		if (this.offBoard(loc, hDir, vDir)){
			return true; 
		}
		if( getPts()[listPos] == color ) {
			if (checked[listPos]) {
				return checkSurrInDir(listPos, vDir, hDir, color, checked);
			} else {
				return checkSurr(listPos, color, checked) && 
						checkSurrInDir(listPos, vDir, hDir, color, checked);
			}
		}
		else if( getPts()[listPos] == Intersection.EMPTY) {
			return false;
		}
		else {

			return true;
		}
	}

	/* color is the "my" color, so checking if surrounding by the other color, or edges of the board */
	public boolean checkSurr(int loc, Intersection color) {
		return checkSurr(loc, color, new boolean[this.cols * this.rows]); /* java will fill the booleans all in with false */
	}
	
	/* color is the "my" color, so checking if surrounding by the other color, or edges of the board 
	 *  checked will be a list of flags of `loc`s that have already had `checkSurr` called on them.
	 * */
	public boolean checkSurr(int loc, Intersection color, boolean[] checked) {
		checked[loc] = true;
		return 
				this.checkSurrInDir(loc, 1, 0, color, checked) && 
				this.checkSurrInDir(loc, -1, 0, color, checked) &&
				this.checkSurrInDir(loc, 0, 1, color, checked) &&
				this.checkSurrInDir(loc, 0, -1, color, checked); 

	}

	/*
	 * HELPER METHOD FOR checkSurrInDir
	 * Because we represent our board as a 1-d array of points, it is hard to 
	 * represent when something is off the board to the right or left.
	 * This helper method determines if a vertical horizontal move to the left or right or up or down is off the board
	 */
	public boolean offBoard(int loc, int hDir, int vDir) {
		if( ((loc + hDir) < 0) || ((loc + hDir) > this.getPts().length - 1) ) {
			return true;
		}
		else if( ((loc + (vDir * this.cols)) < 0) || ((loc + (vDir * this.cols)) > this.getPts().length - 1) ) {
			return true;
		}
		else if( (hDir < 0) && (loc % this.cols == 0) ) { 

			// case where loc is in first column and trying to move horizontally to the left
			return true;
		}
		else if( (hDir > 0) && (loc % this.cols == this.cols -1) ) { 
			// case where loc is in the last column and trying to move horizontally to the right
			return true;
		}

		return false;
	}

	
	
	
	
	public boolean[] checkAllSurr(Intersection color){ // need to write test cases and evaluate helper methods.
		boolean[] s = new boolean[this.getPts().length]; 
		for(int i = 0; i < this.getPts().length; i++) {
			if(checkSurr(i, color) && this.getPts()[i] == color) {
				s[i] = true; 
			}
		}
		
		return s; 
	}
	
	public void clearArray() {
		Arrays.fill(this.pts, null);
	}
	
	public Intersection[] getPts() {
		return pts;
	}

	public void setPts(Intersection[] pts) {
		this.pts = pts;
	}


	void writeToFile(PrintWriter pw) {
		pw.println(this.rows + "\n" + this.cols + "\n" + this.player);
		for (Intersection pt : this.pts) {
			pw.println(pt.name());
		}
	}

}

