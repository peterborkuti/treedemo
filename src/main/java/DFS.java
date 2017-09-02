import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DFS {
	public final Node<String> root;

	private List<String> elementsData = new ArrayList<String>();

	public DFS(Node<String> node) {
		root = node;
	}

	private void walk(Node<String> node) {
		for (Node<String> child: node.getChildren()) {
			walk(child);
		}

		elementsData.add(node.data);
	}

	public String walk() {
		walk(root);

		return String.join(",", elementsData);
	}


}
