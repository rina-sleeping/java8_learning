package ch2.ex05;

import java.util.stream.Stream;

public class RandomStream {
	public static Stream<Long> createRamdomStream(long a, long c, long m,
			long seed) {
		long init = (seed * a + c) % m;
		return Stream.iterate(init, n -> (n * a + c) % m);
	}
}
