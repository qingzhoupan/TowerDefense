package MVC;

import Tower.Tower;

public class TowerDefenseController {
	
	public static final int ROW = 50; //this is temporary
	public static final int COL = 50; //this is temporary
	
	TowerDefenseModel model;
	TowerDefenseView view;
	
	public TowerDefenseController(TowerDefenseView towerDefenseView) {
		model = new TowerDefenseModel();
		this.view = towerDefenseView;
		if (towerDefenseView!=null) { //this if statement is for independent test of controller
			model.addObserver(towerDefenseView);
		}
	}
	
	public void buyTower(Tower tower) {
		
	}
	
	public void placeTower(int x, int y) {
		
	}
	
}
