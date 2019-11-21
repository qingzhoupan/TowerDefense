package Enemies;

public class Enemy1 extends Enemies {

	
	public Enemy1() {
		super();
		this.hp = 1;
		this.cost = 1;
		this.speed = 1;
		this.alive = true;
	}
	
	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	
	
	
	
}
