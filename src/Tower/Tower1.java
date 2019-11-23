package Tower;

public class Tower1 extends Tower {


	public Tower1() {
		super();
		this.damage = 1;
		this.cost = 100;
		this.soldPrice = 1; //Whats this for?
		this.range = 1;
		this.exist = true;
	}

	public  void upgrade() {
		this.damage *= 2;
	}
	
	
	
	
	
} 
