import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent; 

public class GoWorld {
	private Board board;

	public static int GRID_SIZE = 80;
	public static int GRID_MARGIN = 30;
	public static int STONE_SIZE = 45;

	public GoWorld(Board board) {
		this.board = board;
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
	 * given the y-position of a supposed mouse click, return which row the stone should lie on
	 */
	public static int logicalRow(int yPos) {
		return (yPos - GRID_MARGIN + (GRID_SIZE/2)) / GRID_SIZE;
	}

	public static int logicalCol(int xPos) {
		return (xPos - 30 /* align to 0 */ + 40 /* center on the grid line */) / 80;
	}

	public static int physicalX(int col) {
		return (col * GRID_SIZE) + GRID_MARGIN;
	}
	
	public void saveTiles() {
	    try {
	        String filename = javax.swing.JOptionPane.showInputDialog("Please enter file name:");
	        filename = filename.trim();
	        if (filename.equals("")) {
	            javax.swing.JOptionPane.showMessageDialog(null, "Cannot save to a blank name");
	            return;
	        }
	        if (! filename.endsWith(".txt")) {
	            filename = filename + ".txt";
	        }
	        
    	    PrintWriter pw = new PrintWriter(new File(filename));
    	    
    	    for (Intersection i : this.board.getPts()) {    // for-each
    	        i.writeToFile(pw);
    	    }
    	    
    	    pw.close();
	    } catch (IOException exp) {
	        System.out.println("Problem saving tiles: " + exp.getMessage());
	    }
	}

	public GoWorld mousePressed(MouseEvent mev) {
		int logCol = logicalCol(mev.getX()); 
		int logRow = logicalRow(mev.getY());

		if (this.board.get(logRow, logCol) == Intersection.EMPTY) {
			board.set(logRow, logCol, this.board.getColor());  
			
			boolean[] deleteArr = this.board.checkAllSurr(this.board.getOppColor()); 
			
			for(int i = 0; i < this.board.getPts().length; i++) {
				if(deleteArr[i]) {
					this.board.getPts()[i] = Intersection.EMPTY; 
				}
			}

			this.board.rotatePlayer();  // TODO: get rid of this
		}
		
		return this;
	}
	
	public void keyPressed(KeyEvent kev) {
		switch (Character.toLowerCase(kev.getKey())) {
			case 's': this.saveTiles(); break;
			case 'o': this.loadTiles(); break;
		}
	}
}