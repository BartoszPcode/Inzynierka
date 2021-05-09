package Model;

public class Edge {
	
	public Node from;
	public String edgeName;
	public Node to;
	public int cost = 1;

	
	public Edge(String edgeName) {
		
		this.edgeName = edgeName;
	}
	
	public Edge(String edgeName, int cost) {
		
		this.edgeName = edgeName;
		this.cost = cost;
	}

	public void editEdge(Node from, Node to) {
		
		this.from = from;
		this.to = to;
	}	
}
