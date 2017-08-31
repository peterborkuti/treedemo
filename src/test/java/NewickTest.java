import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class NewickTest {
	private String invokeNextNewickElement(Newick newick) {
		Method method = null;
		try {
			method = newick.getClass().getDeclaredMethod("nextNewickElement");
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		method.setAccessible(true);

		Object newickElement = null;
		try {
			newickElement = method.invoke(newick);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newickElement == null ? "" : (String)newickElement;
	}

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
		assertEquals("only root element", "root", invokeNextNewickElement(newick));
	}

	@Test
	public void testTwoClosingBrackets(){
		Newick newick = new Newick("))");
		assertEquals("first bracket", ")", invokeNextNewickElement(newick));
		assertEquals("second bracket", ")", invokeNextNewickElement(newick));
		assertEquals("reading after end", "", invokeNextNewickElement(newick));
	}

	@Test
	public void testNextNewickElementForTree(){
		Newick newick = new Newick("root(child)");
		assertEquals("root", "root", invokeNextNewickElement(newick));
		assertEquals("(", "(", invokeNextNewickElement(newick));
		assertEquals("child", "child", invokeNextNewickElement(newick));
		assertEquals(")", ")", invokeNextNewickElement(newick));
		assertEquals("reading after end", "", invokeNextNewickElement(newick));
	}

	@Test
	public void testNextBiggerNewickElementForTree(){
		Newick newick = new Newick("root(child1, child2(subchild))");
		String[] parts = {"root", "(", "child1",",", "child2","(", "subchild", ")", ")", ""};

		for (int i = 0; i < parts.length; i++) {
			assertEquals(i + ":" + parts[i], parts[i], invokeNextNewickElement(newick));
		}
	}
}
