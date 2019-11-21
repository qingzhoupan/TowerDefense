package Tower;

public abstract class Tower {
	protected int x;
	protected int y;
	protected int damage;
	protected int cost;
	protected int soldPrice = (int) 0.75 * cost;
	protected int range;
	protected boolean exist;
	
	public abstract void upgrade();
	
	//setters
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
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
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getDamage() {
		return damage;
	}
	public int getCost() {
		return cost;
	}
	public int getSoldPrice() {
		return soldPrice;
	}
	public int getRange() {
		return range;
	}
	public boolean isExist() {
		return exist;
	}
	
} 

