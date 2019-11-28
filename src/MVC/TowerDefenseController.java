package MVC;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Tower.*; //imports Tower package

public class TowerDefenseController {
	
	
	TowerDefenseModel model;
	TowerDefenseView view;
	
	public TowerDefenseController(TowerDefenseView towerDefenseView) {
		model = new TowerDefenseModel();
		createStage();
		this.view = towerDefenseView;
		if (towerDefenseView!=null) { //this if statement is for independent test of controller
			model.addObserver(towerDefenseView);
		}
	}

	private void createStage() {
		Scanner input = null;
		try {
			input = new Scanner(new File("map2.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int row = Integer.parseInt(input.nextLine());
		int col = Integer.parseInt(input.nextLine());
		model.setRow(row);
		model.setCol(col);
		model.init_Board();
		for(int i = 0; i < row; i++) {
			String[] tmpLine = input.nextLine().split(" ");
			for(int j = 0; j < col; j++) {
				TowerDefenseCell tmp = new TowerDefenseCell();
				model.set_objBoard_pos(i, j, tmp);
				model.set_intBoard_pos(i, j, Integer.parseInt(tmpLine[j]));
			}
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
		if (!is_tower_here(x, y) && model.get_intBoard_pos(row, col) == 0) {
			model.placeTower(row, col);
		} else {
			
		}
		model.setLCT(null);
	}
	
	public void sellTower(int x, int y) {
//		System.out.println("X: " + x);
//		System.out.println("Y: " + y);
		int col = x/50;
		int row = y/50;
//		System.out.println("ROW: " + row);
//		System.out.println("COL: " + col);
		if (getLCT() != null) {
//			System.out.println("!!!!!!!!!!!!!!!!!!!");
			model.sellTower(row, col);
		}
		
	}
	
	public void new_tower_to_LCT(String towerType) {
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
//		System.out.println("is tower here: here is" + model.get_intBoard_pos(row, col));
		if(model.get_intBoard_pos(row, col) != 2) {
			return false;
		}
		return true;
	}
	
	public Tower getTowerAt(int x, int y) {
		int col = x/50;
		int row = y/50;
		return (Tower) model.get_objBoard_pos(row, col).getFirst();
	}

	public int getRow() {
		return model.getRow();
	}
	public int getCol() {
		return model.getCol();
	}
	
	public void setLCT(Tower tower) {
		model.setLCT(tower);
	}
	public void setLCT_null() {
		model.setLCT(null);
	}
	public Tower getLCT() {
		return model.getLCT();
	}
	
	public int getBalance() {
		return model.getBalance();
	}
	
	public int[][] get_intBoard(){
		return model.get_intBoard();
	}

	

	
}
