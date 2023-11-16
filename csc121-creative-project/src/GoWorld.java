import java.io.File;

import java.io.*;  
import java.io.PrintWriter;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent; 
import java.util.Scanner;
import java.util.Stack;
public class GoWorld {
	private Board board;
	private int prevX;  	// preview locations for the next tile
	private int prevY;
	private Stack<Integer> undoStack;
	private Stack<Integer> redoStack;
	private Stack<Intersection> redoColStack;
	private Stack<Intersection> undoColStack; 

	public static int GRID_SIZE = 80;
	public static int GRID_MARGIN = 30;
	public static int STONE_SIZE = 45;

	public GoWorld(Board board) {
		this.board = board;
		this.prevX = -100;
		this.prevY = -100;
		this.undoStack = new Stack<Integer>();
		this.undoColStack = new Stack<Intersection>(); 
		this.redoStack = new Stack<Integer>();
		this.redoColStack = new Stack<Intersection>();
		

	}

	/*
	 * calls the draw method of the boards 
	 */
	public PApplet draw(PApplet c) { 
		board.draw(c);
		this.board.getColor().draw(c, prevX, prevY);
		return c;
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
		System.out.println("Saving");
		try {
			String filename = javax.swing.JOptionPane.showInputDialog("Save as name");
			PrintWriter pw = new PrintWriter(new File(filename + ".txt"));
			this.board.writeToFile(pw);
			pw.close();
		} catch(IOException exp) {
			System.out.println("Problem saving tiles: " + exp.getMessage());
		}
	}


	
	public void loadTiles() {
		try {
			String filename = javax.swing.JOptionPane.showInputDialog("Save as name");
			Scanner sc = new Scanner(new File(filename + ".txt"));
			this.board = new Board(sc);
			sc.close();
		}catch(IOException exp) {
			System.out.println("Problem loading stones: " + exp.getMessage());
		}
	}
	
	public void undoMove() {
		if (!undoStack.isEmpty()) {
            int lastMove = this.undoStack.pop();
            Intersection lastColor = this.undoColStack.pop(); 
            
            this.redoStack.push(lastMove);
            this.redoColStack.push(lastColor);
            this.board.getPts()[lastMove] = Intersection.EMPTY; 
		}
	}
	
	public void redoMove() {
		if (!redoStack.isEmpty()) {
            int nextMove = this.redoStack.pop();
            Intersection nextColor = this.redoColStack.pop(); 
            
            this.undoStack.push(nextMove);
            this.undoColStack.push(nextColor);

            this.board.getPts()[nextMove] = nextColor; 
        }
	}
	 

	public GoWorld mousePressed(MouseEvent mev) {
		int logCol = logicalCol(mev.getX()); 
		int logRow = logicalRow(mev.getY());

		if (this.board.get(logRow, logCol) == Intersection.EMPTY) {
			board.set(logRow, logCol, this.board.getColor());
			
			
			if (this.undoStack != null) {
	            // Now you can safely push an element onto the undoStack
				this.undoStack.push(board.getLoc(logRow, logCol));
				this.undoColStack.push(this.board.getColor());
	        } else {
	            // Handle the case where undoStack is not properly initialized
	            System.out.println("Undo stack is not initialized!");
	        }
			
			
 
			if (this.board.checkSurr(board.getLoc(logRow, logCol), this.board.getColor())) {
				board.set(logRow, logCol, Intersection.EMPTY);
			} else {
	
				boolean[] deleteArr = this.board.checkAllSurr(this.board.getOppColor()); 
	
				for(int i = 0; i < this.board.getPts().length; i++) {
					if(deleteArr[i]) {
						this.board.getPts()[i] = Intersection.EMPTY; 
					}
				}
	
				this.board.rotatePlayer(); 
			}
		}

		return this;
	}

	public void keyPressed(KeyEvent kev) {
		if (Character.toLowerCase(kev.getKey()) == 's') {
			this.saveTiles();
		} else if (Character.toLowerCase(kev.getKey()) == 'o') {
			loadTiles();
		} else if ( Character.toLowerCase(kev.getKey()) == 'a') {
			undoMove();
		}
		else if ( Character.toLowerCase(kev.getKey()) == 'd') {
			redoMove();
		}
	}

	public GoWorld mouseMoved(MouseEvent mev) {
		this.prevX = mev.getX();
		this.prevY = mev.getY();
		return this;
	}
}