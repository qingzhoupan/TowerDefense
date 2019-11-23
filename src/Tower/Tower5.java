package Tower;

public class Tower5 extends Tower {

	public Tower5() {
		super();
		this.damage = 5;
		this.cost = 500;
		this.soldPrice = 1;
		this.range = 5;
		this.exist = true;
	}

	public  void upgrade() {
		this.damage *= 2;
	}

}
