package Tower;

public abstract class Tower {
	protected int damage;
	protected int cost;
	protected int soldPrice = (int) 0.75 * cost;
	protected int range;
	protected boolean exist;
	
	public abstract void upgrade();
	
	//setters
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public void setSoldPrice(int soldPrice) {
		this.soldPrice = soldPrice;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	
	//getters
	public int getDamage() {
		return this.damage;
	}
	public int getCost() {
		return this.cost;
	}
	public int getSoldPrice() {
		return this.soldPrice;
	}
	public int getRange() {
		return this.range;
	}
	public boolean isExist() {
		return this.exist;
	}
	
} 
