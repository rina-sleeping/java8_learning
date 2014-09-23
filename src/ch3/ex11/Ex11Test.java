package ch3.ex11;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ch3.ex08.Ex8;
import ch3.ex11.Ex11.ColorTransformer;

public class Ex11Test extends Application {

	public void start(Stage stage) {
		Image image = new Image(Ex8.class.getResourceAsStream("queen-mary.png"));

		ColorTransformer trans1 = (x, y, c, w, t) -> x < w
				|| x > image.getWidth() - w || y < w
				|| y > image.getHeight() - w ? t : c;

		ColorTransformer trans2 = Ex11.convert(Color::brighter);

		Image image2 = Ex11.transform(image, Ex11.merge(trans1, trans2));

		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(
				image2))));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
