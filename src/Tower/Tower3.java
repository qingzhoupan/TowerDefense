package Tower;

public class Tower3 extends Tower {

	public Tower3(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.damage = 1;
		this.cost = 1;
		this.soldPrice = 1;
		this.range = 1;
		this.exist = true;
	}

	public  void upgrade() {
		this.damage *= 2;
	}

}