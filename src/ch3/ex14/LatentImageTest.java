package ch3.ex14;

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
				.transform((x, y, reader) -> reader.getColor(x, y).grayscale())
				.transform(
						(x, y, reader) -> reader.getColor(x,
								(int) image.getHeight() - 1 - y)).toImage();
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(
				finalImage))));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
