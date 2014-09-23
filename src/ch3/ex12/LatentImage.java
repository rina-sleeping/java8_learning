package ch3.ex12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY, int width, Color transformed);
}

class LatentImage {
	private Image in;
	private List<ColorTransformer> pendingOperations;

	private ColorTransformer convert(UnaryOperator<Color> op) {
		return (x, y, c, w, t) -> {
			return op.apply(c);
		};
	}

	public static LatentImage from(Image in) {
		LatentImage result = new LatentImage();
		result.in = in;
		result.pendingOperations = new ArrayList<>();
		return result;
	}

	LatentImage transform(ColorTransformer f) {
		pendingOperations.add(f);
		return this;
	}

	LatentImage transform(UnaryOperator<Color> f) {
		pendingOperations.add(convert(f));
		return this;
	}

	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (ColorTransformer f : pendingOperations)
					c = f.apply(x, y, c, 10, Color.GRAY);
				out.getPixelWriter().setColor(x, y, c);
			}
		return out;
	}
}
