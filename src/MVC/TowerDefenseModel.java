package MVC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;

import Enemies.Enemy;
import Networking.TowerDefenseMessge;
import Tower.Tower;

public class TowerDefenseModel extends Observable implements Serializable {

	private int imagePos = 225;

	private int ROW; // this is temporary
	private int COL; // this is temporary
	private List<Point> path = new ArrayList<>();
	private List<List<Enemy>> wave = new ArrayList<>();
	private ArrayList<Tower> towerList = new ArrayList<>();

	private Map<Enemy, Point> map = new HashMap<>();

	private TowerDefenseCell objBoard[][];
	private int intBoard[][]; // [0-Grass] [1-Road] [2-Tower] [3-Enemy]

	private Tower LAST_CLICKED_TOWER;
	private int balance;

	private ArrayList<Integer> pathCoord = new ArrayList<Integer>();

	public boolean changingMap = false;

	public List<Enemy> currentEnemyList;

	public ArrayList<Integer> getPathCoord() {
		return pathCoord;
	}

	public int imagePos() {
		return imagePos;
	}

	public void update_imagePos() {
		imagePos -= 1;

	}

	public TowerDefenseModel() {
		objBoard = new TowerDefenseCell[ROW][COL];
		intBoard = new int[ROW][COL];
		this.balance = 1000;

	}

	public void setPathCoord(int a) {
		this.pathCoord.add(a);
	}

	public ArrayList<Tower> getTowerList() {
		return this.towerList;
	}

	/**
	 * Places tower and notifies the observer if the cost is in the budget.
	 * 
	 * @param row
	 *            coordinate of row.
	 * @param col
	 *            coordinate of col.
	 */
	public void placeTower(int row, int col) {
		TowerDefenseMessge message = null;
		if ((this.balance - LAST_CLICKED_TOWER.getCost()) >= 0) { // checks negative balance
			LAST_CLICKED_TOWER.setTowerROW(row);
			LAST_CLICKED_TOWER.setTowerCOL(col);
			// give a unique id
			LAST_CLICKED_TOWER.setId(UUID.randomUUID().toString());
			towerList.add(LAST_CLICKED_TOWER);
			objBoard[row][col].add(LAST_CLICKED_TOWER);
			intBoard[row][col] = 2;
			this.balance -= LAST_CLICKED_TOWER.getCost();
			message = new TowerDefenseMessge(row, col, LAST_CLICKED_TOWER.getIndex());
		}else {
			message = new TowerDefenseMessge(0, 0, 404);
		}
		setChanged();
		notifyObservers(message);
	}

	public void loadTower() {
		for (Tower tower : towerList) {
			TowerDefenseMessge message = new TowerDefenseMessge(tower.getTowerROW(), tower.getTowerCOL(),
					tower.getIndex());
			setChanged();
			notifyObservers(message);
		}
	}

	/**
	 * sell the tower and notify the observer
	 * 
	 * @param row
	 *            coordinate of row.
	 * @param col
	 *            coordinate of col.
	 */
	public void sellTower(int row, int col) {
		this.balance += LAST_CLICKED_TOWER.getCost();
		for (Tower tower : towerList) {
			if (LAST_CLICKED_TOWER.getId().equals(tower.getId())) {
				towerList.remove(LAST_CLICKED_TOWER);
			}
			break;
		}
		LAST_CLICKED_TOWER = null;
		TowerDefenseMessge message = new TowerDefenseMessge(row, col, 0);
		setChanged();
		notifyObservers(message);
	}

	public void fps() {
		for (Tower tower : towerList) {
			if (tower.getName().equals("Tower 6")) {
				addBalance(30);
			}
		}
		setChanged();
		notifyObservers();
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

	public void init_Board() {
		this.objBoard = new TowerDefenseCell[ROW][COL];
		this.intBoard = new int[ROW][COL];

	}

	public List<Point> getPath() {
		return path;
	}

	public void setPath(List<Point> path) {
		this.path = path;
	}

	public List<List<Enemy>> getWave() {
		return wave;
	}

	public void setWave(List<List<Enemy>> wave) {
		this.wave = wave;
	}

	public Map<Enemy, Point> getMap() {
		return map;
	}

	public void setMap(Map<Enemy, Point> map) {
		this.map = map;
	}

	public void addBalance(int credit) {
		this.balance += credit;
		TowerDefenseMessge message = new TowerDefenseMessge(0, 0, -1);
		setChanged();
		notifyObservers(message);
	}

	public void attackAction(Tower tower, Enemy enemy) {
		TowerDefenseMessge message = new TowerDefenseMessge(tower, enemy, 99);
		setChanged();
		notifyObservers(message);

	}

	public void changeMap() {
		changingMap = true;
		TowerDefenseMessge message = new TowerDefenseMessge(0, 0, TowerDefenseMessge.TYPE_CHANGE_MAP);
		setChanged();
		notifyObservers(message);
	}

	public void load() {
		TowerDefenseMessge message = new TowerDefenseMessge(0, 0, TowerDefenseMessge.TYPE_LOAD);
		setChanged();
		notifyObservers(message);
	}

}
