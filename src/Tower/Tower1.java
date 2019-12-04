package Tower;

import Enemies.Enemy;
import MVC.Point;

public class Tower1 extends Tower {


	public Tower1() {
		super();
		this.damage = 1;
		this.cost = 100;
		this.range = 1;
		this.exist = true;
		this.index = "1";
		this.name = "Tower 1";
	}

	public  void upgrade() {
		this.damage *= 2;
	}	
	
	// range up
	public boolean inRange(Point point) {
		if (point.getCol()==null) {
			return false;
		}
		
		if (this.towerCOL==point.getCol()&&this.towerROW-1==point.getRow()) {
			System.out.println("return true");
			return true;
		}
		return false;
	};
	
} 
