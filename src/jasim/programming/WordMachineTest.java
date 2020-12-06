package jasim.programming;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class WordMachineTest {

	@Test
	public void testMachineWithValidInput() {
		WordMachine wm = new WordMachine();
		assertEquals(8, wm.machineOutput("4 5 6 - 7 +"));
	}
	
	@Test
	public void testInvalidArgument() {
		WordMachine wm = new WordMachine();
		assertEquals(-1, wm.machineOutput("5 6 + -"));
	}
}
