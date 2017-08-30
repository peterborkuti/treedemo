import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Newick {
    private Node<String> root = null;

    public final String newick;

    private String separateAtWholeWordOrOneCharNonWord = "\\W{1}|\\w+";

    private Matcher matcher;
    private int matcherIndex = 0;
    private boolean shouldExecuteFind = true;

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

    public void parse() {
        
    }
    
}