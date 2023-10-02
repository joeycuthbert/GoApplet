import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GoTests {
	
	//Player2 Bella = new Player2(wStone1, false, 0);
	
	
	//Player1 Joe = new Player1(bStone1, true, 0);
	
	Board b = new Board(2, 3);
	
	@Test
	void test() {

		b.set(1,  2, Intersection.BLACK);
		assertEquals(Intersection.EMPTY,  b.get(0, 0));
		assertEquals(Intersection.BLACK,  b.get(1, 2));
	
	}

}
