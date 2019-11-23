package Tower;

public class Tower6 extends Tower {

	public Tower6() {
		super();
		this.damage = 6;
		this.cost = 600;
		this.soldPrice = 1;
		this.range = 6;
		this.exist = true;
	}

	public  void upgrade() {
		this.damage *= 2;
	}
	
} 
