package ch2.ex10;

import java.util.stream.Stream;

public class Ex10 {

	public static double average(Stream<Double> d) {
		// ���v���v�Z�������_�ł����I�[���Ă��܂����߁Acount��K�p���邱�Ƃ��ł��Ȃ��B
		class Tmp {
			int count;
			double sum;

			public Tmp() {
				this.count = 0;
				this.sum = 0;
			}
		}

		Tmp result = d.reduce(new Tmp(), (t, n) -> {
			t.count++;
			t.sum += n;
			return t;
		}, (t1, t2) -> {
			t1.count += t2.count;
			t1.sum += t2.sum;
			return t1;
		});

		return result.sum / result.count;
	}
}
