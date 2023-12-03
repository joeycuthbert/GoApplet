import java.util.Random;

public class RandomComputerPlayer implements IComputerPlayer {

	private static Random rnumGenerator = new Random();
	
	@Override
	public int chooseMove(Board board) {
		
		//int randomNumber = (int) (Math.random() * (max - min + 1)) + min;
		
		
		boolean found = false;
		int row = 0; 
		int col = 0;
		int pos  = 0;
		
		while (!found) {
			
			row = rnumGenerator.nextInt(9); 
			col = rnumGenerator.nextInt(9);
			pos  = board.getLoc(row, col); 
			
			if( (board.get(row, col) == Intersection.EMPTY) && !(board.isSuicide(col, row, board.getColor() )) ) {
			found = true; 
			}
		}
		return pos;
	}
}
