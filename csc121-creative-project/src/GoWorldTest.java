import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GoWorldTest {

	@Test
	void testConversions() {
		// (200, 111)  ====>  (1, 2)
		assertEquals(2, GoWorld.logicalCol(200));
		assertEquals(1, GoWorld.logicalRow(110));
		
		
		
	}

}
