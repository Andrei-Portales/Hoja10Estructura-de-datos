import java.util.ArrayList;
import java.util.List;

public class Graph {
 
    private List<Node> nodes;
 
    public void addNode(Node node) {
        if (nodes == null) {
            nodes = new ArrayList<Node>();
        }
        nodes.add(node);
    }
 
    public List<Node> getNodes() {
        return nodes;
    }
 
   
    @Override
    public String toString() {
    	String string = "";
    	for (Node n : nodes) {
    		string += n.toString() + "\n";
    	}
    	return string;
    }
}
