package Tower;

public class Tower3 extends Tower {

	public Tower3() {
		super();
		this.damage = 3;
		this.cost = 300;
		this.soldPrice = 1;
		this.range = 3;
		this.exist = true;
	}

	public  void upgrade() {
		this.damage *= 2;
	}

}