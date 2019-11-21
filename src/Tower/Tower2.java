package Tower;

public class Tower2 extends Tower {

	public Tower2() {
		super();
		this.damage = 2;
		this.range = 2;
		this.cost = 2;
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
