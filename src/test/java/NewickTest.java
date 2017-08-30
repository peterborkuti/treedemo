import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

public class NewickTest {
	private Newick newick;
	Method method = newick.getClass().getDeclaredMethod("nextNewickElement");
	method.setAccessible(true);
	Object r = method.invoke(object);
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
	public void testNextNewickElementForOneElement(){
		Newick newick = new Newick("root");
		assertEquals("only root element", "root", newick.nextNewickElement());
	}

	@Test
	public void testTwoClosingBrackets(){
		Newick newick = new Newick("))");
		assertEquals("first bracket", ")", newick.nextNewickElement());
		assertEquals("second bracket", ")", newick.nextNewickElement());
		assertEquals("reading after end", "", newick.nextNewickElement());
	}

	@Test
	public void testNextNewickElementForTree(){
		Newick newick = new Newick("root(child)");
		assertEquals("root", "root", newick.nextNewickElement());
		assertEquals("(", "(", newick.nextNewickElement());
		assertEquals("child", "child", newick.nextNewickElement());
		assertEquals(")", ")", newick.nextNewickElement());
		assertEquals("reading after end", "", newick.nextNewickElement());
	}

	@Test
	public void testNextBiggerNewickElementForTree(){
		Newick newick = new Newick("root(child1, child2(subchild))");
		String[] parts = {"root", "(", "child1",",", "child2","(", "subchild", ")", ")", ""};

		for (int i = 0; i < parts.length; i++) {
			assertEquals(i + ":" + parts[i], parts[i], newick.nextNewickElement());
		}
	}
}
