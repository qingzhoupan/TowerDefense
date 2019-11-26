package MVC;

import java.util.Observable;

import Networking.TowerDefenseMessge;
import Tower.Tower;
import Stage.*;

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
	
	/**
	 * sell the tower and notify the observer
	 * @param row coordinate of row.
	 * @param col coordinate of col.
	 */
	public void sellTower(int row, int col) {
		if((this.balance - LAST_CLICKED_TOWER.getCost()) >= 0) { //checks negative balance
			Tower tower = (Tower)board[row][col];
			this.balance += tower.getSoldPrice();
			System.out.println("balance" + this.balance);
			TowerDefenseMessge message = new TowerDefenseMessge(row, col, 3);
			setChanged();
			notifyObservers(message);
		}
	}
	
	public void createPath() {
		Stage1 stage1 = new Stage1();
		board = stage1.createPath();
		for(int i = 0; i < ROW; i++) {
			for(int j = 0; j < COL; j++) {
				if(board[i][j] != null) {
					TowerDefenseMessge message = new TowerDefenseMessge(i, j, 4);
					System.out.println("model");
					setChanged();
					notifyObservers(message);
				}
				//System.out.print(board[i][j]);
			}
			//System.out.println();
		}
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
	
	public int getBalance() {
		return this.balance;
	}
	
	public Object[][] getBoard() {
		return this.board;
	}
	
	public void setLCT(Tower tower) {
		this.LAST_CLICKED_TOWER = tower;
	}
	

	public void boardInformation() {
		//tower placement
		//enemy real time location? 
	}


	

}
