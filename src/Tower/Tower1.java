package Tower;

public class Tower1 extends Tower {


	public Tower1() {
		super();
		this.damage = 1;
		this.cost = 100;
		this.range = 1;
		this.exist = true;
		this.index = "1";
		this.name = "Tower 1";
	}

	public  void upgrade() {
		this.damage *= 2;
	}	
} 
