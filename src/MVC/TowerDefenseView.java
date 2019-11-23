package MVC;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TowerDefenseView extends Application implements Observer{

	public TowerDefenseController controller;
	private GridPane gridPane = new GridPane();
	private Label moneyBalance;
	
	public TowerDefenseView() {
		TowerDefenseModel model = new TowerDefenseModel();
		model.addObserver(this);
		controller = new TowerDefenseController(this);//wei added parameter this for controller
	}
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Tower Defense");
		BorderPane window = new BorderPane();
		Scene scene = new Scene(window, 850, 750);
//		createTopContainer(window);
		//createMainContainer(window);
		createBoard(window);
		createRightContainer(window);
		stage.setScene(scene);
		stage.show();
	}

	
	/**
	 * Creates 50x50 grid which represents the 2D array. Each position
	 * corresponds to the array.
	 * @param window: BorderPane to set grid
	 */
	private void createBoard(BorderPane window) {
		for(int i = 0; i < controller.getCol(); i++) {
			for(int j = 0; j < controller.getRow(); j++) {
				Rectangle rec = new Rectangle();
				rec.setFill(Color.GREEN);
				rec.setWidth(45);
				rec.setHeight(45);
				gridPane.add(rec, i, j);
			}
		}
		gridPane.setOnMouseClicked(e -> {
			int x = (int) e.getX();
			int y = (int) e.getY();
			if(controller.is_tower_here(x, y) == false) { //no tower at location
				if(controller.getLCT() != null) { //last clicked not null
					controller.placeTower(x, y);
				}
			}
		});
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		window.setCenter(gridPane);
		
	}


	/**
	 * Creates the menu, money, and tower vBoxes that's displayed on the right.
	 * @param window BorderPane to add vBoxes
	 */
	private void createRightContainer(BorderPane window) {
		VBox vBox = new VBox(50);
		createMenu(vBox); // menu VBox
		createMoney(vBox); // money VBox
		createTowers(vBox); // tower VBox
		window.setRight(vBox);
	}
	
	/**
	 * Creates the menu drop down for new game, pause, and speed
	 * @param vBox Vertical box to add children.
	 */
	private void createMenu(VBox vBox) {
		// Menu VBox
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
		vBox.getChildren().add(menuBox);
	}
	
	/**
	 * Creates the total balance for the game.
	 * @param vBox Vertical box to add children
	 */
	private void createMoney(VBox vBox) {
		VBox moneyBox = new VBox(5);
		TextFlow moneyDisplay = new TextFlow();
		Text moneyTitle = new Text("Balance");
		//controller.getStartingBalance 
		moneyBalance = new Label(Integer.toString(controller.getBalance()));
		moneyBox.getChildren().addAll(moneyTitle, moneyBalance);
		vBox.getChildren().add(moneyBox);
	}
	
	/**
	 * Creates tower boxes with event handlers.
	 * @param vBox Vertical box to add children
	 */
	private void createTowers(VBox vBox) {
		VBox towerBox = new VBox(50);
		Button tower1 = new Button("Tower1");
		Button tower2 = new Button("Tower2");
		Button tower3 = new Button("Tower3");
		Button tower4 = new Button("Tower4");
		Button tower5 = new Button("Tower5");
		Button tower6 = new Button("Tower6");
		towerBox.getChildren().add(tower1);
		towerBox.getChildren().add(tower2);
		towerBox.getChildren().add(tower3);
		towerBox.getChildren().add(tower4);
		towerBox.getChildren().add(tower5);
		towerBox.getChildren().add(tower6);
		tower1.setOnAction(e -> {	
			controller.set_last_clicked_tower("1");
		});
		tower2.setOnAction(e -> {	
			controller.set_last_clicked_tower("2");
		});
		tower3.setOnAction(e -> {	
			controller.set_last_clicked_tower("3");
		});
		tower4.setOnAction(e -> {	
			controller.set_last_clicked_tower("4");
		});
		tower5.setOnAction(e -> {	
			controller.set_last_clicked_tower("5");
		});
		tower6.setOnAction(e -> {	
			controller.set_last_clicked_tower("6");
		});
		vBox.getChildren().add(towerBox);
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
		TowerDefenseMessge message = (TowerDefenseMessge) arg;
		Rectangle rec = new Rectangle();
		rec.setFill(Color.RED);
		rec.setWidth(45);
		rec.setHeight(45);
		gridPane.add(rec, message.getCol(), message.getRow());
//		System.out.println(Integer.toString(controller.getBalance()));
		moneyBalance.setText(Integer.toString(controller.getBalance()));
		System.out.println("col: " + message.getCol());
		System.out.println("row: " + message.getRow());
		
	}

	

}
