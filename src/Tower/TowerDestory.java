package Tower;

public class TowerDestory extends Tower {

	public TowerDestory() {
		super();
		this.damage = 0;
		this.cost = 0;
		this.range = 0;
		this.exist = false;
		this.name = "Tower Destoried";
	}

	public  void upgrade() {
		this.damage *= 2;
	}
	
} 
