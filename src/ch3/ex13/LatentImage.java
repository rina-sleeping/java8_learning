package ch3.ex13;

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

@FunctionalInterface
interface ImageFilter {
	Color apply(Color nw, Color n, Color ne, Color w, Color c, Color e,
			Color sw, Color s, Color se);
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

	LatentImage filter(ImageFilter filter) {
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
		this.in = out;
		this.pendingOperations.clear();

		out = new WritableImage(width - 1, height - 1);
		for (int x = 0; x < width - 2; x++)
			for (int y = 0; y < height - 2; y++) {
				Color nw = in.getPixelReader().getColor(x, y);
				Color n = in.getPixelReader().getColor(x + 1, y);
				Color ne = in.getPixelReader().getColor(x + 2, y);
				Color w = in.getPixelReader().getColor(x, y + 1);
				Color c = in.getPixelReader().getColor(x + 1, y + 1);
				Color e = in.getPixelReader().getColor(x + 2, y + 1);
				Color sw = in.getPixelReader().getColor(x, y + 2);
				Color s = in.getPixelReader().getColor(x + 1, y + 2);
				Color se = in.getPixelReader().getColor(x + 2, y + 2);
				out.getPixelWriter().setColor(x, y,
						filter.apply(nw, n, ne, w, c, e, sw, s, se));
			}
		this.in = out;
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
