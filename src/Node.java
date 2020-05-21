import java.util.ArrayList;
import java.util.List;

public class Node {
    private String city;
    private List<Edge> edges;
 
    public Node(String city) {
        this.city = city;
        edges = new ArrayList<Edge>();
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public List<Edge> getEdges() {
        return edges;
    }
 
    public void addEdge(Edge edge) {
        edges.add(edge);
    }
 
    @Override
    public String toString() {
    	String retorno = "";
    	retorno += city +" = {";
    	for (Edge e: edges) {
    		if (edges.indexOf(e) == edges.size() -1) {
    			retorno += e.toString();
    		}else {
    			retorno += e.toString() + ",";
    		}
    		
    	}
    	retorno +="}";
    	return retorno;
    }
}