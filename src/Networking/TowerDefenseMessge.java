package Networking;

import Tower.*;
import Enemies.*;

public class TowerDefenseMessge {
	
	public static final int TYPE_CHANGE_MAP = 10;
	public static final int TYPE_LOAD = 11;
	
	private int row;
	private int col;
	private int color; // 0 grass. 1 road. 2 tower. 3 enemy.
	private Tower tower;
	private Enemy enemy;
	 
	public TowerDefenseMessge(int row, int col, int color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}
	
	public TowerDefenseMessge(Tower tower, Enemy enemy, int color) {
		this.tower = tower;
		this.enemy = enemy;
		this.color = color;
	}


	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getColor() {
		return color;
	}

	public Tower getTower() {
		return tower;
	}

	public Enemy getEnemy() {
		return enemy;
	}
}