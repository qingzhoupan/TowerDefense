package MVC;

import java.util.Observable;

import Networking.TowerDefenseMessge;
import Tower.Tower;

public class TowerDefenseModel extends Observable{
	
	private int ROW; //this is temporary
	private int COL; //this is temporary

	private TowerDefenseCell objBoard [][];
	private int intBoard[][];				// [0-Grass] [1-Road] [2-Tower] [3-Enemy]
	
	private Tower LAST_CLICKED_TOWER; 
	private int balance;
	
	
	public TowerDefenseModel() {
		objBoard =   new TowerDefenseCell [ROW][COL];
		intBoard = new int [ROW][COL];
		this.balance = 1000;
	}
	
	/**
	 * Places tower and notifies the observer if the cost is in the budget.
	 * @param row coordinate of row.
	 * @param col coordinate of col.
	 */
	public void placeTower(int row, int col) {
		if((this.balance - LAST_CLICKED_TOWER.getCost()) >= 0) { //checks negative balance
			objBoard[row][col].add(LAST_CLICKED_TOWER);
			intBoard[row][col] = 2;
			this.balance -= LAST_CLICKED_TOWER.getCost();
			TowerDefenseMessge message = new TowerDefenseMessge(row, col, 2);
			setChanged();
			notifyObservers(message);
		}
	}
	
	/**
	 * sell the tower and notify the observer
	 * @param row coordinate of row.
	 * @param col coordinate of col.
	 */
	public void sellTower(int row, int col) {
		if((this.balance - LAST_CLICKED_TOWER.getCost()) >= 0) { //checks negative balance
			objBoard[row][col].setToEmpty();
			intBoard[row][col] = 0;
			this.balance += LAST_CLICKED_TOWER.getCost();
			LAST_CLICKED_TOWER = null;
			System.out.println("balance" + this.balance);
			System.out.println("sellTower" + row +" "+col);
			TowerDefenseMessge message = new TowerDefenseMessge(row, col, 0);
			setChanged();
			notifyObservers(message);
		}
	}

	public int getRow() {
		return ROW;
	}
	public void setRow(int row) {
		this.ROW = row;
	}

	
	public int getCol() {
		return COL;
	}
	public void setCol(int col) {
		this.COL = col;
	}
	
	
	// objBoard setters and getters
	public Object[][] get_objBoard() {
		return this.objBoard;
	}
	public TowerDefenseCell get_objBoard_pos(int row, int col) {
		return objBoard[row][col];
	}
	public void set_objBoard_pos(int row, int col, TowerDefenseCell cell) {
		objBoard[row][col] = cell;
	}
	
	
	// intBoard setters and getters
	public int[][] get_intBoard() {
		return this.intBoard;
	}
	public int get_intBoard_pos(int row, int col) {
		return intBoard[row][col];
	}
	public void set_intBoard_pos(int row, int col, int i) {
		intBoard[row][col] = i;
	}
	
	
	
	public Tower getLCT() {	
		return this.LAST_CLICKED_TOWER;
	}
	public void setLCT(Tower tower) {	
		this.LAST_CLICKED_TOWER = tower;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	
	
	
	

	public void boardInformation() {
		//tower placement
		//enemy real time location? 
	}

	public void init_Board() {
		this.objBoard = new TowerDefenseCell[ROW][COL];	
		this.intBoard = new int[ROW][COL];
		
	}

}
