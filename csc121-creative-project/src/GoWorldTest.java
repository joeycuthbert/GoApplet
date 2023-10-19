import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GoWorldTest {
	Board testBoard = new Board(9, 9, 0); 
	@Test
	void testConversions() {
		// (200, 111)  ====>  (1, 2)
		assertEquals(2, GoWorld.logicalCol(200));
		assertEquals(1, GoWorld.logicalRow(110));
		
	}
	
	@Test
	void testgetLoc() {
		assertEquals(0, testBoard.getLoc(0, 0));
		assertEquals(80, testBoard.getLoc(8, 8)); 
		assertEquals(1, testBoard.getLoc(0, 1));
		assertEquals(51, testBoard.getLoc(5,  6)); 
	}
	
	
	@Test
	void testcheckSurrInDir() {
		testBoard.set(0, 1, Intersection.BLACK);
		testBoard.set(2, 1, Intersection.BLACK);
		testBoard.set(1, 0, Intersection.BLACK);
		testBoard.set(1, 2, Intersection.BLACK);
		testBoard.set(1, 1, Intersection.WHITE); // this white stone is completely surrounded by black stones 
		testBoard.set(5, 6, Intersection.WHITE); // this white stone is not surrounded by any black stones
		
		assertTrue(testBoard.checkSurrInDir(testBoard.getLoc(1, 1), 1, 0, Intersection.WHITE));
		assertTrue(testBoard.checkSurrInDir(testBoard.getLoc(1, 1), -1, 0, Intersection.WHITE));
		assertTrue(testBoard.checkSurrInDir(testBoard.getLoc(1, 1), 0, 1, Intersection.WHITE));
		assertTrue(testBoard.checkSurrInDir(testBoard.getLoc(1, 1), 0, -1, Intersection.WHITE));
		
		assertFalse(testBoard.checkSurrInDir(testBoard.getLoc(5,6), 1, 0, Intersection.WHITE)); 
		assertFalse(testBoard.checkSurrInDir(testBoard.getLoc(5,6), -1, 0, Intersection.WHITE)); 
		assertFalse(testBoard.checkSurrInDir(testBoard.getLoc(5,6), 0, 1, Intersection.WHITE)); 
		assertFalse(testBoard.checkSurrInDir(testBoard.getLoc(5,6), 0, -1, Intersection.WHITE)); 
		
		
		
		
		
	}
	
}
