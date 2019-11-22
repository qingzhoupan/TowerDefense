package MVC;

import java.util.Observable;

public class TowerDefenseModel extends Observable{
	
	private Object board [][];
	
	public TowerDefenseModel() {
		board = new Object [TowerDefenseController.ROW][TowerDefenseController.COL];
	}
	
	public void boardInformation() {
		//tower placement
		//enemy real time location? 
	}
	

}
