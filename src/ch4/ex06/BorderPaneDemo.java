package ch4.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneDemo extends Application {
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();
		Button top = new Button("Top");
		BorderPane.setAlignment(top, Pos.TOP_CENTER);
		pane.setTop(top);
		pane.setLeft(new Button("Left"));
		pane.setCenter(new Button("Center"));
		pane.setRight(new Button("Right"));
		Button bottom = new Button("Bottom");
		BorderPane.setAlignment(bottom, Pos.BOTTOM_CENTER);
		pane.setBottom(bottom);
		stage.setScene(new Scene(pane));
		stage.show();
	}
}
