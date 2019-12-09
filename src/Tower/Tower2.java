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
		this.index = 2;
		this.name = "Sniper";
		this.description = "";
		this.direction = "Down(South) 2 Meters";
	}

	public void upgrade() {
		this.damage *= 2;
	}

	// range down
	public boolean inRange(Point point) {
		/*System.out.println("this.towerCOL"+this.towerCOL);
		System.out.println("point.getCol()"+point.getCol());
		System.out.println("&this.towerROW"+this.towerROW);
		System.out.println("point.getRow()+1"+(point.getRow()+1));*/
		if (point.getCol()==null) {
			return false;
		}
		
		if (this.towerCOL == point.getCol() && this.towerROW + 2 == point.getRow()) {
			System.out.println("return true");
			return true;
		}
		return false;
	};
}
