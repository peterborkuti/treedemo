import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NodeTest {
  @Test
  public void testCreateNode() {
    Node<String> node = new Node<String>("x");
    assertEquals("x", node.data);
  }
  
  @Test
  public void testAddChild() {
    Node<String> node = new Node<String>(null);
    Node<String> child = new Node<String>("child");
    node.addChild(child);
    assertArrayEquals(new String[]{"child"}, node.getChildrenData().toArray(new String[0]));
  }
}