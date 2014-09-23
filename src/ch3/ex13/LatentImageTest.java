package ch3.ex13;

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
				.filter((nw, n, ne, w, c, e, sw, s, se) -> {
					double r = (nw.getRed() + n.getRed() + ne.getRed()
							+ w.getRed() + c.getRed() + e.getRed()
							+ sw.getRed() + s.getRed() + se.getRed()) / 9;
					double g = (nw.getGreen() + n.getGreen() + ne.getGreen()
							+ w.getGreen() + c.getGreen() + e.getGreen()
							+ sw.getGreen() + s.getGreen() + se.getGreen()) / 9;
					double b = (nw.getBlue() + n.getBlue() + ne.getBlue()
							+ w.getBlue() + c.getBlue() + e.getBlue()
							+ sw.getBlue() + s.getBlue() + se.getBlue()) / 9;
					double o = (nw.getOpacity() + n.getOpacity()
							+ ne.getOpacity() + w.getOpacity() + c.getOpacity()
							+ e.getOpacity() + sw.getOpacity() + s.getOpacity() + se
							.getOpacity()) / 9;
					return new Color(r, g, b, o);
				})
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
