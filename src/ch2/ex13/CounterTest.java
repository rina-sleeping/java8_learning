package ch2.ex13;

import static org.junit.Assert.assertArrayEquals;

import java.util.stream.Stream;

import org.junit.Test;

public class CounterTest {

	@Test
	public void test() {
		// prepare
		long[] expected = new long[12];
		expected[0] = 0;
		expected[1] = 1;
		expected[2] = 2;
		expected[3] = 1;
		expected[6] = 1;

		// action
		long[] result = Counter.count(Stream.of("a", "aaaaaaaaaaaaaaaaaaa",
				"cccccc", "aa", "aa", "aaa", "ddddddddddddd"));

		// check
		assertArrayEquals(expected, result);

	}
}
