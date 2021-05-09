package Model;
import java.util.ArrayList;

public class Graph {

	public ArrayList<Node> nodesList;
	public ArrayList<Edge> edgesList;
	public ArrayList<Node>[] connectionsList;
	public ArrayList<RoadBuilder> roads = new ArrayList<RoadBuilder>();
	public int[] indexOfShortestRoadBack;
	public int[] nodesOrders;
	
	public Graph(ArrayList<Node> nodesList, ArrayList<Edge> edgesList) {
		
		this.nodesList = nodesList;
		this.edgesList = edgesList;
		nodesOrders = new int[nodesList.size()];

		for(int i = 1; i < nodesOrders.length; i++) {
			nodesOrders[i] = nodesList.get(i).order ;
		}	
		
		initializeConnectionList( nodesList.size() ); 
		
		createConnections();
		findAllRoads();
	}
	
	@SuppressWarnings("unchecked")
	public void initializeConnectionList(int size) {
		connectionsList = new ArrayList[ size ];
		
		for(int i = 0; i < size; i++) 
		{ 
			connectionsList[i] = new ArrayList<>(); 
		} 	
	}
	
	public void createConnections() {
		
		for (int i = 0; i < nodesList.size(); i++) {

			Node nodeOne = nodesList.get(i);

			for (int j = 0; j < nodesList.size(); j++) {

				if (j != i) {
					
					Node nodeTwo = nodesList.get(j);
					
					for(int k = 0; k < nodeOne.edgeList.size(); k++) {
						
						Edge edgeOne = nodeOne.edgeList.get(k);
						
						for(int x = 0; x < nodeTwo.edgeList.size(); x++) {
							
							Edge edgeTwo = nodeTwo.edgeList.get(x);
							
							if( edgeOne.equals(edgeTwo) ) {
								
								if(!connectionsList[i].contains(nodeTwo) ) {
									
									connectionsList[i].add(nodeTwo);
									
									nodesList.get(j).edgeList.get(x).editEdge(nodeOne, nodeTwo);
								}	
							}
						}
					}
				}
			}
			
			nodesList.get(i).nodeIndex = i;
		}
	}
			
	private void printAllPathsUtil(Node nodeOne, Node nodeTwo, boolean[] isVisited, 
									ArrayList<Node> localPathList) {
		
		int nodeIndex = nodesList.indexOf(nodeOne);
		isVisited[nodeIndex] = true;

		if ( nodeOne.equals(nodeTwo) ) {	
			
			RoadBuilder roadbuilder = new RoadBuilder(localPathList, nodesList.size());
			roads.add(roadbuilder);	
			isVisited[nodeIndex] = false;
			
			return;
		}

		ArrayList<Node> connectionsInNodeOne = connectionsList[nodeIndex];

		for ( int k = 0;k < connectionsInNodeOne.size(); k++ ) {
			
			Node checkNextNode = connectionsInNodeOne.get(k);		
			int nodeNumberInList = nodesList.indexOf(checkNextNode);
			
			if( !isVisited[nodeNumberInList] ) {
				
				localPathList.add(checkNextNode);
				
				printAllPathsUtil(checkNextNode, nodeTwo, isVisited, localPathList);
	
				localPathList.remove(checkNextNode);
			}
		}	
		isVisited[nodeIndex] = false;	
	}
	
	public void printSinglePath(ArrayList<Node> localPathList) {

		for(int i = 0; i < localPathList.size(); i++) {
			
			System.out.print(localPathList.get(i).nodeName + ", ");		
		}
		
		System.out.println("");		
	}
	
	public void findEdgesForAllRoads() {
		
		for(int z = 0; z < roads.size(); z ++ ) {

			roads.get(z).findEdgesAndCostForOneRoad();
		}
	}
	
	public void printAllRoadsToDestination() {
		
		for(int i = 0; i < roads.size(); i++) {
			System.out.print(" " +i + "  ===>  ");
			roads.get(i).printOneRoad();
			System.out.print("  <==== Road cost (only for edges and without coming back) = " + roads.get(i).drivingCost);
			System.out.println(" ");
		}
		
		System.out.println(" ===================================== ");
		System.out.println("Informations:");
		System.out.println("Nodes: " + nodesList.size());
		System.out.println("All possible roads: " + roads.size());
	}
	
	public void checkConnectionsBetweenNodes() {
		
		System.out.println("Sprawdzenie polaczen: ");	
		for(int i = 0; i < connectionsList.length; i++) {
			ArrayList<Node> lista = connectionsList[i];
			
			System.out.print("(" + i + ") ===> ");
			
			for(int j = 0; j < lista.size(); j++ ) {
				
				System.out.print(" " + lista.get(j).nodeName );
			}
			System.out.println("");	
		}
	}

	public void findAllRoads() {
		
		int graphSize = nodesList.size();
		indexOfShortestRoadBack = new int[graphSize];
		indexOfShortestRoadBack[0] = 0;
		
		int indexOfRoad = -1;
		int temporaryNumberOfRoads = 0;
		int roadCost = 0;

		for(int i = 1; i < graphSize; i++) {
			
			temporaryNumberOfRoads = roads.size();			

			int nodesCount = nodesList.size();			
			boolean[] isVisited = new boolean[nodesCount]; 			
			ArrayList<Node> pathList = new ArrayList<>();
			
			pathList.add( nodesList.get(0) ); 
			
			printAllPathsUtil(nodesList.get(0), nodesList.get(i), isVisited, pathList);	
				
			for (int j = temporaryNumberOfRoads; j < roads.size(); j++) {
				
				roads.get(j).findEdgesAndCostForOneRoad();	
				
				if(roadCost == 0) {
					
					roadCost = roads.get(j).drivingCost;
					indexOfRoad = j;
					
				}else if(roadCost > roads.get(j).drivingCost){
					
					roadCost = roads.get(j).drivingCost;
					indexOfRoad = j;
				}
			}
			
			indexOfShortestRoadBack[i] = indexOfRoad;
			roadCost = 0;
		}
	}
}
