package Enemies;

public class Enemy3 extends Enemies {

	public Enemy3() {
		super();
		this.hp = 3;
		this.cost = 3;
		this.speed = 3;
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
