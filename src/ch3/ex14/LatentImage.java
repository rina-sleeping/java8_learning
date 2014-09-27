package ch3.ex14;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, PixelReader reader);
}

class LatentImage {
	private Image in;
	private List<ColorTransformer> pendingOperations;

	private ColorTransformer convert(UnaryOperator<Color> op) {
		return (x, y, r) -> {
			return op.apply(r.getColor(x, y));
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
		CashPixelReader cash = new CashPixelReader(in, pendingOperations.size());

		for (int i = 0; i < pendingOperations.size(); i++) {
			cash.setLevel(i);
			for (int x = 0; x < width; x++)
				for (int y = 0; y < height; y++) {
					Color c = in.getPixelReader().getColor(x, y);
					c = pendingOperations.get(i).apply(x, y, cash);
					out.getPixelWriter().setColor(x, y, c);
				}
			cash.setImage(out);
		}
		return out;
	}

	private class CashPixelReader implements PixelReader {
		private Image in;
		private PixelReader[] cash;
		private int level;

		CashPixelReader(Image in, int size) {
			this.in = in;
			this.cash = new PixelReader[size];
			for (int i = 0; i < size; i++) {
				this.cash[i] = null;
			}
			this.level = 0;
		}

		private void load() {
			if (cash[level] == null) {
				cash[level] = in.getPixelReader();
			}
		}

		private void setLevel(int level) {
			this.level = level;
		}

		private void setImage(Image in) {
			this.in = in;
		}

		@Override
		public int getArgb(int x, int y) {
			load();
			return cash[level].getArgb(x, y);
		}

		@Override
		public Color getColor(int x, int y) {
			load();
			return cash[level].getColor(x, y);
		}

		@Override
		public PixelFormat getPixelFormat() {
			load();
			return cash[level].getPixelFormat();
		}

		@Override
		public <T extends Buffer> void getPixels(int x, int y, int w, int h,
				WritablePixelFormat<T> pixelformat, T buffer, int scanlineStride) {
			load();
			cash[level].getPixels(x, y, w, h, pixelformat, buffer,
					scanlineStride);
		}

		@Override
		public void getPixels(int x, int y, int w, int h,
				WritablePixelFormat<ByteBuffer> pixelformat, byte[] buffer,
				int offset, int scanlineStride) {
			load();
			cash[level].getPixels(x, y, w, h, pixelformat, buffer, offset,
					scanlineStride);
		}

		@Override
		public void getPixels(int x, int y, int w, int h,
				WritablePixelFormat<IntBuffer> pixelformat, int[] buffer,
				int offset, int scanlineStride) {
			load();
			cash[level].getPixels(x, y, w, h, pixelformat, buffer, offset,
					scanlineStride);
		}

	}
}
