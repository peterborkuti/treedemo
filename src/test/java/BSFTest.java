import static org.junit.Assert.*;

import org.junit.Test;


public class BSFTest {
	@Test
	public void BSFClassInstantiation() {
		Node<String> root = new Node<String>("root");
		BFS bfs = new BFS(root);
		assertNotNull(bfs);
		assertNotNull(bfs.root);
		assertEquals("root", bfs.root.data);
	}

	@Test
	public void walkOnRoot() {
		Node<String> root = new Node<String>("root");
		BFS bfs = new BFS(root);
		assertEquals("root", bfs.walk());
	}

	@Test
	public void walkOnRootAndChild() {
		Newick newick = new Newick("root(child)");
		Node<String> root = newick.parse();
		BFS bfs = new BFS(root);
		assertEquals("root,child", bfs.walk());
	}

	@Test
	public void walkOnRootAndMoreChild() {
		Newick newick = new Newick("root(c1,c2,c3,c4)");
		Node<String> root = newick.parse();
		BFS bfs = new BFS(root);
		assertEquals("root,c1,c2,c3,c4", bfs.walk());
	}

	@Test
	public void walkOnRootAndSubchild() {
		Newick newick = new Newick("root(c1(s1))");
		Node<String> root = newick.parse();
		BFS bfs = new BFS(root);
		assertEquals("root,c1,s1", bfs.walk());
	}

	@Test
	public void walkOnComplexTree() {
		Newick newick = new Newick("root(c1(s1),c2,c3(s2,s3(s5,s6),s4)");
		Node<String> root = newick.parse();
		BFS bfs = new BFS(root);
		assertEquals("root,c1,c2,c3,s1,s2,s3,s4,s5,s6", bfs.walk());
	}

}
