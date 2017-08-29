import static org.junit.Assert.*;

import org.junit.Test;

public class NewickTest {
	@Test
	public void instantiationTest(){
		Newick newick = new Newick("x");
		assertEquals("x", newick.newick);
	}

	@Test
	public void deleteNonAllowedChars(){
		Newick newick = new Newick("a- .b,()[]");
		assertEquals("ab,()", newick.newick);
	}

	@Test
	public void toLowerCase(){
		Newick newick = new Newick("ABC");
		assertEquals("abc", newick.newick);
	}

	@Test
	public void testNextForRootElement(){
		Newick newick = new Newick("root");
		assertEquals("root", newick.newick);
	}

	@Test
	public void testNextNewickElement(){
		Newick newick = new Newick("root");
		assertEquals("root", newick.nextNewickElement());
		newick = new Newick("(");
		assertEquals("(", newick.nextNewickElement());
		newick = new Newick("root(");
		assertEquals("root", newick.nextNewickElement());
		assertEquals("(", newick.nextNewickElement());

	}
	
}
