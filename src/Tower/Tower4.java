package Tower;

public class Tower4 extends Tower {

	public Tower4() {
		super();
		this.damage = 4;
		this.cost = 400;
		this.soldPrice = 1;
		this.range = 4;
		this.exist = true;
	}

	public  void upgrade() {
		this.damage *= 2;
	}

} 