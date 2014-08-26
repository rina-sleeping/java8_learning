package ch2.ex9;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Test;

public class Ex9Test {

	@Test
	public void test() {
		// prepare
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> all = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			a.add(i);
			all.add(i);
		}
		ArrayList<Integer> b = new ArrayList<Integer>();
		for (int i = 5; i < 10; i++) {
			b.add(i);
			all.add(i);
		}
		ArrayList<Integer> c = new ArrayList<Integer>();
		for (int i = 10; i < 15; i++) {
			c.add(i);
			all.add(i);
		}

		ArrayList<Integer> result;

		// action and check

		result = Ex9.zip1(Stream.of(a, b, c));
		assertEquals(all, result);

		result = Ex9.zip2(Stream.of(a, b, c));
		assertEquals(all, result);

		result = Ex9.zip3(Stream.of(a, b, c));
		assertEquals(all, result);

		result = Ex9.zip1(Stream.empty());
		assertEquals(new ArrayList<Integer>(0), result);

		result = Ex9.zip2(Stream.empty());
		assertEquals(new ArrayList<Integer>(0), result);

		result = Ex9.zip3(Stream.empty());
		assertEquals(new ArrayList<Integer>(0), result);
	}

}
