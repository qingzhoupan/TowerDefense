package MVC;

public class Point {
	private Integer col;
	private Integer row;
	
	
	public Point(Integer col, Integer row) {
		super();
		this.col = col;
		this.row = row;
	}
	
	public Point() {
		super();
	}
	
	public boolean equalPoint(Point other) {
		if(col==null||row==null) {
			return false;
		}else {
			return col.equals(other.getCol())&&row.equals(other.getRow());
		}
	}
	
	public void move(Point other) {
		this.setCol(other.getCol());
		this.setRow(other.getRow());
	}
	
	public double getX(){
		return this.col*TowerDefenseView.HEIGHT+TowerDefenseView.HEIGHT/2;
	}
	public double getY(){
		return this.row*TowerDefenseView.HEIGHT+TowerDefenseView.HEIGHT/2;
	}
	
	public Integer getCol() {
		return col;
	}
	public void setCol(Integer col) {
		this.col = col;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	
	
	
}
