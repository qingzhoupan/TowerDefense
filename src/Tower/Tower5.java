package Tower;

public class Tower5 extends Tower {

	public Tower5() {
		super();
		this.damage = 5;
		this.range = 5;
		this.cost = 5;
	}
	
	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public int getRange() {
		return range;
	}

	@Override
	public int getCost() {
		return cost;
	}

}
