package Tower;

public class Tower5 extends Tower {

	public Tower5() {
		super();
		this.damage = 5;
		this.cost = 500;
		this.range = 5;
		this.exist = true;
		this.name = "Tower 5";
	}

	public  void upgrade() {
		this.damage *= 2;
	}

}
