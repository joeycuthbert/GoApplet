import processing.core.*;
import java.util.ArrayList;
import java.util.List;

public class GoApp extends PApplet {
    GoWorld g;
    List<PVector> circles = new ArrayList<>();

    public void settings() {
        this.size(700, 700);
    }

    public void setup() {
        g = new GoWorld(9, 9);
    }

    public void draw() {
        g.draw(this);
        for (PVector circlePos : circles) {
            g.drawCircle(this, circlePos.x, circlePos.y);
        }
    }

    public void mousePressed() {
        PVector circlePos = new PVector(mouseX, mouseY);
        circles.add(circlePos);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "GoApp" }, new GoApp());
    }
}



