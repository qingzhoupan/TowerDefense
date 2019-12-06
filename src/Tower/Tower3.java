package Tower;

import MVC.Point;

public class Tower3 extends Tower {
 
	public Tower3() {
		super();
		this.damage = 3;
		this.cost = 300;
		this.range = 3;
		this.exist = true;
		this.index = 3;
		this.name = "Tower 3";
	}

	public  void upgrade() {
		this.damage *= 2;
	}

	// range left
	public boolean inRange(Point point) {
		/*System.out.println("this.towerCOL"+this.towerCOL);
		System.out.println("point.getCol()"+point.getCol());
		System.out.println("&this.towerROW"+this.towerROW);
		System.out.println("point.getRow()+1"+(point.getRow()+1));*/
		if (point.getCol()==null) {
			return false;
		}
		
		if (this.towerCOL - 2 == point.getCol() && this.towerROW == point.getRow()) {
			System.out.println("return true");
			return true;
		}
		return false;
	};
}