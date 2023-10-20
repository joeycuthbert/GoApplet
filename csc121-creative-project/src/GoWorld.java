import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent; 

public class GoWorld {
	Board board;
	//Player[] player;
	// int curP = 0 / 1;
    

    
    public static int GRID_SIZE = 80;
    public static int GRID_MARGIN = 30;
    public static int STONE_SIZE = 45;

    
   
    public GoWorld(Board board) {
		this.board = board;
	//	this.player[0] = new Player( Intersection.WHITE, .board.cols..)
		//this.player = Intersection.WHITE;
	}

    /*
     * calls the draw method of the boards 
     */
	public PApplet draw(PApplet c) { 
		return board.draw(c);
    }

    /*
     * Draws a circle of a specified diameter at a given (x,y) position
     */
    public void drawCircle(PApplet c, float x, float y) {
    	int diameter = 60;
        c.circle(x, y, diameter);
    }
    
    /*
     * TODO 
     * Place a circle of correct color on the nearest intersection to the (x,y) location of the mouse click
     * Should handle the board actions when a the mouse is pressed 
     */
    
    /*
     * given the y-position of a supposed mouse click, return which row the stone should lie on
     * 
     */
    public static int logicalRow(int yPos) {
    	return (yPos - GRID_MARGIN + (GRID_SIZE/2)) / GRID_SIZE;
    	/*
    	int rowSpace = 30;
    	int rows = 9; 
    	int row = -1; // talk to Dr. Hamid about this 
    	for(int i = 0; i <= rows; i++) {
    		if(((i * rowSpace + 15) > yPos) && ((i * rowSpace) < yPos)) {
    			row = i;  
    			break;
    		}
    	}
    	return row;  
    	*/
    }
    
    public static int logicalCol(int xPos) {
    	return (xPos - 30 /* align to 0 */ + 40 /* center on the grid line */) / 80;
    	/*int colSpace = 30;
    	int cols = 9; 
    	int col = -1; // talk to Dr. Hamid about this 
    	for(int i = 0; i <= cols; i++) {
    		if(((i * colSpace + 15) > xPos) && ((i * colSpace) < xPos)) {
    			col = i;  
    			break;
    		}
    	}
    	return col; 
    	*/ 
    }
    
    public static int physicalX(int col) {
    	return (col * GRID_SIZE) + GRID_MARGIN;
    }
    
    public GoWorld mousePressed(MouseEvent mev) {
    	int logCol = this.logicalCol(mev.getX()); 
        int logRow = this.logicalRow(mev.getY());
        
        
        if (this.board.get(logRow, logCol) == Intersection.EMPTY) {
        	board.set(logRow, logCol, this.board.getColor());  
      
        	this.board.rotatePlayer();  // TODO: get rid of this
        }
        
        // int n = board.detectCaptures(player[curP]);  // detect number of captures by the current player
        // score += n;
        // curP = 1 - curP;   // flips between 0 and 1
    	
        return this;
    }


}