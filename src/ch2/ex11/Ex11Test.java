package ch2.ex11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Test;

public class Ex11Test {

	@Test
	public void test() {
		Integer[] data = { 1, 2, 3 };

		ArrayList<Integer> result = Ex11.collect1(Stream.of(data));

		assertEquals(data.length, result.size());
		for (Integer i : data) {
			assertTrue(result.contains(i));
		}

	}
}
