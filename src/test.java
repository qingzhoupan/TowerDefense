import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;


public class test extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Path path = new Path();
		Circle circle = new Circle();
		Group root = new Group(circle);
		circle.setCenterX(50);
		circle.setCenterX(50);
		circle.setRadius(20);
		circle.setFill(Color.ANTIQUEWHITE);
		path.getElements().add(new MoveTo(50, 50));
		path.getElements().add(new LineTo(300, 50));
		path.getElements().add(new LineTo(300, 100));
		path.getElements().add(new LineTo(250, 100));
		
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(5000));
		pathTransition.setNode(circle);
		pathTransition.setPath(path);
		pathTransition.play();
		pathTransition.setOnFinished((event)->{
			System.out.println("end!!");
			path.getElements().clear();
		});
		
		
		
		
		Scene scene = new Scene(root, 600, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
