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

		// Integer.MAX_VALUE���傫���l���������߂ɂ�toUnsignedLong��long�Ōv�Z���Ȃ��Ƃ��Ȃ�
		// ���̂��ߌ��ʂ�cast���Ȃ��ƌ��ʂ͈̔͂�int�ɂ��ւ�炸int�ɂł��Ȃ�
		// cast�O��int�̒l��Integer.MAX_VALUE���傫���ꍇ�Aint�ɃL���X�g�����Ƃ��ɐ�����unsigend�l��int�ɂȂ�ۏ؂��Ȃ�
		// ��L�̗��R����Integer.divideUnsigned������Breminder�̗]������l�B
		// �Q�l�Fhttp://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html#jls-5.1.6
		// �́u5.1.3. Narrowing Primitive Conversion�v
		// �uA narrowing primitive conversion may lose information about the
		// overall magnitude of a numeric value and may also lose precision and
		// range.. �v
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
