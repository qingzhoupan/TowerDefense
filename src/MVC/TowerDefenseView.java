package MVC;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javax.swing.JOptionPane;

import Enemies.Enemy;
import Enemies.Enemy1;
import Enemies.Enemy2;
import Enemies.Enemy3;
import Enemies.Enemy4;
import Networking.TowerDefenseMessge;
import Tower.Tower;
import Tower.Tower1;
import Tower.Tower2;
import Tower.Tower3;
import Tower.Tower4;
import Tower.Tower5;
import Tower.Tower6;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.TowerDefense;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class TowerDefenseView extends Application implements Observer {

	public static final double HEIGHT = 50;

	public TowerDefenseController controller;
	private GridPane gridPane = new GridPane();
	private Tower sellTower = null;
	private Label moneyBalance;
	private static Timeline timeline;
	private PathTransition pathTransition;
	private Group root;
	private BorderPane window;
	private AudioStream audios;

	public TowerDefenseView() {
		init();

	}

	public void init() {
		controller = new TowerDefenseController(this);
		playSound("start.wav");
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Tower Defense");
		window = new BorderPane();
		root = new Group(window);
		createBoard(window);
		createRightContainer(window);
		Scene scene = new Scene(root, 850, 750);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Creates 50x50 grid which represents the 2D array. Each position corresponds
	 * to the array.
	 * 
	 * @param window:
	 *            BorderPane to set grid
	 */
	private void createBoard(BorderPane window) {
		for (int i = 0; i < controller.getCol(); i++) {
			for (int j = 0; j < controller.getRow(); j++) {

				if (controller.get_imagePos() > 0) {
					Image image = new Image(
							"file:image/map" + TowerDefense.LEVEL + "/" + controller.get_imagePos() + ".jpg");
					ImageView imageView = new ImageView();
					imageView.setImage(image);
					imageView.setFitHeight(50);
					imageView.setFitWidth(50);

					gridPane.add(imageView, j, i);
					controller.update_imagePos();
				}

			}
		}
		gridPane.setOnMouseClicked(e -> {
			int x = (int) e.getX();
			int y = (int) e.getY();
			if (!controller.is_tower_here(x, y)) {
				if (controller.getLCT() != null) {
					controller.placeTower(x, y);
				} else {
					// lct is null, nothing happen
					playSound("404.wav");
				}
			} else {
				if (controller.getLCT() == null) {
					for (Tower tower : controller.getTowerList()) {
						if (tower.getTowerCOL() == x / 50 && tower.getTowerROW() == y / 50) {
							sellTower = tower;
						}
					}
					Tower temp = controller.getTowerAt(x, y);
					int credit = temp.getSoldPrice();
					sellTower.setCost(credit);
					controller.setLCT(sellTower);
				} else {
					controller.setLCT_null();
				}
			}
		});
		gridPane.setVgap(0.2);
		gridPane.setHgap(0.2);
		window.setCenter(gridPane);

	}

	/**
	 * Creates the menu, money, and tower vBoxes that's displayed on the right.
	 * 
	 * @param window
	 *            BorderPane to add vBoxes
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
	 * 
	 * @param vBox
	 *            Vertical box to add children.
	 */
	private void createMenu(VBox vBox) {
		// Menu VBox
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		MenuItem newGame = new MenuItem("New Game");
		MenuItem startGame = new MenuItem("Start Game");
		MenuItem pause = new MenuItem("Pause");
		MenuItem speedUp = new MenuItem("Speed++");
		MenuItem save = new MenuItem("Save");
		MenuItem load = new MenuItem("Load");

		pause.setDisable(true); // disable until new game is clicked
		speedUp.setDisable(true); // disable until new game is clicked

		
		save.setOnAction(event -> {
			this.controller.save();
		});
		load.setOnAction(event -> {
			this.controller.load();
		});
		// event handler
		startGame.setOnAction(event -> {
			startNewGame(startGame, pause, speedUp); // parameters used when new game
		});
		menu.getItems().addAll(newGame, startGame, pause, speedUp, save, load);
		menuBar.getMenus().add(menu);
		VBox menuBox = new VBox(menuBar);
		vBox.getChildren().add(menuBox);
	}

	/**
	 * Creates the total balance for the game.
	 * 
	 * @param vBox
	 *            Vertical box to add children
	 */
	private void createMoney(VBox vBox) {
		VBox moneyBox = new VBox(5);
		// TextFlow moneyDisplay = new TextFlow();
		Text moneyTitle = new Text("BALANCE");
		moneyBalance = new Label(Integer.toString(controller.getBalance()));
		moneyBox.getChildren().addAll(moneyTitle, moneyBalance);
		vBox.getChildren().add(moneyBox);
	}

	/**
	 * Creates tower boxes with event handlers.
	 * 
	 * @param vBox
	 *            Vertical box to add children
	 */
	private void createTowers(VBox vBox) {
		// main tower Vbox
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
			final String index = i + 1 + "";
			Tower tower = towerList.get(i);
			VBox towerBox = new VBox(5);
			Button towerButton = new Button(tower.getName());
			Label towerCost = new Label("Cost: " + tower.getCost());
			towerBox.getChildren().addAll(towerButton, towerCost);
			mainTowerBox.getChildren().addAll(towerBox);
			towerButton.setOnAction(e -> {
				playSound("Click.wav");
				controller.new_tower_to_LCT(index);
			});
		}

		VBox towerBox = new VBox(5);
		Button sellButton = new Button("sell");
		Label sellReturn = new Label("get 75%");
		towerBox.getChildren().addAll(sellButton, sellReturn);
		mainTowerBox.getChildren().addAll(towerBox);
		sellButton.setOnMouseClicked(e -> {
			int x = sellTower.getTowerCOL();
			int y = sellTower.getTowerROW();
			controller.sellTower(x, y);

		});

		VBox testBox = new VBox(5);
		Button testButton = new Button("test");
		towerBox.getChildren().addAll(testButton);
		mainTowerBox.getChildren().addAll(testBox);
		testButton.setOnMouseClicked(e -> {
			this.fps();
		});

		vBox.getChildren().add(mainTowerBox);
	}

	// Event handlers methods
	private void startNewGame(MenuItem startGame, MenuItem pause, MenuItem speedUp) {
		pause.setDisable(false);
		speedUp.setDisable(false);
		startTimer();
		pause.setOnAction(event -> {
			pauseGame();
		});
		speedUp.setOnAction(event -> {
			speedUpGame();
		});

	}

	private void pauseGame() {
		timeline.pause();
	}

	private void speedUpGame() {
		timeline.stop();
		timeline = new Timeline();
		addKeyFrame(100);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);
		timeline.play();
	}

	private void startTimer() {
		if(TowerDefense.LEVEL == 1 || TowerDefense.LEVEL == 2) {
			playBackground("1and2.wav");
		}else {
			playBackground("3.wav");
		}
		double speed = 1000;
		timeline = new Timeline();
		addKeyFrame(speed);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);
		timeline.play();
	}

	private void addKeyFrame(double speed) {
		KeyFrame keyFrame = new KeyFrame(Duration.millis(speed), e -> fps());
		KeyFrame refrashEndStatus = new KeyFrame(Duration.millis(speed), e -> refrashEndStatus());
		timeline.getKeyFrames().add(keyFrame);
		timeline.getKeyFrames().add(refrashEndStatus);
	}

	private void fps() {
		controller.fps();
	}

	private void refrashEndStatus() {
		controller.refrashEndStatus();
	}

	Map<String, ImageView> circleMap = new HashMap<>();

	@Override
	public void update(Observable o, Object arg) {
		TowerDefenseModel model = (TowerDefenseModel) o;
		if (arg != null) {
			TowerDefenseMessge message = (TowerDefenseMessge) arg;
			if (message.getColor() == 0) { // Grass
				sellTower(message);
			} else if (message.getColor() > 0 && message.getColor() < 7) { // Tower
				placeTower(message);
			} else if (message.getColor() == -1) { // < 0 update balance
				
			} else if (message.getColor() == 99) { // attack action
				attackAction(message);
			}else if (message.getColor() == 404) { // attack action
				playSound("404.wav");
			}else if (message.getColor() == TowerDefenseMessge.TYPE_CHANGE_MAP) { // attack action
				// alert 'continue?'
				Platform.runLater(() -> {
					AudioPlayer.player.stop(audios);
					playSound("victory.wav");
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Congratulations!");
					alert.setHeaderText("You did a great a job!");
					alert.setContentText("Do you want to move to next stage?");
					Optional<ButtonType> result = alert.showAndWait();
					TowerDefenseView.timeline.stop();
//					ObservableList<KeyFrame> keyFrames = TowerDefenseView.timeline.getKeyFrames();
//					for (int i = 0; i < keyFrames.size(); i++) {
//					}
					if (result.get() == ButtonType.OK) {
						// ... user chose OK
						TowerDefense.LEVEL++;
						if(TowerDefense.LEVEL > 3) {
							Alert al = new Alert(AlertType.INFORMATION);
							al.setTitle("Thank You!");
							al.setHeaderText(null);
							al.setContentText("Thank you for choosing Tower Defense Game!");
							al.showAndWait();
							System.exit(1);
						}
						init();
						createBoard(window);
					} else {
						// ... user chose CANCEL or closed the dialog
						Alert al = new Alert(AlertType.INFORMATION);
						al.setTitle("Thank You!");
						al.setHeaderText(null);
						al.setContentText("Thank you for choosing Tower Defense Game!");
						al.showAndWait();
						System.exit(1);
					}
				});
			}
		} else {
			moveEnemy(model);
		}
		moneyBalance.setText(Integer.toString(controller.getBalance()));  
	}

	private void moveEnemy(TowerDefenseModel model) {
		/*
		 * move enemies on the view based on the data from model
		 */
		Map<Enemy, Point> map = model.getMap();
		ArrayList<Enemy> removelist = new ArrayList<>();
		// enemy in view
		for (Entry<Enemy, Point> element : map.entrySet()) {
			Enemy key = element.getKey();
			Point value = element.getValue();
			if (value == null) {
				createEnemyView(key);
				// create line
			} else {
				// find circle
				ImageView circle = circleMap.get(key.getId());
				if (circle==null) {
					circle = createEnemyView(key);
				}
				
				// Trigger die?
				if (!key.isAlive()) {
					circle.setVisible(false);
					removelist.add(key);
					circleMap.remove(key.getId());

					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					model.addBalance(key.getCredit());
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				} else {
					Path path = new Path();
					path.getElements().add(new MoveTo(value.getX(), value.getY()));
					path.getElements().add(new LineTo(key.getX(), key.getY()));

					// move in line
					pathTransition = new PathTransition();
					pathTransition.setDuration(Duration.millis(1000));
					pathTransition.setNode(circle);
					pathTransition.setPath(path);
					//
					pathTransition.play();
					pathTransition.setOnFinished((event) -> {
						path.getElements().clear();
					});

					// enemy exits map without dying
					if (key.getX() == 15 * HEIGHT + 25) {
						circle.setVisible(false);
					}
				}
			}
		}
		for (Enemy enemy : removelist) {
			controller.removeEnemy(enemy);
		}
		System.out.println();
	}

	private ImageView createEnemyView(Enemy key) {
		ImageView imageView = new ImageView();

		imageView.setX(-50);
		imageView.setY(-50);
		if (key instanceof Enemy1) {
			Image image = new Image("file:image/enemy/e1.gif");
			imageView.setImage(image);
		} else if (key instanceof Enemy2) {
			Image image = new Image("file:image/enemy/e2.gif");
			imageView.setImage(image);
		} else if (key instanceof Enemy3) {
			Image image = new Image("file:image/enemy/e3.gif");
			imageView.setImage(image);
		} else if (key instanceof Enemy4) {
			Image image = new Image("file:image/enemy/e4.gif");
			imageView.setImage(image);
		} else {
		}

		imageView.setFitHeight(50);
		imageView.setFitWidth(50);
		circleMap.put(key.getId(), imageView);
		root.getChildren().add(imageView);
		return imageView;
		
	}

	private void attackAction(TowerDefenseMessge message) {
		String sound = "";
		if (message.getTower() instanceof Tower1) {
			sound = "Minigun_lvl3.wav";
			playSound(sound);
		} else if (message.getTower() instanceof Tower2) {
			sound = "Sniper.wav";
			playSound(sound);
		} else if (message.getTower() instanceof Tower3) {
			sound = "Rocketgun_launch.wav";
			playSound(sound);
		} else if (message.getTower() instanceof Tower4) {
			sound = "Explosion.wav";
			playSound(sound);
		} else if (message.getTower() instanceof Tower5) {
			sound = "Teslagun.wav";
			playSound(sound);
		}
		Circle c = new Circle();
		c.setFill(Color.BLACK);
		c.setRadius(5);
		Path p = new Path();
		p.getElements().add(
				new MoveTo(message.getTower().getTowerCOL() * 50 + 25, message.getTower().getTowerROW() * 50 + 25));
		p.getElements().add(new LineTo(message.getEnemy().getX(), message.getEnemy().getY()));

		PathTransition pt = new PathTransition();
		pt.setDuration(Duration.millis(100));
		pt.setNode(c);
		pt.setPath(p);
		pt.play();

		pt.setOnFinished((event) -> {
			c.setVisible(false);
		});
		root.getChildren().add(c);
	}

	private void placeTower(TowerDefenseMessge message) {
		playSound("Building.wav");
		ImageView imageView = new ImageView();
		if (message.getColor() == 1) {
			Image image = new Image("file:image/tower/t1.gif");
			imageView.setImage(image);
		} else if (message.getColor() == 2) {
			Image image = new Image("file:image/tower/t2.gif");
			imageView.setImage(image);
		} else if (message.getColor() == 3) {
			Image image = new Image("file:image/tower/t3.gif");
			imageView.setImage(image);
		} else if (message.getColor() == 4) {
			Image image = new Image("file:image/tower/t4.gif");
			imageView.setImage(image);
		} else if (message.getColor() == 5) {
			Image image = new Image("file:image/tower/t5.gif");
			imageView.setImage(image);
		} else if (message.getColor() == 6) {
			Image image = new Image("file:image/tower/t6.gif");
			imageView.setImage(image);
		}
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);
		gridPane.add(imageView, message.getCol(), message.getRow());
	}

	private void sellTower(TowerDefenseMessge message) {
		playSound("Buying.wav");
		Image image = new Image("file:image/map" + TowerDefense.LEVEL + "/"
				+ ((15 - message.getRow() - 1) * 15 + (15 - message.getCol())) + ".jpg");
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);
		gridPane.add(imageView, message.getCol(), message.getRow());
	}

	private void playSound(String sound) {
		try {
			InputStream music;
			music = new FileInputStream(new File("audio/" + sound));
			AudioStream audios = new AudioStream(music);
			AudioPlayer.player.start(audios);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
	
	private void playBackground(String sound) {
		try {
			InputStream music;
			music = new FileInputStream(new File("audio/" + sound));
			audios = new AudioStream(music);
			AudioPlayer.player.start(audios);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
	}

}
