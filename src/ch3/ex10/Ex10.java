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

		UnaryOperator<Color> op = Color::brighter; // <Color>�����Ȃ���Object�^�̈����Ɩ߂�l��brithter�������Ȃ��Ƃ����Ȃ����߃G���[�ɂȂ�B

		// Image image2 = transform(image, op.compose(Color::grayscale));
		// op��<Color>�����Ă��Ȃ��ƁAgrayscale��Object��߂�l�ɂ��Ȃ����߁A�G���[�B
		// <Color>�̏ꍇ�ɂ́uType mismatch: cannot convert from Function<Color,Color>
		// to UnaryOperator<Color>�v�ƌ^���s��v�ŃG���[�ɂȂ�B
		// <V> Function<V,R> compose(Function<? super V,? extends T> before)
		// �̖߂�l�̊֐��ŁAFunction<Color,Color>���߂邪�A���Ƃ�Function<V,R>�ƂȂ��Ă��邽�߁AFunction<T,T>�����߂�UnaryOperator�ƈ�v���Ȃ��B
		// �X�g���N�`�����^�Ȃ�󂯕t�������A�m�~�i���^�̂��ߎ󂯕t�����Ȃ������B
	}
}
