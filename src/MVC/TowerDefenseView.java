package MVC;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Networking.TowerDefenseMessge;
import Tower.Tower;
import Tower.Tower1;
import Tower.Tower2;
import Tower.Tower3;
import Tower.Tower4;
import Tower.Tower5;
import Tower.Tower6;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
		controller = new TowerDefenseController(this);
	}
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Tower Defense");
		BorderPane window = new BorderPane();
		Scene scene = new Scene(window, 850, 750);
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
			//if(controller.is_tower_here(x, y) == false) { //no tower at location
			if(controller.getLCT() != null) { //last clicked not null
				controller.placeTower(x, y);
				}
			//}
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
		Text moneyTitle = new Text("BALANCE");
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
		//main tower Vbox
		VBox mainTowerBox = new VBox(30);
		
		List<Tower> towerList = new ArrayList<Tower>();
		Tower t = new Tower1();
		towerList.add(t);
		t = new Tower2();
		towerList.add(t);
		t = new Tower3();
		towerList.add(t);
		t = new Tower4();
		towerList.add(t);
		t = new Tower5();
		towerList.add(t);
		t = new Tower6();
		towerList.add(t);
		
		for (int i = 0; i < towerList.size(); i++) {
			final String index = i+1+"";
			Tower tower = towerList.get(i);
			VBox towerBox = new VBox(5);
			Button towerButton = new Button(tower.getName());
			Label towerCost = new Label("Cost: "+tower.getCost());
			towerBox.getChildren().addAll(towerButton, towerCost);	
			mainTowerBox.getChildren().addAll(towerBox);
			towerButton.setOnAction(e -> {	
				controller.set_last_clicked_tower(index);
			});
		}
		
		VBox towerBox = new VBox(5);
		Button towerButton = new Button("sell");
		Label towerCost = new Label("get 75%");
		towerBox.getChildren().addAll(towerButton, towerCost);	
		mainTowerBox.getChildren().addAll(towerBox);
		towerButton.setOnAction(e -> {	
			controller.set_last_clicked_tower("sell");
		});
		vBox.getChildren().add(mainTowerBox);
	}
		/*
		//individual tower Vboxes
		VBox towerBox1 = new VBox(5);
		VBox towerBox2 = new VBox(5);
		VBox towerBox3 = new VBox(5);
		VBox towerBox4 = new VBox(5);
		VBox towerBox5 = new VBox(5);
		VBox towerBox6 = new VBox(5);
		
		//each tower button
		Button towerButton1 = new Button("Tower1");
		Button towerButton2 = new Button("Tower2");
		Button towerButton3 = new Button("Tower3");
		Button towerButton4 = new Button("Tower4");
		Button towerButton5 = new Button("Tower5");
		Button towerButton6 = new Button("Tower6");
		
		//each label cost
		Label towerCost1 = new Label("Cost: 100");
		Label towerCost2 = new Label("Cost: 200");
		Label towerCost3 = new Label("Cost: 300");
		Label towerCost4 = new Label("Cost: 400");
		Label towerCost5 = new Label("Cost: 500");
		Label towerCost6 = new Label("Cost: 600");
		
		//adds tower button and cost label to a vbox
		towerBox1.getChildren().addAll(towerButton1, towerCost1);	
		towerBox2.getChildren().addAll(towerButton2, towerCost2);
		towerBox3.getChildren().addAll(towerButton3, towerCost3);
		towerBox4.getChildren().addAll(towerButton4, towerCost4);
		towerBox5.getChildren().addAll(towerButton5, towerCost5);
		towerBox6.getChildren().addAll(towerButton6, towerCost6);
		mainTowerBox.getChildren().addAll(towerBox1, towerBox2, towerBox3, towerBox4, towerBox5, towerBox6);
		
		//event handlers for each tower button
		towerButton1.setOnAction(e -> {	
			controller.set_last_clicked_tower("1");
		});
		towerButton2.setOnAction(e -> {	
			controller.set_last_clicked_tower("2");
		});
		towerButton3.setOnAction(e -> {	
			controller.set_last_clicked_tower("3");
		});
		towerButton4.setOnAction(e -> {	
			controller.set_last_clicked_tower("4");
		});
		towerButton5.setOnAction(e -> {	
			controller.set_last_clicked_tower("5");
		});
		towerButton6.setOnAction(e -> {	
			controller.set_last_clicked_tower("6");
		});
		vBox.getChildren().add(mainTowerBox);
	}
	*/
	
	
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
		/*
		Image image = new Image("file:image/456.jpg");
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitHeight(45);
		imageView.setFitWidth(45);
		gridPane.add(imageView, message.getCol(), message.getRow());
		*/
		if (message.getColor()==2) {
			Rectangle rec = new Rectangle();
			rec.setFill(Color.RED);
			rec.setWidth(45);
			rec.setHeight(45);
			gridPane.add(rec, message.getCol(), message.getRow());
		}else if(message.getColor()==3) {
			Rectangle rec = new Rectangle();
			rec.setFill(Color.GREEN);
			rec.setWidth(45);
			rec.setHeight(45);
			gridPane.add(rec, message.getCol(), message.getRow());
		}
//		System.out.println(Integer.toString(controller.getBalance()));
		moneyBalance.setText(Integer.toString(controller.getBalance()));
		System.out.println("col: " + message.getCol());
		System.out.println("row: " + message.getRow());
		
	}

	

}
