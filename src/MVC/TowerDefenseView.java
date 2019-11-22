package MVC;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TowerDefenseView extends Application implements Observer{

	public TowerDefenseController controller;
	
	public TowerDefenseView() {
		TowerDefenseModel model = new TowerDefenseModel();
		model.addObserver(this);
		controller = new TowerDefenseController();
	}
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Tower Defense");
		BorderPane window = new BorderPane();
		Scene scene = new Scene(window, 1000, 1000);
		createTopContainer(window);
		//createMainContainer(window);
		createRightContainer(window);
		stage.setScene(scene);
		stage.show();
	}
	
	
	/**
	 * Top container has the menu drop down. Pause and speed up are disabled
	 * until new game is clicked.
	 * @param window sets the menu bar in window.
	 */
	private void createTopContainer(BorderPane window) {
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		MenuItem newGame = new MenuItem("New Game");
		MenuItem pause = new MenuItem("Pause");
		MenuItem speedUp = new MenuItem("Speed++");
		pause.setDisable(true); //disable until new game is clicked
		speedUp.setDisable(true); // disable until new game is clicked
		
		//event handler
		newGame.setOnAction(event->{
			startNewGame(pause, speedUp); //parameters used when new game
		});
		
		
		menu.getItems().addAll(newGame, pause, speedUp);
		menuBar.getMenus().add(menu);
		VBox menuBox = new VBox(menuBar);
		window.setTop(menuBox);
	}
	
	
	private void createMainContainer(BorderPane window) {
		
	}
	
	
	private void createRightContainer(BorderPane window) {
		VBox vBox = new VBox();
		HBox moneyBox = new HBox();
		TextFlow moneyDisplay = new TextFlow();
		Text moneyTitle = new Text("Balance: ");
		
		
		//controller.getStartingBalance 
		Text moneyBalance = new Text("1000");
	
		moneyBox.getChildren().addAll(moneyTitle, moneyBalance);
		
	
	
		vBox.getChildren().add(moneyBox);
		window.setRight(vBox);
	}
	
	
	//Event handlers methods
	private void startNewGame(MenuItem pause, MenuItem speedUp) {
		pause.setOnAction(event->{
			pauseGame();
		});
		speedUp.setOnAction(event->{
			speedUpGame();
		});
		
		
	}
	
	private void pauseGame() {
		
	}
	
	private void speedUpGame() {
		
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	

}
