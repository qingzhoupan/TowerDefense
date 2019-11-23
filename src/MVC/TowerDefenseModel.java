package MVC;

import java.util.Observable;

import Tower.Tower;

public class TowerDefenseModel extends Observable{
	
	private final int ROW = 15; //this is temporary
	private final int COL = 15; //this is temporary
	
	private Object board [][];
	private Tower LAST_CLICKED_TOWER; 
	private int balance;
	
	public TowerDefenseModel() {
		board = new Object [ROW][COL];
		this.balance = 1000;

	}
	
	public int getRow() {
		return this.ROW;
	}
	
	public int getCol() {
		return this.COL;
	}
	
	public Object getPos(int row, int col) {
		return board[row][col];
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

	/**
	 * Places tower and notifies the observer if the cost is in the budget.
	 * @param row coordinate of row.
	 * @param col coordinate of col.
	 */
	public void placeTower(int row, int col) {
		if((this.balance - LAST_CLICKED_TOWER.getCost()) >= 0) { //checks negative balance
			board[row][col] = LAST_CLICKED_TOWER;
			System.out.println(LAST_CLICKED_TOWER.getCost());
			this.balance -= LAST_CLICKED_TOWER.getCost();
			System.out.println("balance" + this.balance);
			TowerDefenseMessge message = new TowerDefenseMessge(row, col, 2);
			setChanged();
			notifyObservers(message);
		}
	}
	

}
