import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GoTests {
	WhiteStone wStone1 = new WhiteStone(0,1);
	Player2 Bella = new Player2(wStone1, false, 0);
	
	BlackStone bStone1 = new BlackStone(0,0);
	Player1 Joe = new Player1(bStone1, true, 0);
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
