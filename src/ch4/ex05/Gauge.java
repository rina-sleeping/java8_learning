package ch4.ex05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Gauge extends Application {

	@Override
	public void start(Stage stage) {
		Button smaller = new Button("Smaller");
		smaller.setId("smallButton");
		Button larger = new Button("Larger");
		larger.setId("largeButton");
		Rectangle gauge = new Rectangle(0, 5, 50, 15);
		Rectangle outline = new Rectangle(0, 5, 100, 15);
		outline.setFill(null);
		outline.setStroke(Color.BLACK);
		Pane pane = new Pane();
		pane.getChildren().addAll(gauge, outline);
		smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
		larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));

		// for test from here
		smaller.disableProperty().bind(
				Observer.observe((t, u) -> t.intValue() <= 0
						|| u.intValue() > 100, gauge.widthProperty(),
						pane.heightProperty()));
		larger.disableProperty().bind(
				Observer.observe(t -> t.intValue() >= 100,
						gauge.widthProperty()));
		// end

		HBox box = new HBox(10);
		box.getChildren().addAll(smaller, pane, larger);

		Scene scene = new Scene(box);
		stage.setScene(scene);
		stage.show();
	}
}
