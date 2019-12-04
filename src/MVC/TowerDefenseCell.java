package MVC;
 
import java.util.ArrayList;

public class TowerDefenseCell {
	private ArrayList<Object> cellList;
	
	TowerDefenseCell(){
		cellList = new ArrayList<Object>();
	}
	
	public void add(Object obj) {
		cellList.add(obj);
	}
	
	public void setToEmpty(){
		cellList.remove(0);
	}
	
	public boolean isEmpty() {
		return cellList.size() == 0;
	}
	
	public Object getFirst() {
		return cellList.get(0);
	}
	
	public ArrayList<Object> getList(){
		return cellList;
	}
}
