import processing.core.*;
import processing.event.*;

public class GoApp extends PApplet {
    private GoWorld g;

	public void settings() {
        this.size(700, 700);
    }

    public void setup() {
    	Board nineBoard = new Board(9, 9, 0);
        g = new GoWorld(nineBoard);
    }

    public void draw() {        
        g = g.update(); 
        g.draw(this);
    }

    public void mouseMoved(MouseEvent mev) {
        g = g.mouseMoved(mev);
    }
    
    public void mousePressed(MouseEvent mev) {
        g = g.mousePressed(mev);
    }
    
    public void keyPressed(KeyEvent kev) {
    	g.keyPressed(kev);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "GoApp" }, new GoApp());
    }
}



