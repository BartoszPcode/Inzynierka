package Model;

import java.util.ArrayList;

import cloner.ObjectCloner;

public class DeliveryPlanFinder {
	
	public ArrayList<RoadBuilder> siftedRoads;	
	public ArrayList<RoadBuilder> roads;
	public int[] indexOfShortestRoadBack;
	public int[] nodesOrders;
	public static int counter = 0;
	public int carMaxLoad = 33;
	public int allPossiblePlansNumber = 0;
	int graphSize;
	
	@SuppressWarnings("rawtypes")
	public ObjectCloner cloner = new ObjectCloner();
	public DeliveryPlan bestPlan = null;
	
	public DeliveryPlanFinder( ArrayList<RoadBuilder> roads, int[] indexOfShortestRoadBack, 
								int[] nodesOrders, int carMaxLoad, int graphSize ) {
		
		this.roads = roads;
		this.indexOfShortestRoadBack = indexOfShortestRoadBack;
		this.nodesOrders = nodesOrders;
		this.carMaxLoad = carMaxLoad;	
		this.graphSize = graphSize;		
		this. siftedRoads = new  ArrayList<RoadBuilder>();
		
		siftRoads();

		DeliveryPlan p = new DeliveryPlan();
		
		finder(this.nodesOrders, p, siftedRoads);
	}	

	@SuppressWarnings("unchecked")
	public void finder(int[] nodesOrders, DeliveryPlan plan, 
						ArrayList<RoadBuilder> sRoads) {
		
		int[] localOrders = (int[]) cloner.deepClone(nodesOrders);
		DeliveryPlan localPlan = (DeliveryPlan) cloner.deepClone(plan);

		boolean[] servedNodes = new boolean[localOrders.length];
		
		for(int i = 1; i < localOrders.length; i++) {
			if(localOrders[i] > 0) {
				servedNodes[i] = false;
			}else {
				servedNodes[i] = true;
			}
		}	
		
		if( isItTheEnd(servedNodes) ){
			
			if(bestPlan == null) {
				
				bestPlan = (DeliveryPlan) cloner.deepClone(localPlan);		
				countPlanCost(bestPlan);
			}else {
				
				countPlanCost(localPlan);
				if(bestPlan.deliveryPlanCost > localPlan.deliveryPlanCost ) {
					
					bestPlan = (DeliveryPlan) cloner.deepClone(localPlan);	
				}
			}
			
			this.allPossiblePlansNumber++;
			return;		
		}else {
			ArrayList<RoadBuilder> possibleRoads = sifter(servedNodes, sRoads );
			
			for(int j = 0; j < possibleRoads.size(); j++) {
				int[] temporaryOrders = (int[]) cloner.deepClone(localOrders);
				
				RoadBuilder chosenRoad = possibleRoads.get(j);
				int carLoad = this.carMaxLoad;

				boolean[] visitedInThisRoad = chosenRoad.indexOfVisitedNode;
				boolean[] wasProviding = new boolean[visitedInThisRoad.length];
				boolean wasThisRoadUseful = false;
				int[] deliveredAmount = new int[visitedInThisRoad.length];
				

				for(int x = 1; x < visitedInThisRoad.length; x++) {

					if(servedNodes[x] == false && visitedInThisRoad[x] == true) {
						
						if(carLoad > 0 ) {
							
							wasProviding[x] = true;
							wasThisRoadUseful = true;
							if(carLoad >= temporaryOrders[x]) {
								
								deliveredAmount[x] = temporaryOrders[x];
								carLoad = carLoad - temporaryOrders[x];
								temporaryOrders[x] = 0;
							}else {		
								
								deliveredAmount[x] = carLoad;
								temporaryOrders[x] = temporaryOrders[x] - carLoad;
								carLoad = 0;
							}
						}
					}
				}							
				
				if(wasThisRoadUseful) {
					
					localPlan.roadsToDestination.add(chosenRoad);
					localPlan.editPlan(deliveredAmount, wasProviding);
									
					finder(temporaryOrders, localPlan, possibleRoads);
					
					localPlan.deleteLastChoice();
				}
			}
		}
	}

	public int countMaxNumberOfRoadsInSinglePlan(int[] nodesOrders, int carMaxLoad) {
		int maxRoadsNumber = 0;
		int howMany = 0;
		int mod = 0;
		
		for(int i = 1; i < nodesOrders.length; i++) {

			howMany = nodesOrders[i]/carMaxLoad;
			mod =  nodesOrders[i]%carMaxLoad;
			
			if(mod != 0) {
				howMany++;
			}

			maxRoadsNumber = maxRoadsNumber + howMany;
			
		}
		return maxRoadsNumber;
	}
	
	//wybiera drogi w ktorych jest przynajmniej jeden nie obsluzony wierzcholek
	public ArrayList<RoadBuilder> sifter(boolean[] alreadyVisitedNodes,
							ArrayList<RoadBuilder> previouslyAvailableRoads) {

		ArrayList<RoadBuilder> availableRoads = new ArrayList<RoadBuilder>();
		boolean[] posibbleVisitedNodes;

		for (int i = 0; i < previouslyAvailableRoads.size(); i++) {
			posibbleVisitedNodes = previouslyAvailableRoads.get(i).indexOfVisitedNode;

			boolean canChooseThatRoad = false;

			for (int j = 1; j < posibbleVisitedNodes.length; j++) {

				if (alreadyVisitedNodes[j] == false && posibbleVisitedNodes[j] == true) {
					canChooseThatRoad = true;
					break;
				}
			}

			if (canChooseThatRoad) {

				availableRoads.add(previouslyAvailableRoads.get(i));
			}
		}
		return availableRoads;
	}
	
	
	public ArrayList<RoadBuilder> sifter(int[] neededOrder, ArrayList<RoadBuilder> previouslyAvailableRoads) {

		ArrayList<RoadBuilder> availableRoads = new ArrayList<RoadBuilder>();
		boolean[] posibbleVisitedNodes;

		for (int i = 0; i < previouslyAvailableRoads.size(); i++) {
			posibbleVisitedNodes = previouslyAvailableRoads.get(i).indexOfVisitedNode;
			//System.out.println("===="+posibbleVisitedNodes[2]);

			boolean canChooseThatRoad = false;

			//j=1 bo pomijamy magazyn ktory jest zawsze odwiedzony
			for (int j = 1; j < posibbleVisitedNodes.length; j++) {

				//if(posibbleVisitedNodes[j] == true && posibbleVisitedNodes[j] == alreadyVisitedNodes[j] ) {
				//wybiera droge w ktorej jest przynajmniej jeden nie obsluzony wierzcholek
				if (neededOrder[j] > 0 && posibbleVisitedNodes[j] == true) {
					canChooseThatRoad = true;
					break;
				}
			}

			//System.out.println(i + " Can i choose that road? " + canChooseThatRoad );
			if (canChooseThatRoad) {

				availableRoads.add(previouslyAvailableRoads.get(i));
			}
		}
		return availableRoads;
	}

	//opuszcza te drogi w  ktorych  nie jestesmy dostarczyc towaru dla wszystkich wierzcholkow - 
	//maksymalnie do ostatniego wierzcholka mozemy dostarczyc czesciowy towar	
	public void siftRoads() {
		
		for(int i = 0; i < roads.size(); i++) {

			int nodesNumber = 0;
			int load = this.carMaxLoad;
			int count = 0;
			
			nodesNumber = roads.get(i).nodesInRoad.size();
			
			for(int j =1; j<nodesNumber; j++) {

				load = load - roads.get(i).nodesInRoad.get(j).order;
					
				if( load <= 0 ) {
					count--;
				}
			}
			
			if( count == 0 || count == -1 ) {
				
				siftedRoads.add( roads.get(i) );
			}
		}
	}
	
	//sprawdza czy juz wszystkim dostarczono towar
	
	public boolean isItTheEnd(boolean[] visitedNodes) {
		boolean end = true;
		
		for(int i = 1; i < visitedNodes.length; i++) {
			if(visitedNodes[i] == false) {
				end = false;
			}	
		}
		return end;
	}
	
	//sprawdza czy juz wszystkim dostarczono towar na podstawie tablicy potrzebnych jeszcze towarow
	
	public boolean isItTheEnd(int[] neededOrded) {
		boolean end = true;
		
		for(int i = 1; i < neededOrded.length; i++) {
			if(neededOrded[i] > 0) {
				end = false;
				break;
			}	
		}
		return end;
	}
	
	public void countPlanCost(DeliveryPlan dp) {
		
		int planCost = 0;
		
		for(int i = 0; i < dp.roadsToDestination.size(); i++) {
			
			RoadBuilder rb = dp.roadsToDestination.get(i);		
			int roadDrivingCost = rb.drivingCost;
			
			int loadCost = rb.nodesInRoad.get(0).loadCost;
			boolean[] whichNodeWasServedInRoad = dp.whichNodeWasServedInRoad.get(i);
			int unloadCost = 0;
			int indexOfLastNode = rb.nodesInRoad.get(rb.nodesInRoad.size()-1).nodeIndex;
			int comeBackCost = roads.get(indexOfShortestRoadBack[indexOfLastNode]).drivingCost;			
			
			for(int j = 1; j < rb.nodesInRoad.size(); j++) {
				
				int nodeIndex = rb.nodesInRoad.get(j).nodeIndex;
				if(whichNodeWasServedInRoad[nodeIndex] == true) {
					unloadCost = unloadCost + rb.nodesInRoad.get(j).unloadCost;
				}
			}	
			planCost = planCost + unloadCost + loadCost + comeBackCost + roadDrivingCost;
		}
		dp.deliveryPlanCost = planCost;
	}

	

	public void printPlan(DeliveryPlan plan) {
		
		System.out.println("========== Best delivery plan  ==========");
		
		for(int i = 0; i < plan.roadsToDestination.size(); i++) {
			RoadBuilder rb = plan.roadsToDestination.get(i);
			System.out.print("Road " + i + ": " );
			for(int j = 0; j < rb.nodesInRoad.size(); j++) {
				
				Node node = rb.nodesInRoad.get(j);
				
				System.out.print("[" + node.nodeName + "," +node.nodeIndex + "]");
				
				if(j!=0) {
					System.out.print("(" + plan.deliveredAmountInOneRoad.get(i)[node.nodeIndex] + ")" );
				}
				
				if(j < rb.nodesInRoad.size()-1) {
					Edge edge = rb.edgesInRoad.get(j);
					System.out.print("---" + edge.edgeName + "(" + edge.cost + ")---");
				}
			}

			//droga powrotna
			int lastNodeIndex = rb.nodesInRoad.get(rb.nodesInRoad.size()-1).nodeIndex;
			RoadBuilder back = roads.get(indexOfShortestRoadBack[lastNodeIndex]);
		
			int indexOfEdge = back.edgesInRoad.size() - 1;
			System.out.print("  |coming back| ");
			for(int x = back.nodesInRoad.size()-2; x >= 0; x--) {
				Node nodeB = back.nodesInRoad.get(x);

				if(x != 1) {
					Edge edge = back.edgesInRoad.get(indexOfEdge);
					indexOfEdge--;
					System.out.print("---" + edge.edgeName + "(" + edge.cost + ")---");
				}
				
				System.out.print("[" + nodeB.nodeName + "," + nodeB.nodeIndex + "]");
			}

			if( i != plan.roadsToDestination.size()-1) {
				System.out.println("\n");
			}
		}
		System.out.println("\n============ Plan cost " + plan.deliveryPlanCost + " =============");
	}	
}
