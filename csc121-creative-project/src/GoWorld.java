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
		board.draw(c);
		
        int width = 30;
        int height = 30; 
        c.background(255); 
        while(width < 700) {
            c.line(width, 30, width, 670); 
            width = width + 80; 
        }
        while(height < 700) {
            c.line(30, height, 670, height); 
            height = height + 80; 
        }
        
        for (PVector circlePos : circles) {
            drawCircle(c, circlePos.x, circlePos.y);
        }
        
        return c;
    }

    
    public void drawCircle(PApplet c, float x, float y) {
        c.circle(x, y, 60);
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