package Tower;

import MVC.Point;

public class Tower5 extends Tower {
 
	public Tower5() {
		super();
		this.damage = 5;
		this.cost = 500;
		this.range = 5;
		this.exist = true;
		this.index = 5;
		this.name = "Tower 5";
	}

	public void upgrade() {
		this.damage *= 2;
	}

	// range ? // TODO
	public boolean inRange(Point point) {
		if (point.getCol()==null) {
			return false;
		}
		if (this.towerCOL == point.getCol() && this.towerROW == point.getRow() + 2) {
			return true;
		}else if(this.towerCOL == point.getCol() + 2 && this.towerROW == point.getRow()) {
			return true;
		}else if(this.towerCOL == point.getCol() - 2 && this.towerROW == point.getRow()) {
			return true;
		}else if(this.towerCOL == point.getCol() && this.towerROW == point.getRow() - 2) {
			return true;
		}
		return false;
	};
}
