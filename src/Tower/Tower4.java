package Tower;

public class Tower4 extends Tower {

	public Tower4() {
		super();
		this.damage = 4;
		this.range = 4;
		this.cost = 4;
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
