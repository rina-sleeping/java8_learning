package ch8.ex01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Ex01Test {

	@Test
	public void test() {
		long ans = Ex01.add(Integer.MAX_VALUE, Integer.MAX_VALUE);
		assertEquals(4294967294L, ans);
		ans = Ex01.add(Integer.MAX_VALUE * 2 - 1, Integer.MAX_VALUE * 2 - 1);
		assertEquals((Integer.toUnsignedLong(Integer.MAX_VALUE) * 4 - 2), ans);

		ans = Ex01.sub(Integer.MAX_VALUE, 0);
		assertEquals(Integer.MAX_VALUE, ans);
		ans = Ex01.sub(0, Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE * -1, ans);
		ans = Ex01.sub(Integer.MAX_VALUE * 2 - 1, 0);
		assertEquals((Integer.toUnsignedLong(Integer.MAX_VALUE) * 2 - 1), ans);
		ans = Ex01.sub(0, Integer.MAX_VALUE * 2 - 1);
		assertEquals((Integer.toUnsignedLong(Integer.MAX_VALUE) * (-2) + 1),
				ans);

		ans = Ex01.devide(Integer.MAX_VALUE * 2 - 1, Integer.MAX_VALUE * 2 - 1);
		assertEquals(Integer.divideUnsigned(Integer.MAX_VALUE * 2 - 1,
				Integer.MAX_VALUE * 2 - 1), ans);
		ans = Ex01.devide(Integer.MAX_VALUE + 1, 65536);
		assertEquals(Integer.divideUnsigned(Integer.MAX_VALUE + 1, 65536), ans);
		ans = Ex01.devide(Integer.MAX_VALUE * 2 - 1, 1);
		assertEquals(Integer.divideUnsigned(Integer.MAX_VALUE * 2 - 1, 1), ans);

		ans = Ex01.remainder(Integer.MAX_VALUE + 1, 1);
		assertEquals(Integer.remainderUnsigned(Integer.MAX_VALUE + 1, 1), ans);
		ans = Ex01.remainder(Integer.MAX_VALUE * 2 - 1, Integer.MAX_VALUE);
		assertEquals(Integer.remainderUnsigned(Integer.MAX_VALUE * 2 - 1,
				Integer.MAX_VALUE), ans);

		ans = Ex01.compare(Integer.MAX_VALUE, Integer.MAX_VALUE + 1);
		assertTrue(ans < 0);
		ans = Ex01.compare(Integer.MAX_VALUE + 1, Integer.MAX_VALUE);
		assertTrue(0 < ans);
		ans = Ex01.compare(Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1);
		assertEquals(0, ans);
	}
}
