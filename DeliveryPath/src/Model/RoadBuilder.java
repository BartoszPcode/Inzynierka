package Model;
import java.util.ArrayList;

public class RoadBuilder {

	public ArrayList<Node> nodesInRoad = new ArrayList<Node>();
	public ArrayList<Edge> edgesInRoad = new ArrayList<Edge>();
	public int numberOfBestRoadToComeBack = -1;
	public boolean[] indexOfVisitedNode;	
	int indexOfLastNode = -1;	
	int drivingCost = -1;
	int nodesHandlingCost = -1;	
	
	public RoadBuilder(ArrayList<Node> nodesInRoad, int graphSize) {
		this.nodesInRoad.addAll( nodesInRoad );
		this. indexOfVisitedNode = new boolean[graphSize];
		
		for(int i = 0; i < indexOfVisitedNode.length; i++) {
			indexOfVisitedNode[i] = false;
		}		
		
	}
	
	public RoadBuilder() {}
	
	public void findEdgesAndCostForOneRoad() {
		Node nodeOne = new Node();
		Node nodeTwo = new Node();
		
		for(int i =0; i<nodesInRoad.size();i++) {
			nodeOne = nodesInRoad.get(i);

			if(i == (nodesInRoad.size() - 1) ) {
				nodeTwo = null;
			}else {
				nodeTwo = nodesInRoad.get(i+1);
			}
			
			if(nodeTwo!=null) {
				
				for(int j=0; j<nodeOne.edgeList.size();j++) {
					
					if(nodeOne.edgeList.get(j).from.equals(nodeTwo) 
						|| nodeOne.edgeList.get(j).to.equals(nodeTwo) ) {
						
						edgesInRoad.add(nodeOne.edgeList.get(j));
					}	
				}
			}
			indexOfVisitedNode[nodesInRoad.get(i).nodeIndex] = true;
		}
		countRoadCost();
	}

	public void countRoadCost() {	
		int drivingCost = 0;
		int handlingCost = nodesInRoad.get(0).loadCost;
		
		for(int i = 0; i < edgesInRoad.size(); i++) {
			
			drivingCost = drivingCost + edgesInRoad.get(i).cost;
		}
			
		for(int j = 1; j < nodesInRoad.size(); j++) {
			
			handlingCost = handlingCost + nodesInRoad.get(j).unloadCost;
		}	
		
		this.drivingCost = drivingCost;
		this.nodesHandlingCost = handlingCost;
	}
	
	public void printOneRoad() {
		
		for(int i = 0; i < nodesInRoad.size(); i++) {
			
			if( !(i == nodesInRoad.size()-1) ) {
				
			System.out.print("(" + nodesInRoad.get(i).nodeName + ")--"
					+ edgesInRoad.get(i).edgeName + "--");
			
			}else {
				System.out.print("(" + nodesInRoad.get(i).nodeName + ")");
			}
		}
	}

	public void printNodes() {
		for(int i = 0; i<nodesInRoad.size(); i++) {
			System.out.print( i + " = " + nodesInRoad.get(i).nodeName + ", ");
		}
	}

	public int countRoadOrder() {
		int roadOrderCount = 0;
		for(int i = 1; i< nodesInRoad.size(); i++) {
			roadOrderCount = roadOrderCount + nodesInRoad.get(i).order;
		}
		return roadOrderCount;
	}
}
