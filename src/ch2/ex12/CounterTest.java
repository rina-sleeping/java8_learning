package ch2.ex12;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.Test;

public class CounterTest {

	@Test
	public void test() {
		// prepare
		int[] expected = new int[12];
		expected[0] = 0;
		expected[1] = 1;
		expected[2] = 2;
		expected[3] = 1;
		expected[6] = 1;

		// action
		AtomicInteger[] result = Counter.count(Stream.of("a",
				"aaaaaaaaaaaaaaaaaaa", "cccccc", "aa", "aa", "aaa",
				"ddddddddddddd"));

		// check
		for (int i = 0; i < result.length; i++) {
			assertEquals(expected[i], result[i].get());
		}

	}
}
