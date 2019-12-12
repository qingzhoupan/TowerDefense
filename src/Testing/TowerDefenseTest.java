package Testing;
import Enemies.*;
import MVC.*;
import Tower.*;
import Networking.*;
import main.*;
import Testing.*;




import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author  YongqiJia & JasonFukumoto & QingzhouPan & GuojunWei
 * 
 * Description: Tests all the files except for the view
 *
 */

class TowerDefenseTest{
	
	/**
	 * 
	 */
	@Test
	public void testController() {
//		TowerDefenseView view = new TowerDefenseView();
//		view.startTimer();
//		view.controller.
		TowerDefenseController controller = new TowerDefenseController(null);
		controller.callSelf();
		System.out.println(controller.model.getWave().get(0));
		controller.model.setRow(15);
		controller.model.setCol(15);
		controller.model.init_Board();
		Tower LAST_CLICKED_TOWER = new Tower1();
		Tower tower2 = new Tower2();
		Tower tower3 = new Tower3();
		Tower tower4 = new Tower4();
		Tower tower5 = new Tower5();
		Tower tower6 = new Tower6();
		Enemy e1 = new Enemy1();
		Enemy e2 = new Enemy2();
		Enemy e3 = new Enemy3();
		Enemy e4 = new Enemy4();
		controller.model.setLCT(LAST_CLICKED_TOWER);
		controller.model.set_objBoard_pos(1, 9, new TowerDefenseCell());
		
		controller.get_imagePos();
		controller.addLevel();
		
		TowerDefenseCell defCell = new TowerDefenseCell();
		defCell.add(tower6);
		//defCell.add(tower5);
		defCell.test();
		
		TowerDefenseMessge mess = new TowerDefenseMessge(1,1,1);
		mess.test();
		
		e1.test();
		
		controller.model.placeTower(1, 9);
		String[] enemiesWave_1 = {"1", "2", "3", "4"};
		controller.model.getWave().add(controller.helper(enemiesWave_1));
		controller.is_tower_here(50, 450);
		controller.model.getTowerList().add(LAST_CLICKED_TOWER);
		controller.model.getTowerList().add(tower2);
		controller.model.getTowerList().add(tower3);
		controller.fps();
		System.out.println(controller.getTowerList());
		
		Point point = new Point(1, 7);
		double pointX = point.getX();
		double pointY = point.getY();
		LAST_CLICKED_TOWER.inRange(point);
		tower2.inRange(point);
		tower3.inRange(point);
		tower4.inRange(point);
		tower5.inRange(point);
		tower6.inRange(point);
		
		Tower testTower = new Tower1();
		testTower.test();
		
		controller.model.set_intBoard_pos(1,9,1);
		controller.model.addBalance(100);
		controller.model.changeMap();
		controller.model.loadTower();
		controller.model.load();
		controller.model.addLevel();
		controller.model.loadLevel();
		controller.refrashEndStatus();
		controller.is_tower_here(50, 450);
		controller.sellTower(1, 5);
		controller.model.setRow(7);
		controller.model.attackAction(LAST_CLICKED_TOWER, e1);
		controller.model.test();
//		controller.placeTower(500, 200);
//		controller.getTowerAt(135, 135);
//		controller.getRow();
//		controller.getCol();
//		controller.getPathCoord();
//		controller.getLEVEL();
//		controller.getBalance();
//		controller.get_intBoard();
//		controller.load();
//		controller.setLCT(tower2);
//		controller.setLCT_null();
//		controller.update_imagePos();
		
	}
	
	/**
	 * 
	 */
	@Test
	public void testControllerPlaceTower() {
		TowerDefenseController controller = new TowerDefenseController(null);
		controller.placeTower(500, 200);
		controller.fps();
		controller.save();
		controller.new_tower_to_LCT("1");
		controller.new_tower_to_LCT("2");
		controller.new_tower_to_LCT("3");
		controller.new_tower_to_LCT("4");
		controller.new_tower_to_LCT("5");
		controller.new_tower_to_LCT("6");
//		controller.load();
//		controller.getTowerAt(200, 400);
//		controller.model.setRow(15);
//		controller.model.setCol(15);
//		controller.model.init_Board();
//		Tower LAST_CLICKED_TOWER = new Tower6();
//		controller.model.setLCT(LAST_CLICKED_TOWER);
//		controller.model.set_objBoard_pos(7, 8, new TowerDefenseCell());
		
	}

}