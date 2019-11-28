package Tower;

public class Tower2 extends Tower {

	public Tower2() {
		super();
		this.damage = 2;
		this.cost = 200;
		this.range = 2;
		this.exist = true;
		this.index = "2";
		this.name = "Tower 2";
	}

	public  void upgrade() {
		this.damage *= 2;
	}

} 

