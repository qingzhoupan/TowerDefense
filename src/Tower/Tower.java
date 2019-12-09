package Tower;
 
import java.io.Serializable;

import MVC.Point;

public abstract class Tower implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7990071241630315383L;
	
	protected int damage;
	protected int cost;
	protected int range;
	protected boolean exist;
	protected int index;
	protected String name;
	protected int towerROW;
	protected int towerCOL;
	private String id;
	protected String description;
	protected String direction;
	
	
	public abstract void upgrade();
	public abstract boolean inRange(Point point);
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getSoldPrice() {
		return (int) (0.75 * cost);
	}
	
	public int getTowerROW() {
		return towerROW;
	}

	public void setTowerROW(int towerROW) {
		this.towerROW = towerROW;
	}

	public int getTowerCOL() {
		return towerCOL;
	}

	public void setTowerCOL(int towerCOL) {
		this.towerCOL = towerCOL;
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
	public int getIndex() {
		return index;
	}
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
} 
