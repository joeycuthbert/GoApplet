import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent; 

public class GoWorld {
	Board board;
	//Player[] player;
	// int curP = 0 / 1;
    List<PVector> circles = new ArrayList<>();
    

   
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
    
    public GoWorld mousePressed(MouseEvent mev) {
    
        // translate the "physical" x,y of mev to "logical" grid locations row/col 
        
    	// board.set( row, col,  player[curP].getColor() )
       //  curP = 1 - curP;  // swaps between 0 and 1
    	
       // PVector circlePos = new PVector(mev.getX(), mev.getY());
        // circles.add(circlePos);
    	
        return this;
    }


}