package ch8.ex01;

public class Ex01 {
	public static long add(int a, int b) {
		return Integer.toUnsignedLong(a) + Integer.toUnsignedLong(b);
	}

	public static long sub(int a, int b) {
		return Integer.toUnsignedLong(a) - Integer.toUnsignedLong(b);
	}

	public static int devide(int dividend, int divisor) {
		return (int) (Integer.toUnsignedLong(dividend) / Integer
				.toUnsignedLong(divisor));

		// Integer.MAX_VALUEより大きい値を扱うためにはtoUnsignedLongでlongで計算しないとけない
		// そのため結果をcastしないと結果の範囲がintにも関わらずintにできない
		// cast前のintの値がInteger.MAX_VALUEより大きい場合、intにキャストしたときに正しくunsigend値のintになる保証がない
		// 上記の理由からInteger.divideUnsignedがある。reminderの余りも同様。
		// 参考：http://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html#jls-5.1.6
		// の「5.1.3. Narrowing Primitive Conversion」
		// 「A narrowing primitive conversion may lose information about the
		// overall magnitude of a numeric value and may also lose precision and
		// range.. 」
	}

	public static int remainder(int dividend, int divisor) {
		return (int) (Integer.toUnsignedLong(dividend) % Integer
				.toUnsignedLong(divisor));
	}

	public static int compare(int a, int b) {
		if (a == b) {
			return 0;
		}
		return Integer.toUnsignedLong(a) < Integer.toUnsignedLong(b) ? -1 : 1;
	}
}
