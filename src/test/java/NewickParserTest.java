import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

public class NewickParserTest {

	private void assertChildrenEqual(String expected, List<String> actual) {
		assertArrayEquals(expected.split(","), actual.toArray(new String[0]));
	}

	/*
	@Test
	public void returnWithNonNullNode(){
		Newick newick = new Newick("root");
		Node<String> root = newick.parse();
		assertNotNull(root);
	}

	@Test
	public void returnWithRootNode(){
		Newick newick = new Newick("root");
		Node<String> root = newick.parse();
		assertEquals("root",root.data);
	}

	@Test
	public void returnWithRootAndChild(){
		Newick newick = new Newick("root(child)");
		Node<String> root = newick.parse();
		assertEquals("root",root.data);
		assertEquals(1, root.getChildren().size());
		assertEquals("child", root.getChildrenData().get(0));
	}

	@Test
	public void returnWithRootAndChildren(){
		Newick newick = new Newick("root(child1,child2,child3)");
		Node<String> root = newick.parse();
		assertEquals("root",root.data);
		assertEquals(3, root.getChildren().size());
		List<String> childrenData = root.getChildrenData();
		assertChildrenEqual("child1,child2,child3", root.getChildrenData());
	}

*/
	@Test
	public void returnWithThreeLevelTree(){
		Newick newick = new Newick("root(child1(sub1,sub2),child2,child3)");
		Node<String> root = newick.parse();
		assertEquals("root",root.data);
		assertEquals(3, root.getChildren().size());
		List<String> childrenData = root.getChildrenData();
		assertChildrenEqual("child1,child2,child3", root.getChildrenData());
		Node<String> child1 = root.getChildren().get(0);
		assertChildrenEqual("sub1,sub2", child1.getChildrenData());
	}

}
