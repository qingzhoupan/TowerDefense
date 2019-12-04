package Tower;
 
import MVC.Point;

public class TowerDestory extends Tower {
	private int x;
	private int y;
	private int cost;

	public int getSellX() {
		return x;
	}

	public int getSellY() {
		return y;
	}

	public TowerDestory(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setCost(int credit) {
		this.cost = credit;
	}

	public int getCost() {
		return this.cost;
	}

	@Override
	public void upgrade() {

	}

	// WHAT ? // TODO
	public boolean inRange(Point point) {
		if (this.towerCOL == point.getCol() && this.towerROW == point.getRow() + 1) {
			return true;
		}
		return false;
	};

}
