import java.util.Deque;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Newick {
	public final String newick;

	private Node<String> lastNode;

	private Matcher newickElementMatcher;

	private Stack<Node<String>> parents = new Stack<Node<String>>();

	private boolean thereIsMoreNewickElement = true;

	public Newick(String newick) {
		newick = newick.toLowerCase();
		newick = newick.replaceAll("[^a-z0-9(),]", "");
		this.newick = newick;

		String separateAtWholeWordOrOneCharNonWord = "\\W{1}|\\w+";
		Pattern p = Pattern.compile(separateAtWholeWordOrOneCharNonWord);
		newickElementMatcher = p.matcher(newick);
	}

	private String nextNewickElement() {
		String newickElement = "";

		if (newickElementMatcher.find()) {
			newickElement =
				newick.substring(
					newickElementMatcher.start(), newickElementMatcher.end()
				);
		}
		else {
			thereIsMoreNewickElement  = false;
		}

		return newickElement;
	}

	private void processNewick() {
		while (thereIsMoreNewickElement) {
			processOneElement();
		}
	}

	private void processOneElement() {
		String newickElement = nextNewickElement();

		if (!thereIsMoreNewickElement) return;

		if (isWord(newickElement)) {
			newNodeIsLastNodesChild(newickElement);
		}
		else {
			switch (newickElement.charAt(0)) {
				case '(' :
					lastNodeIsNewParent();
					break;
				case ')' :
					setLastParentAsLastNodeAndRemoveItFromParents();
					break;
				case ',' :
					setLastNodeToLastParent();
					break;
			}
		}
	}

	private void setLastNodeToLastParent() {
		lastNode = parents.peek();
	}

	private void setLastParentAsLastNodeAndRemoveItFromParents() {
		lastNode = parents.pop();
	}

	private void lastNodeIsNewParent() {
		parents.push(lastNode);
	}

	private void newNodeIsLastNodesChild(String newickElement) {
		Node<String> child = new Node<String>(newickElement);
		lastNode.addChild(child);
		lastNode = child;
	}

	private boolean isWord(String s) {
		return s.matches("\\w+");
	}

	public Node<String> parse() {
		String rootData = nextNewickElement();
		Node<String> root = new Node<String>(rootData);

		lastNode = root;

		processNewick();

		return root;
	}
}