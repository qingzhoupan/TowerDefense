package Tower;

public class Tower3 extends Tower {

	public Tower3() {
		super();
		this.damage = 3;
		this.cost = 300;
		this.range = 3;
		this.exist = true;
		this.name = "Tower 3";
	}

	public  void upgrade() {
		this.damage *= 2;
	}

}