package ch4.ex09;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Planet extends Application {
	public void start(Stage stage) {
		Group root = new Group();

		Circle planet = new Circle();
		planet.setCenterX(0);
		planet.setCenterY(0);
		planet.setRadius(5);
		planet.setFill(Color.BLUE);
		root.getChildren().add(planet);

		int width = 100;
		int height = 100;
		int x = width / 2;
		int y = height / 2;
		int rx = (int) (width * 0.8) / 2;
		int ry = (int) (height * 0.3) / 2;

		Path path = new Path();
		root.getChildren().add(path);
		path.getElements().add(new MoveTo(x, y - ry));

		ArcTo overArc = new ArcTo();
		overArc.setRadiusX(rx);
		overArc.setRadiusY(ry);
		overArc.setX(x);
		overArc.setY(y + ry);
		path.getElements().add(overArc);

		ArcTo underArc = new ArcTo();
		underArc.setRadiusX(rx);
		underArc.setRadiusY(ry);
		underArc.setX(x);
		underArc.setY(y - ry);
		path.getElements().add(underArc);

		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(1000));
		pathTransition.setNode(planet);
		pathTransition.setPath(path);
		pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(Timeline.INDEFINITE);

		pathTransition.play();

		stage.setScene(new Scene(root, width, height));
		stage.show();
	}
}
