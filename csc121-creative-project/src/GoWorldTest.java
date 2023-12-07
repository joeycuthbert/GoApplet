package src;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

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
	void testoffBoard() {
		assertTrue(testBoard.offBoard(testBoard.getLoc(0, 0), -1, 0)); // in top left corner, trying to move to the left
		assertTrue(testBoard.offBoard(testBoard.getLoc(8, 8), 1, 0)); // in bottom right corner, trying to move to the right
		assertTrue(testBoard.offBoard(testBoard.getLoc(4, 0), -1, 0)); // on left side, trying to move to the left
		assertTrue(testBoard.offBoard(testBoard.getLoc(4, 8), 1, 0)); // on right side, trying to move to the right

		assertTrue(testBoard.offBoard(testBoard.getLoc(0, 5), 0, -1)); // on top row, trying to move up
		assertTrue(testBoard.offBoard(testBoard.getLoc(8, 5), 0, 1));  // on bottom row, trying to move down


	}

	@Test
	void testcheckSurrInDir() {
		testBoard.set(0, 1, Intersection.BLACK);
		testBoard.set(2, 1, Intersection.BLACK);
		testBoard.set(1, 0, Intersection.BLACK);
		testBoard.set(1, 2, Intersection.BLACK);
		testBoard.set(1, 1, Intersection.WHITE); // this white stone is completely surrounded by black stones 
		testBoard.set(5, 6, Intersection.WHITE); // this white stone is not surrounded by any black stones
/*
		assertTrue(testBoard.checkSurrInDir(testBoard.getLoc(1, 1), 1, 0, Intersection.WHITE));
		assertTrue(testBoard.checkSurrInDir(testBoard.getLoc(1, 1), -1, 0, Intersection.WHITE));
		assertTrue(testBoard.checkSurrInDir(testBoard.getLoc(1, 1), 0, 1, Intersection.WHITE));
		assertTrue(testBoard.checkSurrInDir(testBoard.getLoc(1, 1), 0, -1, Intersection.WHITE));

		assertFalse(testBoard.checkSurrInDir(testBoard.getLoc(5,6), 1, 0, Intersection.WHITE)); 
		assertFalse(testBoard.checkSurrInDir(testBoard.getLoc(5,6), -1, 0, Intersection.WHITE)); 
		assertFalse(testBoard.checkSurrInDir(testBoard.getLoc(5,6), 0, 1, Intersection.WHITE)); 
		assertFalse(testBoard.checkSurrInDir(testBoard.getLoc(5,6), 0, -1, Intersection.WHITE));  
*/

	}

	@Test
	void testcheckSurr() {
		testBoard.set(0, 1, Intersection.BLACK);
		testBoard.set(2, 1, Intersection.BLACK);
		testBoard.set(1, 0, Intersection.BLACK);
		testBoard.set(1, 2, Intersection.BLACK);
		testBoard.set(1, 1, Intersection.WHITE); // this white stone is completely surrounded by black stones 
		testBoard.set(5, 6, Intersection.WHITE); // this white stone is not surrounded by any black stones

		assertTrue(testBoard.checkSurr(testBoard.getLoc(1, 1), Intersection.WHITE));
		
		testBoard.set(0, 0, Intersection.BLACK);
		testBoard.set(0, 2, Intersection.BLACK);
		testBoard.set(0, 1, Intersection.WHITE);
		
		assertTrue(testBoard.checkSurr(testBoard.getLoc(1,1), Intersection.WHITE)); 
		assertTrue(testBoard.checkSurr(testBoard.getLoc(0,1), Intersection.WHITE)); 
		
		
		testBoard.set(0, 8, Intersection.WHITE);
		testBoard.set(0, 7, Intersection.BLACK);
		testBoard.set(1, 8, Intersection.BLACK);
		testBoard.set(1, 7, Intersection.WHITE);
		
		assertTrue(testBoard.checkSurr(testBoard.getLoc(0,8), Intersection.WHITE));
		assertFalse(testBoard.checkSurr(testBoard.getLoc(1,8), Intersection.BLACK)); 
		/*
		 * the following two test cases check if a location is surrounded but for the opposite color of the stone that occupies
		 * that location
		 */
		assertFalse(testBoard.checkSurr(testBoard.getLoc(1,8), Intersection.WHITE));
		assertFalse(testBoard.checkSurr(testBoard.getLoc(0,8), Intersection.BLACK));
		
	}
	
	@Test 
	void testCheckAllSurr() {
		testBoard.set(0, 1, Intersection.BLACK);
		testBoard.set(2, 1, Intersection.BLACK);
		testBoard.set(1, 0, Intersection.BLACK);
		testBoard.set(1, 2, Intersection.BLACK);
		testBoard.set(1, 1, Intersection.WHITE); // this white stone is completely surrounded by black stones 
		testBoard.set(5, 6, Intersection.WHITE); // this white stone is not surrounded by any black stones
		
		boolean[] testArr = new boolean[81];
		testArr[testBoard.getLoc(1, 1)] = true; 
		
		assertTrue(Arrays.equals(testArr, testBoard.checkAllSurr(Intersection.WHITE))); 
		assertFalse(Arrays.equals(testArr, testBoard.checkAllSurr(Intersection.BLACK))); 
		
		testBoard.set(0, 0, Intersection.BLACK);
		testBoard.set(0, 2, Intersection.BLACK);
		testBoard.set(0, 1, Intersection.WHITE);
		testArr[testBoard.getLoc(0, 1)] = true; 
		
		assertTrue(Arrays.equals(testArr, testBoard.checkAllSurr(Intersection.WHITE))); 
		
		testBoard.set(0, 8, Intersection.WHITE);
		testBoard.set(0, 7, Intersection.BLACK);
		testBoard.set(1, 8, Intersection.BLACK);
		testBoard.set(1, 7, Intersection.WHITE);
		
		testArr[testBoard.getLoc(0, 8)] = true; 
		
		assertTrue(Arrays.equals(testArr, testBoard.checkAllSurr(Intersection.WHITE))); 
		
	
	}
	}
//	@Test
//	void testXPattern() {
//		//setting black Stones
//		testBoard.set(0, 0, Intersection.BLACK);
//		testBoard.set(1, 1, Intersection.BLACK);
//		testBoard.set(2, 2, Intersection.BLACK);
//		testBoard.set(3, 3, Intersection.BLACK);
//		testBoard.set(4, 0, Intersection.BLACK);
//		testBoard.set(4, 1, Intersection.BLACK);
//		testBoard.set(4, 2, Intersection.BLACK);
//		testBoard.set(5, 3, Intersection.BLACK);
//		testBoard.set(6, 2, Intersection.BLACK); 
//		testBoard.set(7, 1, Intersection.BLACK);
//		testBoard.set(8, 0, Intersection.BLACK);
//		testBoard.set(4, 4, Intersection.BLACK);
//		testBoard.set(3, 5, Intersection.BLACK);
//		testBoard.set(4, 5, Intersection.BLACK);
//		testBoard.set(5, 5, Intersection.BLACK); 
//		testBoard.set(6, 2, Intersection.BLACK);
//		testBoard.set(6, 3, Intersection.BLACK);
//		testBoard.set(6, 4, Intersection.BLACK);
//		testBoard.set(6, 6, Intersection.BLACK);
//		testBoard.set(7, 1, Intersection.BLACK);
//		testBoard.set(7, 2, Intersection.BLACK);  
//		testBoard.set(7, 3, Intersection.BLACK);
//		testBoard.set(7, 4, Intersection.BLACK);
//		testBoard.set(7, 7, Intersection.BLACK);
//		testBoard.set(8, 0, Intersection.BLACK);
//		testBoard.set(8, 1, Intersection.BLACK);
//		testBoard.set(8, 2, Intersection.BLACK); 
//		testBoard.set(8, 3, Intersection.BLACK);
//		testBoard.set(8, 4, Intersection.BLACK);
//		testBoard.set(8, 5, Intersection.BLACK);
//		testBoard.set(8, 8, Intersection.BLACK);
//		
//		//getting black Stone locations
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(0, 0), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(1, 1), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(2, 2), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(3, 3), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(4, 0), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(4, 1), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(4, 2), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(5, 3), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(6, 2), Intersection.BLACK)); 
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(7, 1), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(8, 0), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(4, 4), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(3, 5), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(4, 5), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(5, 5), Intersection.BLACK)); 
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(2, 6), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(3, 6), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(4, 6), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(6, 6), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(1, 7), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(2, 7), Intersection.BLACK));  
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(3, 7), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(4, 7), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(7, 7), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(0, 8), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(1, 8), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(2, 8), Intersection.BLACK)); 
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(3, 8), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(4, 8), Intersection.BLACK));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(5, 8), Intersection.BLACK));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(8, 8), Intersection.BLACK));
//		
//		
//		//getting black Stone locations
//		testBoard.set(1, 0, Intersection.WHITE);
//		testBoard.set(3, 0, Intersection.WHITE); 
//		testBoard.set(5, 0, Intersection.WHITE);
//		testBoard.set(7, 0, Intersection.WHITE);
//		testBoard.set(0, 1, Intersection.WHITE);
//		testBoard.set(2, 1, Intersection.WHITE);
//		testBoard.set(3, 1, Intersection.WHITE);
//		testBoard.set(5, 1, Intersection.WHITE);  
//		testBoard.set(6, 1, Intersection.WHITE);
//		testBoard.set(8, 1, Intersection.WHITE);
//		testBoard.set(1, 2, Intersection.WHITE);
//		testBoard.set(3, 2, Intersection.WHITE);
//		testBoard.set(5, 2, Intersection.WHITE);
//		testBoard.set(7, 2, Intersection.WHITE); 
//		testBoard.set(2, 3, Intersection.WHITE);
//		testBoard.set(4, 3, Intersection.WHITE);
//		testBoard.set(6, 3, Intersection.WHITE); 
//		testBoard.set(3, 4, Intersection.WHITE);
//		testBoard.set(5, 4, Intersection.WHITE);
//		testBoard.set(2, 5, Intersection.WHITE);
//		testBoard.set(6, 5, Intersection.WHITE);
//		testBoard.set(1, 6, Intersection.WHITE);
//		testBoard.set(5, 6, Intersection.WHITE);  
//		testBoard.set(7, 6, Intersection.WHITE);
//		testBoard.set(0, 7, Intersection.WHITE);
//		testBoard.set(5, 7, Intersection.WHITE);
//		testBoard.set(6, 7, Intersection.WHITE);
//		testBoard.set(8, 7, Intersection.WHITE); 
//		testBoard.set(6, 8, Intersection.WHITE);
//		testBoard.set(7, 8, Intersection.WHITE);
//		
//		//setting white stones
//		
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(1, 0), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(3, 0), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(5, 0), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(7, 0), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(0, 1), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(2, 1), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(3, 1), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(5, 1), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(6, 1), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(8, 1), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(1, 2), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(3, 2), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(5, 2), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(7, 2), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(2, 3), Intersection.WHITE));
//		assertTrue(testBoard.checkSurr(testBoard.getLoc(4, 3), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(6, 3), Intersection.WHITE)); 
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(3, 4), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(5, 4), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(2, 5), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(6, 5), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(1, 6), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(5, 6), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(7, 6), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(0, 7), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(5, 7), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(6, 7), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(8, 7), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(6, 8), Intersection.WHITE));
//		assertFalse(testBoard.checkSurr(testBoard.getLoc(7, 8), Intersection.WHITE));
//		
//		
//	}
//}
