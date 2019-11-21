package Enemies;

public class Enemy4 extends Enemies {

	public Enemy4() {
		super();
		this.hp = 4;
		this.cost = 4;
		this.speed = 4;
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
