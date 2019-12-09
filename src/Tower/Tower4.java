package Tower;

import MVC.Point;

public class Tower4 extends Tower {

	public Tower4() {
		super();
		this.damage = 4;
		this.cost = 400; 
		this.range = 4;
		this.exist = true;
		this.index = 4;
		this.name = "Rocket Launcher";
		this.description = "";
		this.direction = "Right(East) 2 Meters";
	}

	public void upgrade() {
		this.damage *= 2;
	}

	// range right
	public boolean inRange(Point point) {
		/*System.out.println("this.towerCOL"+this.towerCOL);
		System.out.println("point.getCol()"+point.getCol());
		System.out.println("&this.towerROW"+this.towerROW);
		System.out.println("point.getRow()+1"+(point.getRow()+1));*/
		if (point.getCol()==null) {
			return false;
		}
		
		if (this.towerCOL + 2 == point.getCol() && this.towerROW == point.getRow()) {
			System.out.println("return true");
			return true;
		}
		return false;
	};
}