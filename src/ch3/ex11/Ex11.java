package ch3.ex11;

import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Ex11 {
	@FunctionalInterface
	interface ColorTransformer {
		Color apply(int x, int y, Color colorAtXY, int width, Color transformed);
	}

	public static ColorTransformer merge(ColorTransformer first,
			ColorTransformer second) {

		return (x, y, c, w, t) -> {
			Color color = first.apply(x, y, c, w, t);
			return second.apply(x, y, color, w, t);
		};

	}

	public static ColorTransformer convert(UnaryOperator<Color> op) {
		return (x, y, c, w, t) -> {
			return op.apply(c);
		};
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
								Color.GRAY));
		return out;
	}
}
