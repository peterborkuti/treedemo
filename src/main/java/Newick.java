import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Newick {
    private Node<String> root = null;

    public final String newick;

    private String workNewick;

    private String separateAtNonWordChars = "\\W";

    private Matcher matcher;

    private int matcherStartIndex = 0;

    public Newick(String newick) {
    	newick = newick.toLowerCase();
    	newick = newick.replaceAll("[^a-z(),]", "");
        this.newick = newick;
        this.workNewick = newick;
        Pattern p = Pattern.compile(separateAtNonWordChars);
        matcher = p.matcher(newick);

    }
    
    public String nextNewickElement() {
    	if (!matcher.find()) {
    		return "";
    	}

    	return newick;
    }
    
    public void parse() {
        
    }
    
}