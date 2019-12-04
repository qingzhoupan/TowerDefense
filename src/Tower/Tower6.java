package Tower;
 
import MVC.Point;

public class Tower6 extends Tower {

	public Tower6() {
		super();
		this.damage = 6;
		this.cost = 600;
		this.range = 6;
		this.exist = true;
		this.index = "6";
		this.name = "Tower 6";
	}

	public void upgrade() {
		this.damage *= 2;
	}

	// range ? // TODO
	public boolean inRange(Point point) {
		if (this.towerCOL == point.getCol() && this.towerROW == point.getRow() + 1) {
			return true;
		}
		return false;
	};
}
