import java.util.Deque;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Newick {
	public final String newick;

	private static final String separateAtWholeWordOrOneCharNonWord = "\\W{1}|\\w+";

	private Matcher matcher;

	private Stack<Node<String>> parents = new Stack<Node<String>>();

	public Newick(String newick) {
		newick = newick.toLowerCase();
		newick = newick.replaceAll("[^a-z0-9(),]", "");
		this.newick = newick;
		Pattern p = Pattern.compile(separateAtWholeWordOrOneCharNonWord);
		matcher = p.matcher(newick);
	}

	private String nextNewickElement() {
		return (matcher.find()) ? newick.substring(matcher.start(), matcher.end()) : "";
	}

	private void processNewick(Node<String> lastNode) {
		String newickElement = nextNewickElement();

		if (newickElement == "") return;

		if (isWord(newickElement)) {
			Node<String> child = new Node<String>(newickElement);
			lastNode.addChild(child);
			processNewick(child);
		}
		else {
			switch (newickElement.charAt(0)) {
				case '(' :
					parents.push(lastNode);
					processNewick(lastNode);
					break;
				case ')' :
					processNewick(parents.pop());
					break;
				case ',' :
					processNewick(parents.peek());
					break;
			}
		}
	}

	private boolean isWord(String s) {
		return s.matches("\\w+");
	}

	
	
	public Node<String> parse() {
		String rootData = nextNewickElement();
		Node<String> root = new Node<String>(rootData);

		processNewick(root);

		return root;
	}

}