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
		this.index = 1;
		this.name = "Machine Gun";
		this.description = "";
		this.direction = "Up(North) 2 Meters";
	}

	public  void upgrade() {
		this.damage *= 2;
	}	
	
	// range up
	public boolean inRange(Point point) {
		/*System.out.println("this.towerCOL"+this.towerCOL);
		System.out.println("point.getCol()"+point.getCol());
		System.out.println("&this.towerROW"+this.towerROW);
		System.out.println("point.getRow()+1"+(point.getRow()+1));*/
		if (point.getCol()==null) {
			return false;
		}
		
		if (this.towerCOL == point.getCol() && this.towerROW - 2 == point.getRow()) {
			System.out.println("return true");
			return true;
		}
		return false;
	};
	
} 
