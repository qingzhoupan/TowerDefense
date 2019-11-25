package MVC;
import Tower.*; //imports Tower package
//import Tower.Tower;
//import Tower.Tower1;
//import Tower.Tower2;
//import Tower.Tower3;
//import Tower.Tower4;
//import Tower.Tower5;
//import Tower.Tower6;

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

	/**
	 * Calls model to place tower then sets last clicked tower to null.
	 * @param x coordinate in pixels
	 * @param y coordinate in pixel
	 */
	public void placeTower(int x, int y) {
		int col = x/50;
		int row = y/50;
		if (is_tower_here(x, y)) {
			if (!model.getLCT().isExist()) {
				model.sellTower(row, col);
			}
		}else {
			model.placeTower(row, col);
		}
		model.setLCT(null);
	}
	
	
	public void set_last_clicked_tower(String towerType) {
		if(towerType.equals("1")) {
			Tower tower1 = new Tower1();
			model.setLCT(tower1);
		}else if(towerType.equals("2")) {
			Tower tower2 = new Tower2();
			model.setLCT(tower2);
		}else if(towerType.equals("3")) {
			Tower tower3 = new Tower3();
			model.setLCT(tower3);
		}else if(towerType.equals("4")) {
			Tower tower4 = new Tower4();
			model.setLCT(tower4);
		}else if(towerType.equals("5")) {
			Tower tower5 = new Tower5();
			model.setLCT(tower5);
		}else if(towerType.equals("6")) {
			Tower tower6 = new Tower6();
			model.setLCT(tower6);
		}else if(towerType.equals("sell")) {
			Tower tower = new TowerDestory();
			model.setLCT(tower);
		}
		
	}

	/**
	 * Checks to see if a position on the grid is taken. For placement purposes
	 * @param x coordinate in pixels.
	 * @param y coordinate in pixels.
	 * @return boolean representing if position is taken.
	 */
	public boolean is_tower_here(int x, int y) {
		int col = x/50;
		int row = y/50;
		Object tmp = model.getPos(row, col);
		if(tmp == null) {
			return false;
		}
		return true;
	}
	
	public void buyTower(Tower tower) {
		
	}

	public int getRow() {
		return model.getRow();
	}
	
	public int getCol() {
		return model.getCol();
	}
	
	public Tower getLCT() {
		return model.getLCT();
	}
	
	public int getBalance() {
		return model.getBalance();
	}

	
}
