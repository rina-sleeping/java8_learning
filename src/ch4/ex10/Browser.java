package ch4.ex10;

import javafx.application.Application;
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

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bar = new BorderPane();
		Label urlLabel = new Label("URL");
		TextField url = new TextField(location);
		Button back = new Button("Back");

		bar.setLeft(urlLabel);
		bar.setCenter(url);
		bar.setRight(back);

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
			;
		});

		Scene scene = new Scene(bar);
		stage.setScene(scene);
		stage.show();

	}
}
