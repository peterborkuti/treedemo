import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TreeBuilderTest {
  @Test
  public void buildOnlyRootTree() {
    Node<String> root = TreeBuilder.buildStringTreeFromNewickFormat("x");
    assertEquals("x", root.data);
    assertEquals(0, root.getChildren().size());
  }
  

  public void buildOneChildTree() {
    Node<String> root = TreeBuilder.buildStringTreeFromNewickFormat("root(child)");
    assertEquals("root", root.data);
    assertArrayEquals(new String[]{"child"}, root.getChildrenData().toArray(new String[0]));
  }
  
}