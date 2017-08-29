import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Node<T> {
    public final T data;
    private final List<Node<T>> children = new ArrayList<Node<T>>();
    
    public Node(T e) {
        data = e;
    }
    
    public void addChild(Node<T> node) {
        children.add(node);
    }
    
    public List<Node<T>> getChildren() {
        return Collections.unmodifiableList(children);
    }
    
    public List<T> getChildrenData() {
        List<T> childrenData = new ArrayList<T>();
        
        for (Node<T> node: children) {
          childrenData.add(node.data);
        }
        
        return Collections.unmodifiableList(childrenData);
    }
}