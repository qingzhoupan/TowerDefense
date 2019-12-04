package Tower;

import Enemies.Enemy;
import MVC.Point;

public class Tower2 extends Tower {

	public Tower2() {
		super();
		this.damage = 2;
		this.cost = 200;
		this.range = 2;
		this.exist = true;
		this.index = "2";
		this.name = "Tower 2";
	}

	public void upgrade() {
		this.damage *= 2;
	}

	// range down
	public boolean inRange(Point point) {
		if (point.getCol()==null) {
			return false;
		}
		
		if (this.towerCOL==point.getCol()&&this.towerROW+1==point.getRow()) {
			System.out.println("return true");
			return true;
		}
		return false;
	};
}
