package Tower;

public class Tower3 extends Tower {

	public Tower3() {
		super();
		this.damage = 3;
		this.range = 3;
		this.cost = 3;
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
