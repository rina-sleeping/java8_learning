package ch2.ex10;

import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

public class Ex10Test {

	@Test
	public void test() {
		Double[] data = { 1.0, 2.2, 3.5 };
		double expected = 0;
		for (double d : data) {
			expected += d;
		}
		expected /= data.length;

		double result = Ex10.average(Stream.of(data));

		assertTrue(expected == result);
	}
}
