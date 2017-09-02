import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
	public final Node<String> root;

	private List<String> elementsData = new ArrayList<String>();

	private Queue<Node<String>> queue = new LinkedList<Node<String>>();

	public BFS(Node<String> node) {
		root = node;
		queue.add(root);
	}

	public String walk() {
		if (!queue.isEmpty()) {
			Node<String> node = queue.poll();
	
			elementsData.add(node.data);
			queue.addAll(node.getChildren());
	
			walk();
		}

		return String.join(",", elementsData);
	}


}
