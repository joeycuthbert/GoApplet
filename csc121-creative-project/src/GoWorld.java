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


	public PApplet draw(PApplet c) {
		return board.draw(c);
    }

    
    public void drawCircle(PApplet c, float x, float y) {
    	int diameter = 60;
        c.circle(x, y, diameter);
    }
    
    
    public GoWorld mousePressed(MouseEvent mev) {
    
        // translate the "physical" x,y of mev to "logical" grid locations row/col 
        
    	// board.set( row, col,  player[curP].getColor() )
    	//  curP = 1 - curP;  // swaps between 0 and 1
    	
        //PVector circlePos = new PVector(mev.getX(), mev.getY());
        //circles.add(circlePos);
        return this;
    }


}