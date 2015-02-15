package ch9.ex09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LabeledPointTest {

	@Test
	public void testEquals() {
		// prepare
		LabeledPoint a = new LabeledPoint("test", 1, 2);
		LabeledPoint b = new LabeledPoint("test", 2, 2);
		LabeledPoint c = new LabeledPoint("test", 1, 1);
		LabeledPoint d = new LabeledPoint("testB", 1, 2);

		// test and verify
		assertTrue(a.equals(a));
		assertFalse(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
		assertFalse(a.equals(null));
		assertFalse(a.equals("test"));
	}

	@Test
	public void testHashCode() {
		// prepare
		LabeledPoint a = new LabeledPoint("test", 1, 2);
		LabeledPoint a2 = new LabeledPoint("test", 1, 2);
		LabeledPoint b = new LabeledPoint("test", 2, 2);
		LabeledPoint c = new LabeledPoint("test", 1, 1);
		LabeledPoint d = new LabeledPoint("testB", 1, 2);
		LabeledPoint e0 = new LabeledPoint(null, 0, 0);
		LabeledPoint f0 = new LabeledPoint(null, 0, 0);

		// test and verify
		assertEquals(a.hashCode(), a.hashCode());
		assertEquals(a.hashCode(), a2.hashCode());
		assertEquals(e0.hashCode(), f0.hashCode());
		assertNotEquals(a.hashCode(), b.hashCode());
		assertNotEquals(a.hashCode(), c.hashCode());
		assertNotEquals(a.hashCode(), d.hashCode());
	}
}
