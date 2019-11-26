package Stage;

import MVC.TowerDefenseModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stage1 {

	private TowerDefenseModel model;
	//private Object[][] pathBoard;
	
	public Stage1() {
		this.model = new TowerDefenseModel();
		
	}
	
	public Object[][] createPath() {
		Object[][] pathBoard = model.getBoard();
		for(int i = 0; i < model.getCol(); i++) {
			for(int j = 6; j < 9; j++) {
				pathBoard[i][j] = 4;
			}
		}
		return  pathBoard;
		
	}
	
}
