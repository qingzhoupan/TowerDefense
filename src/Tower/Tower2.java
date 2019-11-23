package Tower;

public class Tower2 extends Tower {

	public Tower2() {
		super();
		this.damage = 2;
		this.cost = 200;
		this.soldPrice = 1;
		this.range = 2;
		this.exist = true;
	}

	public  void upgrade() {
		this.damage *= 2;
	}

} 

