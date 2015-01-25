package ch8.ex06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import org.junit.Test;

public class Ex06Test {

	@Test
	public void test() {
		Point2D a = new Point2D(1, 2);
		Point2D a2 = new Point2D(1, 2);
		Point2D b = new Point2D(2, 1);

		assertEquals(0, Ex06.compare(a, a2));
		assertTrue(Ex06.compare(a, b) < 0);
		assertTrue(Ex06.compare(b, a) > 0);

		Rectangle2D x = new Rectangle2D(1, 2, 3, 4);
		Rectangle2D x2 = new Rectangle2D(1, 2, 3, 4);
		Rectangle2D y = new Rectangle2D(2, 2, 3, 4);

		assertEquals(0, Ex06.compare(x, x2));
		assertTrue(Ex06.compare(x, y) < 0);
		assertTrue(Ex06.compare(y, x) > 0);

	}
}
