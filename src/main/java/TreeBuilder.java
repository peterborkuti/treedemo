public class TreeBuilder {

    public static Node<String> buildStringTreeFromNewickFormat(String newickFormat) {
        
        return new Node<String>(newickFormat);
        
    } 
}