package MVC;

import Tower.Tower;
import Tower.Tower1;

public class TowerDefenseController {
	
	
	
	TowerDefenseModel model;
	TowerDefenseView view;
	
	public TowerDefenseController(TowerDefenseView towerDefenseView) {
		model = new TowerDefenseModel();
		this.view = towerDefenseView;
		if (towerDefenseView!=null) { //this if statement is for independent test of controller
			model.addObserver(towerDefenseView);
		}
	}
	
	public int getRow() {
		return model.getRow();
	}
	
	public int getCol() {
		return model.getCol();
	}
	
	public void buyTower(Tower tower) {
		
	}
	
	public void placeTower(int x, int y) {
		int col = x/50;
		int row = y/50;
		model.placeTower(row, col);
		model.setLCT(null);
	}
	
	public void set_last_clicked_tower(String towerType) {
		if(towerType.equals("1")) {
			Tower1 tower1 = new Tower1();
			model.setLCT(tower1);
		}else if(towerType.equals("2")) {
			
		}else if(towerType.equals("3")) {
			
		}else if(towerType.equals("4")) {
			
		}else if(towerType.equals("5")) {
			
		}else if(towerType.equals("6")) {
			
		}
		
	}

	public boolean is_tower_here(int x, int y) {
		int col = x/50;
		int row = y/50;
		Object tmp = model.getPos(row, col);
		if(tmp == null) {
			return false;
		}
		return true;
	}
	
	public Tower getLCT() {
		return model.getLCT();
	}
	
	public int getBalance() {
		return model.getBalance();
	}

	
}
