import static org.junit.Assert.*;

import org.junit.Test;

public class NewickTest {
	@Test
	public void instantiationTest(){
		Newick newick = new Newick("x");
		assertEquals("x", newick.newick);
	}

	@Test
	public void testNextForRootElement(){
		Newick newick = new Newick("root");
		assertEquals("root", newick.newick);
	}
}
