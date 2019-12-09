package MVC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import Enemies.Enemy;
import Enemies.Enemy1;
import Enemies.Enemy2;
import Enemies.Enemy3;
import Enemies.Enemy4;
//imports Tower package
import Tower.Tower;
import Tower.Tower1;
import Tower.Tower2;
import Tower.Tower3;
import Tower.Tower4;
import Tower.Tower5;
import Tower.Tower6;
import main.TowerDefense;


/**
 * @author guojunwei
 *
 */
public class TowerDefenseController {

	TowerDefenseModel model;
	TowerDefenseView view;

	public TowerDefenseController(TowerDefenseView towerDefenseView) {
		model = new TowerDefenseModel();
		createStage();
		this.view = towerDefenseView;
		if (towerDefenseView != null) { // this if statement is for independent test of controller
			model.addObserver(towerDefenseView);
		}
	}

	private void createStage() {
		Scanner input = null;
		try {
			input = new Scanner(new File("map"+TowerDefense.LEVEL+".txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int row = Integer.parseInt(input.nextLine());
		int col = Integer.parseInt(input.nextLine());
		model.setRow(row);
		model.setCol(col);   
		model.init_Board();
		
		// set up turn point
		String[] path = input.nextLine().split(" ");
		for (int i = 0; i < path.length; i++) {
			model.setPathCoord(Integer.parseInt(path[i]));
		}
		
		// set up enemies waves
		String[] enemiesWave_1 = input.nextLine().split(" ");
		String[] enemiesWave_2 = input.nextLine().split(" ");
		String[] enemiesWave_3 = input.nextLine().split(" ");
		model.getWave().add(helper(enemiesWave_1));
		model.getWave().add(helper(enemiesWave_2));
		model.getWave().add(helper(enemiesWave_3));

		// set up board
		for (int i = 0; i < row; i++) {
			String[] tmpLine = input.nextLine().split(" ");
			for (int j = 0; j < col; j++) {
				TowerDefenseCell tmp = new TowerDefenseCell();
				model.set_objBoard_pos(i, j, tmp);
				model.set_intBoard_pos(i, j, Integer.parseInt(tmpLine[j]));
			}
		}
		while (input.hasNextLine()) {
			String[] pathPoint = input.nextLine().split(" ");
			Point p = new Point(Integer.valueOf(pathPoint[0]), Integer.valueOf(pathPoint[1]));
			model.getPath().add(p);
		}
	}

	public ArrayList<Enemy> helper(String[] wave){
		ArrayList<Enemy> list = new ArrayList<Enemy>();
		for(int i = 0; i < wave.length; i++) {
			Enemy enemy = null;
			if(wave[i].equals("1")) {
				enemy = new Enemy1();
			}else if(wave[i].equals("2")){
				enemy = new Enemy2();
			}else if(wave[i].equals("3")){
				enemy = new Enemy3();
			}else{
				enemy = new Enemy4();
			}
			enemy.setId(UUID.randomUUID().toString());
			list.add(enemy);
		}
		return list;
	}
	
	/**
	 * Calls model to place tower then sets last clicked tower to null.
	 * 
	 * @param x
	 *            coordinate in pixels
	 * @param y
	 *            coordinate in pixel
	 */
	public void placeTower(int x, int y) {
		int col = x / 50;
		int row = y / 50;
		System.out.println(model.get_intBoard_pos(row, col));
		if (!is_tower_here(x, y) && model.get_intBoard_pos(row, col) == 0) {
			model.placeTower(row, col);
		}
		model.setLCT(null);
	}

	public void sellTower(int x, int y) {
		if (getLCT() != null) {
			model.sellTower(y, x);
		}
	}

	public void new_tower_to_LCT(String towerType) {
		if (towerType.equals("1")) {
			Tower tower1 = new Tower1();
			model.setLCT(tower1);
		} else if (towerType.equals("2")) {
			Tower tower2 = new Tower2();
			model.setLCT(tower2);
		} else if (towerType.equals("3")) {
			Tower tower3 = new Tower3();
			model.setLCT(tower3);
		} else if (towerType.equals("4")) {
			Tower tower4 = new Tower4();
			model.setLCT(tower4);
		} else if (towerType.equals("5")) {
			Tower tower5 = new Tower5();
			model.setLCT(tower5);
		} else if (towerType.equals("6")) {
			Tower tower6 = new Tower6();
			model.setLCT(tower6);
		}

	}

	/**
	 * Checks to see if a position on the grid is taken. For placement purposes
	 * 
	 * @param x
	 *            coordinate in pixels.
	 * @param y
	 *            coordinate in pixels.
	 * @return boolean representing if position is taken.
	 */
	public boolean is_tower_here(int x, int y) {
		int col = x / 50;
		int row = y / 50;
		if (model.get_intBoard_pos(row, col) != 2) {
			return false;
		}
		return true;
	}

	public Tower getTowerAt(int x, int y) {
		int col = x / 50;
		int row = y / 50;
		return (Tower) model.get_objBoard_pos(row, col).getFirst();
	}

	public int getRow() {
		return model.getRow();
	}

	public int getCol() {
		return model.getCol();
	}

	public void setLCT(Tower tower) {
		model.setLCT(tower);
	}

	public void setLCT_null() {
		model.setLCT(null);
	}

	public Tower getLCT() {
		return model.getLCT();
	}

	public int getBalance() {
		return model.getBalance();
	}

	public int[][] get_intBoard() {
		return model.get_intBoard();
	}

	public ArrayList<Integer> getPathCoord() {
		return model.getPathCoord();
	}

	public void fps() {
		// set enemies waves
		if(model.getMap().isEmpty() && !model.getWave().isEmpty()) {
			model.currentEnemyList = model.getWave().get(0);
			model.getWave().remove(0);
		}
		
		// attack
		for(Tower tower : model.getTowerList()) {
			for (Iterator<Enemy> iterator = model.currentEnemyList.iterator(); iterator.hasNext();) {
				Enemy enemy = iterator.next();
				System.out.println(enemy.getCol());
				if (tower.inRange(enemy)) {
					model.attackAction(tower, enemy);
					enemy.beingAttacked(tower.getDamage());		
					if(!enemy.isAlive()) {
						iterator.remove();
					}
				}
			}
		}
		
		// 1 enemy move
		for (Enemy enemies : model.currentEnemyList) {
			List<Point> path = model.getPath();
			if (enemies.getCol()==null) {
				//initEnemy();
				Point point = path.get(0);
				enemies.move(point);
				model.getMap().put(enemies, null); // means create enemy
				break;
			}else {
				// move
				for (int i = 0; i < path.size(); i++) {
					Point point = path.get(i);
					if (point.equalPoint(enemies)) {
						if (i==path.size()-1) {
							// fail destroy enemy 
							model.getMap().remove(enemies);
						}else {
							enemies.move(path.get(i+1));
							model.getMap().put(enemies, point);
							break;
						}
					}
				}			
			}
		}
		
		
		model.fps();
	}

	public void removeEnemy(Enemy key) {
		model.getMap().remove(key);
	}
	
	public int get_imagePos() {
		return model.imagePos();
	}
	
	public void update_imagePos() {
		model.update_imagePos();
	}

	public void refrashEndStatus() {
		if (!model.changingMap) {
			if (model.getWave().isEmpty() && model.getMap().isEmpty()) {
				model.changeMap();
			} 
		}
	}

	public ArrayList<Tower> getTowerList() {
		return model.getTowerList();
	}

	public void save() {
		try{  
            FileOutputStream fs = new FileOutputStream("foo.ser");  // TODO
            ObjectOutputStream os =  new ObjectOutputStream(fs);  
            os.writeObject(this.model);  
            os.close();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
	}

	public void load() {
		try {
			FileInputStream fs = new FileInputStream("foo.ser");  // TODO
			ObjectInputStream os =  new ObjectInputStream(fs);  
			this.model = (TowerDefenseModel)os.readObject();
			os.close(); 
			System.out.println("loadTest !!!!!!!!!!!!!"+model.getCol());
			model.addObserver(this.view);
			model.loadTower();
			fps();
		}catch(Exception ex){  
            ex.printStackTrace();  
		}
	}
}
