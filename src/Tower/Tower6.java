package Tower;

public class Tower6 extends Tower {

	public Tower6() {
		super();
		this.damage = 6;
		this.cost = 600;
		this.range = 6;
		this.exist = true;
		this.name = "Tower 6";
	}

	public  void upgrade() {
		this.damage *= 2;
	}
	
} 
