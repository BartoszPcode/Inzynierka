package Model;
import java.util.ArrayList;

public class Node {
	
	public String nodeName;
	public ArrayList<Edge> edgeList = new ArrayList<Edge>();
	public int order;
	public int unloadCost = 10;
	public int loadCost = 10;
	public int nodeIndex = -1;
	
	public Node() {}

	public Node(String nodeName, int order) {
		
		this.nodeName = nodeName;
		this.order = order;
	}
	
	public Node(String nodeName, int order, int unloadCost) {
		
		this.nodeName = nodeName;
		this.order = order;
		this.unloadCost = unloadCost;
	}
	
	public void addEdge(Edge edge) {
		
		this.edgeList.add(edge);
	}	
}
