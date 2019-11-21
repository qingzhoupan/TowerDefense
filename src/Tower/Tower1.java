package Tower;

public class Tower1 extends Tower {
	
	public Tower1() {
		super();
		this.damage = 1;
		this.range = 1;
		this.cost = 1;
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
