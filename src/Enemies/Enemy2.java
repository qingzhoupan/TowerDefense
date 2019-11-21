package Enemies;

public class Enemy2 extends Enemies {

	
	public Enemy2() {
		super();
		this.hp = 2;
		this.cost = 2;
		this.speed = 2;
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
