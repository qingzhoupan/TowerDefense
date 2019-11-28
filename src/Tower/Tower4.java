package Tower;

public class Tower4 extends Tower {

	public Tower4() {
		super();
		this.damage = 4;
		this.cost = 400;
		this.range = 4;
		this.exist = true;
		this.index = "4";
		this.name = "Tower 4";
	}

	public  void upgrade() {
		this.damage *= 2;
	}

} 