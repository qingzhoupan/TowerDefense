package MVC;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
		Scene scene = new Scene(root, 1150, 750);
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
		GridPane rightGrid = new GridPane();
		rightGrid.setPadding(new Insets(10,10,10, 20));
		rightGrid.setVgap(30);
		rightGrid.setHgap(10);
		//Image img = new Image("file:image/456.png");
		//BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.AUTO);
//		rightGrid.setBackground());
		rightGrid.setStyle("-fx-background-image:url('file:image/tut5_win.png')");
		//rightGrid.setStyle("-fx-background-repeat:stretch;");
		createMenu(rightGrid); // menu VBox
		createMoney(rightGrid); // money VBox
		createTowers(rightGrid); // tower VBox
		createTowerInfo(rightGrid);
		window.setRight(rightGrid);
	}

	/**
	 * Creates the menu drop down for new game, pause, and speed
	 * 
	 * @param vBox
	 *            Vertical box to add children.
	 */
	private void createMenu(GridPane rightGrid) {
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
		rightGrid.add(menuBar,0,0);
	}

	/**
	 * Creates the total balance for the game.
	 * 
	 * @param vBox
	 *            Vertical box to add children
	 */
	private void createMoney(GridPane rightGrid) {
		VBox moneyBox = new VBox(5);
		// TextFlow moneyDisplay = new TextFlow();
		Text moneyTitle = new Text("BALANCE");
		moneyBalance = new Label(Integer.toString(controller.getBalance()));
		moneyBox.getChildren().addAll(moneyTitle, moneyBalance);
		rightGrid.add(moneyBox,1,0);
	}

	/**
	 * Creates tower boxes with event handlers.
	 * 
	 * @param vBox
	 *            Vertical box to add children
	 */
	private void createTowers(GridPane rightGrid) {
		  // main tower Vbox
		  //VBox mainTowerBox = new VBox(20);
		List<String> towerName = new ArrayList<String>(Arrays.asList("Machine Gun", "Sniper", "Cannon", "Rocket\nLauncher", "Tesla Gun", "Money\nGenerator"));
		
		List<Tower> towerList = towerList();
		List<ImageView> imgList = imgList();
		for (int i = 0; i < towerName.size(); i++) {
			final String index = i + 1 + "";
			ImageView image = imgList.get(i);
			Tower tower = towerList.get(i);
			VBox towerBox = new VBox(5);
			towerBox.setAlignment(Pos.CENTER);
			Button towerButton = new Button(towerName.get(i));
			towerBox.getChildren().addAll(image, towerButton);
			rightGrid.add(towerBox, 0, i + 1);
//			image.setOnMouseEntered(e -> {
//				String info = "";
//				info +="Cost: $" + tower.getCost() + "\n" + "Damage: " + tower.getDamage()+ "00\n" + "Range: " + tower.getRange();
//		   
//			});
			towerButton.setOnAction(e -> {
				controller.new_tower_to_LCT(index);
			});
		}

		VBox towerBox = new VBox(3);
		towerBox.setAlignment(Pos.CENTER);
		Button sellButton = new Button("sell");
		Label sellReturn = new Label("Returns 75% cost of tower");
		towerBox.getChildren().addAll(sellButton);
		rightGrid.add(towerBox,0, 7);
		rightGrid.add(sellReturn, 1, 7);
		sellButton.setOnMouseClicked(e -> {
			int x = sellTower.getTowerCOL();
			int y = sellTower.getTowerROW();
			controller.sellTower(x, y);
		});
	}
	
	private void createTowerInfo(GridPane rightGrid) {
		List<Tower> towerName = towerList();
		for(int i = 0; i < towerName.size(); i++) {
			Tower tower = towerName.get(i);
			Label label = new Label();
//			VBox vBox = new VBox();
			for(int j = 0; j < 5; j++) {// 5 labels
				String info = "";
				info = "Name: "+tower.getName()+"\nDamage: "+tower.getDamage()+" dmg\nDescription: "+tower.getDirection()+"\nCost: $"+tower.getCost();
				label = new Label(info);
			}
			rightGrid.add(label, 1, i+1);
		}
	}
	
	
		 /**
		  * Creates a list of tower objects to be used as buttons
		  * @return List of Tower obejcts
		  */
	private List<Tower> towerList(){
		List<Tower> towerList = new ArrayList<Tower>();
		Tower t1 = new Tower1();
		Tower t2 = new Tower2();
		Tower t3 = new Tower3();
		Tower t4 = new Tower4();
		Tower t5 = new Tower5();
		Tower t6 = new Tower6();
		Collections.addAll(towerList, t1,t2,t3,t4,t5,t6);
		return towerList;
	 }
	 
	 /**
	  * Creates individual Images in an image view into a list
	  * @return A list of ImageView Objects to be displayed.
	  */
	private List<ImageView> imgList(){
		List<ImageView> imgList = new ArrayList<ImageView>();
		List<Image> imgs = new ArrayList<Image>();
		Image image1 = new Image("file:image/tower/t1.png");
		Image image2 = new Image("file:image/tower/t2.png");
		Image image3 = new Image("file:image/tower/t3.png");
		Image image4 = new Image("file:image/tower/t4.png");
		Image image5 = new Image("file:image/tower/t5.png");
		Image image6 = new Image("file:image/tower/t6.png");
		Collections.addAll(imgs, image1,image2,image3,image4,image5, image6);
		for(int i = 0; i < imgs.size(); i ++) {
			ImageView imageView = new ImageView();
			imageView.setImage(imgs.get(i));
			imageView.setFitHeight(35);
			imageView.setFitWidth(45);
			imgList.add(imageView);
		}
		return imgList;
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
