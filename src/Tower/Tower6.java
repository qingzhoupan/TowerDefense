package Tower;

public class Tower6 extends Tower {

	public Tower6() {
		super();
		this.damage = 6;
		this.range = 6;
		this.cost = 6;
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
