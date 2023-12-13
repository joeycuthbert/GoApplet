
import java.io.File;
import java.util.Random;


import java.io.*;  
import java.io.PrintWriter;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent; 
import java.util.Scanner;
import java.util.Stack;
public class GoWorld {
	private Board board;
	private IComputerPlayer opponent;  // could be null, if not null the computer is always player #1
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
		this.opponent = new RandomComputerPlayer(); 
		//this.opponent = null; 
		this.board = board;
		this.prevX = -100;
		this.prevY = -100;
		this.undoStack = new Stack<Integer>();
		this.undoColStack = new Stack<Intersection>(); 
		this.redoStack = new Stack<Integer>();
		this.redoColStack = new Stack<Intersection>();
		
	}

	public GoWorld update() {
		Random rgen = new Random();
		if (this.opponent != null && this.board.getPlayer() == 1) {
	    
			int move = this.opponent.chooseMove(this.board);
			
			int row = this.board.rowOf(move);
			int col = this.board.colOf(move);
			/*
			try {
                Thread.sleep(500 + rgen.nextInt(1000));  // pause a random amt between .5 to 1.5 seconds,
            }
			catch (Exception e) {
           // ignore
        }
        */
			
			int time = rgen.nextInt(1000);
			long timer = System.currentTimeMillis(); 
			while(time != (System.currentTimeMillis() - timer)) {
			} 
			makeMove(row, col); 
		}
		
		
		
		return this;
	}
	
	
	/*
	 * calls the draw method of the boards 
	 */
	public PApplet draw(PApplet c) { 
		board.draw(c);
		this.board.getColor().draw(c, prevX, prevY);
		if (!redoStack.isEmpty()) {
			c.fill(255, 0, 0, 75);
			c.rect(0, 0, 700, 700);
		}
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
            
            
            if( this.board.getPts()[lastMove] == Intersection.EMPTY) {
            	this.redoStack.push(lastMove);
            	this.redoColStack.push(lastColor);
            	this.redoColStack.push(Intersection.EMPTY);
            	this.board.getPts()[lastMove] = lastColor;
            	while ( undoColStack.peek() == lastColor) {
            		 lastMove = this.undoStack.pop();
                     lastColor = this.undoColStack.pop();
                     this.redoStack.push(lastMove);
                     this.redoColStack.push(lastColor);
                 	 this.redoColStack.push(Intersection.EMPTY); 
                 	 this.board.getPts()[lastMove] = lastColor;
            	}
            	
            }else { 
            	this.redoColStack.push(lastColor); 
            	this.redoStack.push(lastMove);
            	this.board.getPts()[lastMove] = Intersection.EMPTY;
            }
		}
	}
	
	void redoMove() {
		if (!redoStack.isEmpty()) {
            int nextMove = this.redoStack.pop();
            Intersection nextColor = this.redoColStack.pop(); 
          
            if ( nextColor == Intersection.EMPTY ) {
            	nextColor = this.redoColStack.pop();
            	this.board.getPts()[nextMove] = Intersection.EMPTY;
            	this.undoStack.push(nextMove);
                this.undoColStack.push(nextColor);
            	while( (redoColStack.size() >= 2) && (redoColStack.peek() == Intersection.EMPTY) ) {
            		redoColStack.pop();
            		nextColor = this.redoColStack.pop();
            		nextMove = this.redoStack.pop();
                	this.board.getPts()[nextMove] = Intersection.EMPTY;
                	this.undoStack.push(nextMove);
                    this.undoColStack.push(nextColor);
            	}
            			
            }else { 
            	this.board.getPts()[nextMove] = nextColor;
            	this.undoStack.push(nextMove);
                this.undoColStack.push(nextColor);
                }
            
            
		}
	}
	
	
	private void makeMove(int logRow, int logCol) {
		if(!this.board.isSuicide(logCol,  logRow, this.board.getColor())) {
			board.set(logRow, logCol, this.board.getColor()); // place stone in pts array
			this.undoStack.push(board.getLoc(logRow, logCol)); // update the undoStack by pushing the recent move
			this.undoColStack.push(this.board.getColor()); // update the undoStack by pushing the recent move

			boolean[] deleteArr = this.board.checkAllSurr(this.board.getOppColor()); // parallel array, stores the boolean value for if a stone should be captured after the recent move 
	
			for(int i = 0; i < this.board.getPts().length; i++) {
				if(deleteArr[i]) {
					this.undoColStack.push(this.board.getPts()[i]); // update the colored undo stack
					this.undoStack.push(i); // update the undo stack
					this.board.getPts()[i] = Intersection.EMPTY; // stone at i is captured, set its intersection to empty 
					this.board.updateScore();
					System.out.println(this.board.getBlackScore());
					System.out.println(this.board.getWhiteScore()); 
					}
				}

			this.board.rotatePlayer(); 
		}
	}
	public GoWorld mousePressed(MouseEvent mev) {
		int logCol = logicalCol(mev.getX()); 
		int logRow = logicalRow(mev.getY());

		if ((this.board.get(logRow, logCol) == Intersection.EMPTY) && (this.redoStack.size() == 0)) {
			makeMove(logRow, logCol);
		}

		return this;
	}

	public void keyPressed(KeyEvent kev) {
		if (Character.toLowerCase(kev.getKey()) == 's') {
			this.saveTiles();
		} else if (Character.toLowerCase(kev.getKey()) == 'o') {
			loadTiles();
		} else if ( Character.toLowerCase(kev.getKey()) == 'z'  && kev.isMetaDown() && kev.isShiftDown() ) {
				redoMove();
		} else if ( Character.toLowerCase(kev.getKey()) == 'z'  && kev.isMetaDown() ) {
			undoMove();
		}
	}

	public GoWorld mouseMoved(MouseEvent mev) {
		this.prevX = mev.getX();
		this.prevY = mev.getY();
		return this;
	}
}