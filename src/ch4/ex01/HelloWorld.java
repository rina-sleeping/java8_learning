package ch4.ex01;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorld extends Application {

	private VBox root;

	public HelloWorld() {
		root = new VBox();
		String initial = "Hello, JavaFX!";
		Label message = new Label(initial);
		message.setFont(new Font(100));
		message.setId("msg");
		TextField input = new TextField(initial);
		input.setId("input");

		message.textProperty().bind(input.textProperty());

		root.getChildren().addAll(message, input);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(this.root));
		stage.setTitle("Hello");
		stage.show();
	}

	public Parent getRoot() {
		return root;

	}
}
