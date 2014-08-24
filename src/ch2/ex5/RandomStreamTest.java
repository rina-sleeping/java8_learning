package ch2.ex5;

import static org.junit.Assert.assertArrayEquals;

import java.util.stream.Stream;

import org.junit.Test;

public class RandomStreamTest {

	@Test
	public void test() {
		long a = 25214903917L;
		long c = 11;
		long m = (long) Math.pow(2, 48);

		Stream<Long> random = RandomStream.createRamdomStream(a, c, m, 0);

		Long[] result = random.limit(10).toArray(Long[]::new);

		Long[] expected = new Long[10];
		expected[0] = 0L;
		for (int i = 1; i < 10; i++)
			expected[i] = (expected[i - 1] * a + c) % m;

		assertArrayEquals(expected, result);
	}
}
