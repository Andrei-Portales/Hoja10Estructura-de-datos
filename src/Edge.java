


public class Edge {
    
    private Node destination;
    private long distance;
 
    public Edge(Node destination, long distance) {
        
        this.destination = destination;
        this.distance = distance;
    }
 
 
    public Node getDestination() {
        return destination;
    }
 
    public void setDestination(Node destination) {
        this.destination = destination;
    }
 
    public long getDistance() {
        return distance;
    }
 
    public void setDistance(long distance) {
        this.distance = distance;
    }
    
    @Override
    public String toString() {
    	return destination.getCity()+"="+distance;
    }
 
 
}