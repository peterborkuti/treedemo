import static org.junit.Assert.*;

import org.junit.Test;


public class DFSTest {
	@Test
	public void DFSClassInstantiation() {
		Node<String> root = new Node<String>("root");
		DFS dfs = new DFS(root);
		assertNotNull(dfs);
		assertNotNull(dfs.root);
		assertEquals("root", dfs.root.data);
	}

	@Test
	public void walkOnRoot() {
		Node<String> root = new Node<String>("root");
		DFS DFS = new DFS(root);
		assertEquals("root", DFS.walk());
	}

	@Test
	public void walkOnRootAndChild() {
		Newick newick = new Newick("root(child)");
		Node<String> root = newick.parse();
		DFS DFS = new DFS(root);
		assertEquals("child,root", DFS.walk());
	}

	@Test
	public void walkOnRootAndMoreChild() {
		Newick newick = new Newick("root(c1,c2,c3,c4)");
		Node<String> root = newick.parse();
		DFS DFS = new DFS(root);
		assertEquals("c1,c2,c3,c4,root", DFS.walk());
	}

	@Test
	public void walkOnRootAndSubchild() {
		Newick newick = new Newick("root(c1(s1))");
		Node<String> root = newick.parse();
		DFS DFS = new DFS(root);
		assertEquals("s1,c1,root", DFS.walk());
	}

	@Test
	public void walkOnComplexTree() {
		Newick newick = new Newick("root(c1(s1),c2,c3(s2,s3(s5,s6),s4)");
		Node<String> root = newick.parse();
		DFS DFS = new DFS(root);
		assertEquals("s1,c1,c2,s2,s5,s6,s3,s4,c3,root", DFS.walk());
	}

}
