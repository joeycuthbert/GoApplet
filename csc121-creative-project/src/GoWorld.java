import processing.core.PApplet;
import processing.event.MouseEvent; 

public class GoWorld {
	int rows;
	int columns; 
	
	
public GoWorld(int rows, int columns){
		this.rows = rows; 
		this.columns = columns;  
	}

public PApplet draw(PApplet c) {
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
    return c;
}

public void drawCircle(PApplet c, float x, float y) {
	c.circle(x, y, 60);
}

}