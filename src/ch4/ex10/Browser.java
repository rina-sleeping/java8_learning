package ch4.ex10;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Browser extends Application {

	private String location = "http://horstmann.com";
	private BorderPane bar;
	private Label urlLabel;
	private TextField url;
	private Button back;

	public Browser() {
		bar = new BorderPane();
		urlLabel = new Label("URL");
		url = new TextField(location);
		back = new Button("Back");

		bar.setLeft(urlLabel);
		bar.setCenter(url);
		bar.setRight(back);

		url.setId("url");

		back.setId("back");

		WebView browser = new WebView();
		WebEngine engine = browser.getEngine();
		engine.load(location);
		bar.setBottom(browser);

		url.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				engine.load(url.getText());
			}
		});

		back.setOnMouseReleased(event -> {
			engine.getHistory().go(-1);
			url.setText(engine.getLocation());
		});
	}

	public Parent getRoot() {
		return bar;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(bar);
		stage.setScene(scene);
		stage.show();

	}
}
