package MVC;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TowerDefenseView extends Application implements Observer{

	public TowerDefenseController tower;
	
	public TowerDefenseView() {
		TowerDefenseModel model = new TowerDefenseModel();
		model.addObserver(this);
		tower = new TowerDefenseController();
	}
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Tower Defense");
		BorderPane window = new BorderPane();
		Scene scene = new Scene(window, 1000, 1000);
		createTopContainer(window);
		//createMainContainer(window);
		//createRightContainer(window);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	private void createTopContainer(BorderPane window) {
		HBox hBox = new HBox();
		Button newGame = new Button("New Game");
		Button pause = new Button("Pause");
		Button speedUp = new Button("Speed++");
		hBox.getChildren().add(newGame);
		hBox.getChildren().add(pause);
		hBox.getChildren().add(speedUp);
		window.setRight(hBox);
		
	}
	
	
	private void createMainContainer(BorderPane window) {
		VBox vBox = new VBox();
	}
	
	
	private void createRightContainer(BorderPane window) {
		
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	

}
