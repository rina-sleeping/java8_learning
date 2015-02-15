package ch9.ex08;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PointTest {

	@Test
	public void test() {
		Point a = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point b = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		Point c = new Point(Integer.MAX_VALUE, Integer.MIN_VALUE);

		assertTrue(a.compareTo(b) > 0);
		assertTrue(b.compareTo(a) < 0);
		assertTrue(a.compareTo(c) > 0);
		assertTrue(c.compareTo(a) < 0);
		assertEquals(0, a.compareTo(a));
	}
}
