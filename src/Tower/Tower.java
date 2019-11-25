package Tower;

public abstract class Tower {
	protected int damage;
	protected int cost;
	//protected int soldPrice = (int) 0.75 * cost;
	protected int range;
	protected boolean exist;
	protected String name;
	
	public abstract void upgrade();
	
	public int getSoldPrice() {
		return (int) (0.75 * cost);
	}
	//setters
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//getters
	public int getDamage() {
		return this.damage;
	}
	public int getCost() {
		return this.cost;
	}
	
	public int getRange() {
		return this.range;
	}
	public boolean isExist() {
		return this.exist;
	}
	public String getName() {
		return name;
	}
	
} 
