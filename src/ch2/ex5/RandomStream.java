package ch2.ex5;

import java.util.stream.Stream;

public class RandomStream {
	public static Stream<Long> createRamdomStream(long a, long c, long m,
			long seed) {
		return Stream.iterate(seed, n -> (n * a + c) % m);
	}
}
