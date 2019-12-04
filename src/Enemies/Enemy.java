package Enemies;
 
import MVC.Point;

public abstract class Enemy extends Point {

	private String id;
	
	protected int hp;
	protected int cost;
	protected int speed;
	protected boolean alive;
	
	
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isAlive() {
		if(this.hp <= 0) {
			this.alive = false;
		}
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void beingAttacked(int damage) {
		this.hp = this.hp - damage;
	}
	
	
	
	
}
