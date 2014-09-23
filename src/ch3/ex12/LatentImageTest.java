package ch3.ex12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LatentImageTest extends Application {

	public void start(Stage stage) {
		Image image = new Image(
				LatentImageTest.class.getResourceAsStream("eiffel-tower.jpg"));
		Image finalImage = LatentImage
				.from(image)
				.transform(Color::brighter)
				.transform(Color::grayscale)
				.transform(
						(x, y, c, w, t) -> x < w || x > image.getWidth() - w
								|| y < w || y > image.getHeight() - w ? t : c)
				.toImage();
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(
				finalImage))));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
