package ch3.ex08;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY, int width, Color transformed);
}

public class Ex8 extends Application {
	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y)));
		return out;
	}

	public static Image transform(Image in, ColorTransformer f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(
						x,
						y,
						f.apply(x, y, in.getPixelReader().getColor(x, y), 20,
								Color.BLACK));
		return out;
	}

	public void start(Stage stage) {
		Image image = new Image(Ex8.class.getResourceAsStream("queen-mary.png"));
		Image image2 = transform(image,
				(x, y, c, w, t) -> x < w || x > image.getWidth() - w || y < w
						|| y > image.getHeight() - w ? t : c);

		stage.setScene(new Scene(new HBox(new ImageView(image2))));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
