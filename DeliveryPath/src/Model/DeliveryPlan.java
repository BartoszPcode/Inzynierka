package Model;

import java.util.ArrayList;

public class DeliveryPlan {

	public ArrayList<RoadBuilder> roadsToDestination = new ArrayList<RoadBuilder>();
	public ArrayList<int[]> deliveredAmountInOneRoad = new ArrayList<int[]>();
	public ArrayList<boolean[]> whichNodeWasServedInRoad = new ArrayList<boolean[]>();
	public int deliveryPlanCost = 0;
	
	public DeliveryPlan() {}
	
	public DeliveryPlan(DeliveryPlan dp) {
		this.roadsToDestination = dp.roadsToDestination;
		this.deliveryPlanCost = dp.deliveryPlanCost;
	}
		
	public void addPlan(RoadBuilder road, RoadBuilder roadBack, Boolean wasVisitingOnWayBack,
						Boolean wasComingBackFromLastNode, int[] deliveredInOneRoad) {
		
		this.roadsToDestination.add(road);
		this.deliveredAmountInOneRoad.add(deliveredInOneRoad);
	}
	
	public void addPlan(RoadBuilder road, int[] deliveredInOneRoad, boolean[] whichNodeWasServedInRoad) {

		this.roadsToDestination.add(road);
		this.deliveredAmountInOneRoad.add(deliveredInOneRoad);
		this.whichNodeWasServedInRoad.add(whichNodeWasServedInRoad);
	}

	public void editPlan(int[] deliveredInOneRoad, boolean[] whichNodeWasServedInRoad) {

		this.deliveredAmountInOneRoad.add(deliveredInOneRoad);
		this.whichNodeWasServedInRoad.add(whichNodeWasServedInRoad);
		
	}
	
	public void deleteLastChoice() {

		if( roadsToDestination.size() > 0 ) {
			roadsToDestination.remove(roadsToDestination.size() - 1);
			deliveredAmountInOneRoad.remove(deliveredAmountInOneRoad.size() - 1);
			whichNodeWasServedInRoad.remove(whichNodeWasServedInRoad.size() - 1);
		}
	}
	

}
