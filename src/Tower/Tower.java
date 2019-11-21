package Tower;

public abstract class Tower {
	private int x;
	private int y;
	private int damage;
	private int cost;
	private int soldPrice = (int) 0.75 * cost;
	private int range;
	private boolean exist;
	
	public Tower(int x, int y, int damage, int cost, int soldPrice, int range, 
			boolean exist) {
		super();
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.cost = cost;
		this.soldPrice = soldPrice;
		this.range = range;
		this.exist = exist;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(int soldPrice) {
		this.soldPrice = soldPrice;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}
	
	
}
