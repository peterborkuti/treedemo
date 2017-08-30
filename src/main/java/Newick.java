import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Newick {
    private Node<String> root = null;

    public final String newick;

    private String separateAtNonWordChars = "\\W";

    private Matcher matcher;
    private int matcherIndex = 0;
    private boolean shouldExecuteFind = true;

    public Newick(String newick) {
    	newick = newick.toLowerCase();
    	newick = newick.replaceAll("[^a-z(),]", "");
        this.newick = newick;
        Pattern p = Pattern.compile(separateAtNonWordChars);
        matcher = p.matcher(newick);

    }

    private String findAndGiveTheStringUpToMatchStart() {
    	String match = "";

		boolean found = matcher.find();

		if (found) {
			match = newick.substring(matcherIndex, matcher.start());

			matcherIndex = matcher.start();
		}
		else {
			match = newick.substring(matcherIndex);
		}

		return match;
    }

    private String giveTheStringAtMatch() {
		String match = newick.substring(matcher.start(), matcher.end());

		matcherIndex = matcher.end();

		return match;
    }

    public String nextNewickElement() {
    	String nextNewick = "";

    	if (shouldExecuteFind) {
    		nextNewick = findAndGiveTheStringUpToMatchStart();
    	}
    	else {
    		nextNewick = giveTheStringAtMatch();
    	}

    	shouldExecuteFind = !shouldExecuteFind;

    	return nextNewick;
    }

    public void parse() {
        
    }
    
}