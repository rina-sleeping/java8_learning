package ch3.ex9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

class Sample {
	private String name;
	public int val;

	Sample(String name, int val) {
		this.name = name;
		this.val = val;
	}
}

public class Ex9Test {

	@Test
	public void test() {
		// prepare

		Sample a = new Sample("a", 0);
		Sample a2 = new Sample("a", 0);
		Sample a3 = new Sample("a", 1);
		Sample b = new Sample("b", 0);

		Comparator<Object> compName = new Ex9().lexicographicComparator("name");
		Comparator<Object> compVal = new Ex9().lexicographicComparator("val");
		Comparator<Object> compBoth = new Ex9().lexicographicComparator("name",
				"val");
		Comparator<Object> compBoth2 = new Ex9().lexicographicComparator("val",
				"name");

		// test
		assertEquals(compName.compare(a, a2), 0);
		assertEquals(compVal.compare(a, a2), 0);
		assertEquals(compBoth.compare(a, a2), 0);
		assertEquals(compBoth2.compare(a, a2), 0);

		assertTrue(compName.compare(a, b) < 0);
		assertEquals(compVal.compare(a, b), 0);
		assertTrue(compBoth.compare(a, b) < 0);
		assertTrue(compBoth2.compare(a, b) < 0);

		assertEquals(compName.compare(a, a3), 0);
		assertTrue(compVal.compare(a, a3) < 0);
		assertTrue(compBoth.compare(a, a3) < 0);
		assertTrue(compBoth2.compare(a, a3) < 0);

		assertTrue(compBoth.compare(a3, b) < 0);
		assertTrue(compBoth2.compare(a3, b) > 0);
	}

}
