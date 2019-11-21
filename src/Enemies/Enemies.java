package Enemies;

public abstract class Enemies {

	protected int hp;
	protected int cost;
	protected int speed;
	protected boolean alive;
	
	
	public abstract int getHP();
	
	public abstract int getCost();
	
	public abstract int getSpeed();
	
	public abstract boolean isAlive();
	
	
}
