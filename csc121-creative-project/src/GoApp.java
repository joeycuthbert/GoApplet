import processing.core.*;
import processing.event.*;

public class GoApp extends PApplet {
    GoWorld g;

	public void settings() {
        this.size(700, 700);
    }

    public void setup() {
    	Board NineBoard = new Board(9,9);
        g = new GoWorld(NineBoard);
    }

    public void draw() {
        g.draw(this);
        // g = g.update(); 
    }

    public void mousePressed(MouseEvent mev) {
        g = g.mousePressed(mev);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "GoApp" }, new GoApp());
    }
}



