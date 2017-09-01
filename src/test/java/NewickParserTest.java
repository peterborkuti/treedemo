import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

public class NewickParserTest {

	private void assertChildrenEqual(String expected, List<String> actual) {
		assertArrayEquals(expected.split(","), actual.toArray(new String[0]));
	}

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
		Newick newick = new Newick("root(child1,child2)");
		Node<String> root = newick.parse();
		assertEquals("root",root.data);
		assertEquals(2, root.getChildren().size());
		assertChildrenEqual("child1,child2", root.getChildrenData());
	}

	@Test
	public void returnWithRootAndManyChildren(){
		Newick newick = new Newick("root(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10)");
		Node<String> root = newick.parse();
		assertEquals("root",root.data);
		assertEquals(10, root.getChildren().size());
		assertChildrenEqual("c1,c2,c3,c4,c5,c6,c7,c8,c9,c10", root.getChildrenData());
	}

	@Test
	public void returnWithThreeLevelTree(){
		Newick newick = new Newick("root(child1(sub1))");
		Node<String> root = newick.parse();
		assertEquals("root",root.data);
		assertEquals(1, root.getChildren().size());
		assertChildrenEqual("child1", root.getChildrenData());
		Node<String> child1 = root.getChildren().get(0);
		assertChildrenEqual("sub1", child1.getChildrenData());
	}

	@Test
	public void returnWithThreeLevelTree2(){
		Newick newick = new Newick("root(c1(s1),c2)");
		Node<String> root = newick.parse();
		assertEquals("root",root.data);
		assertEquals(2, root.getChildren().size());
		assertChildrenEqual("c1,c2", root.getChildrenData());
		Node<String> c1 = root.getChildren().get(0);
		assertChildrenEqual("s1", c1.getChildrenData());
	}

	@Test
	public void returnWithThreeLevelTree3(){
		Newick newick = new Newick("root(c1(s1,s2,s3),c2(s4,s5),c3");
		Node<String> root = newick.parse();
		assertEquals("root",root.data);
		assertChildrenEqual("c1,c2,c3", root.getChildrenData());
		Node<String> c1 = root.getChildren().get(0);
		assertChildrenEqual("s1,s2,s3", c1.getChildrenData());
		Node<String> c2 = root.getChildren().get(1);
		assertChildrenEqual("s4,s5", c2.getChildrenData());
		Node<String> c3 = root.getChildren().get(2);
		assertEquals(0, c3.getChildren().size());
	}
}
