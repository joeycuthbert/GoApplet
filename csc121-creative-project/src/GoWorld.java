package src;

import java.io.File;

import java.io.*;  
import java.io.PrintWriter;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent; 
import java.util.Scanner;
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
		//TODO
		try {
			PrintWriter pw = new PrintWriter(new File("output.txt"));

			for (Intersection inter : this.board.getPts()) {
				inter.writeToFile(pw);

			}
			pw.close();
		} catch(IOException exp) {
			System.out.println("Problem saving tiles: " + exp.getMessage());}
	}

		
		
	public void loadTiles() {
		//TODO
		try {
			Scanner sc = new Scanner(new File("output.txt"));
			this.board.pts.clear();
			
			while(sc.hasNextInt()) {
				Intersection inter = new Intersection(sc);
				
			}
			
			sc.close();
			
		}catch(IOException exp) {
			
			System.out.println("Problem loading stones: " + exp.getMessage());
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
		if (Character.toLowerCase(kev.getKey()) == 's') {
			saveTiles();
		}
		}
}