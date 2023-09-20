import processing.core.PApplet;

public interface IStone {

}

class WhiteStone implements IStone{
    int row;
    int col;
    
    public WhiteStone(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public PApplet draw(PApplet c) {
        c.fill(255);
        c.circle(col * 60, row * 60, 60);
        return c;
    }
	
}

class BlackStone implements IStone{
	IStone black;

	public BlackStone(IStone black) {
		this.black = black;
	}
}
