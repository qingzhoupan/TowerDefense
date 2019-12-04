package Networking;

public class TowerDefenseMessge {
	private int row;
	private int col;
	private int color; // 0 grass. 1 road. 2 tower. 3 enemy.
	 
	public TowerDefenseMessge(int row, int col, int color) {
		this.row = row;
		this.col = col;
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
}