package ch3.ex10;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ch3.ex08.Ex8;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY, int width, Color transformed);
}

public class Ex10 extends Application {
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

	public void start(Stage stage) {
		Image image = new Image(Ex8.class.getResourceAsStream("queen-mary.png"));

		UnaryOperator<Color> op = Color::brighter; // <Color>を入れないとObject型の引数と戻り値をbrithterが持たないといけないためエラーになる。

		// Image image2 = transform(image, op.compose(Color::grayscale));
		// opで<Color>がついていないと、grayscaleがObjectを戻り値にしないため、エラー。
		// <Color>の場合には「Type mismatch: cannot convert from Function<Color,Color>
		// to UnaryOperator<Color>」と型が不一致でエラーになる。
		// <V> Function<V,R> compose(Function<? super V,? extends T> before)
		// の戻り値の関数で、Function<Color,Color>が戻るが、もとはFunction<V,R>となっているため、Function<T,T>を求めるUnaryOperatorと一致しない。
		// ストラクチャル型なら受け付けたが、ノミナル型のため受け付けられなかった。
	}
}
