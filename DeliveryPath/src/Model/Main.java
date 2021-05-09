package Model;

import test.GraphsContainer;

public class Main {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		GraphsContainer graphContainer = new GraphsContainer();	
		Graph graph = graphContainer.graphMain5();
		//Graph graph = graphContainer.graph1();		
		int carMaxLoad = 33;		
		long beforeFindingBestPlan = System.currentTimeMillis();

		DeliveryPlanFinder dpf = new DeliveryPlanFinder(graph.roads, graph.indexOfShortestRoadBack, 
											graph.nodesOrders, carMaxLoad, graph.nodesList.size() );

		long tEnd = System.currentTimeMillis();
			
		printInformations(graph, carMaxLoad, dpf);
		
		dpf.printPlan(dpf.bestPlan);
				
		double findingAllPossibleRoadsTime = (beforeFindingBestPlan - start) / 1000.0;
		double findingBestPlanTime = (tEnd - beforeFindingBestPlan) / 1000.0;
		double compiledTime = (tEnd - start) / 1000.0;

		System.out.println("\nFinding all possible roads time " +findingAllPossibleRoadsTime + "s");
		System.out.println("Finding best plan time " +findingBestPlanTime + "s" );
		System.out.println("Compiled in " +compiledTime + "s" );
	}

	
	

	public static void printInformations(Graph graph, int carMaxLoad, DeliveryPlanFinder dpf) {
		System.out.println("--------------- Informations ---------------");
		System.out.println("Nodes number: " + graph.nodesList.size() );
		System.out.println("Edges number: " + graph.edgesList.size() );
		System.out.println("Warehouse load cost: " + graph.nodesList.get(0).loadCost );
		System.out.println("Possible roads number: " + graph.roads.size() );
		System.out.println("Possible delivery plans number: " + dpf.allPossiblePlansNumber );
		System.out.println("Car maximum load: " + carMaxLoad);
		
		System.out.print("Nodes: ");
		for(int i = 0; i<graph.nodesList.size(); i++){
			System.out.print("[" + graph.nodesList.get(i).nodeName + "] ");
		}
		System.out.println("");
		
		System.out.print("Nodes orders: ");
		for(int i = 0; i<graph.nodesOrders.length; i++){
			System.out.print("[" + graph.nodesOrders[i] + "] ");
		}
		System.out.println("");
		
		System.out.print("Nodes unload costs: ");
		for(int i = 0; i<graph.nodesList.size(); i++){
			System.out.print("<" + graph.nodesList.get(i).unloadCost + "> ");
		}
		System.out.println("");
		
		System.out.print("Edges: ");
		for(int i = 0; i<graph.edgesList.size(); i++){
			System.out.print("{" + graph.edgesList.get(i).edgeName+ "} ");
		}
		System.out.println("");
		
		System.out.print("Edges costs: ");
		for(int i = 0; i<graph.edgesList.size(); i++){
			System.out.print("(" + graph.edgesList.get(i).cost+ ") ");
		}
		System.out.println("");
		
		System.out.println("--------------- Informations ---------------");
		
		
		System.out.println("\n--------------- L E G E N D ---------------");
		System.out.print("---Edge name(edge cost)---:");

			System.out.print("   ---" + graph.edgesList.get(0).edgeName + "(" +graph.edgesList.get(0).cost + ")---");
		System.out.println("");
		
		System.out.print("[Node name, node index](delivered order): ");
			System.out.print("[" + graph.nodesList.get(0).nodeName + "," +graph.nodesList.get(0).nodeIndex + "]");
			System.out.print("("+graph.nodesList.get(0).order + ")  ");
		System.out.println("\n--------------- L E G E N D ---------------\n");
	}
	
}
