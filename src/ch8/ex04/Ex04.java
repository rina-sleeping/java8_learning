package ch8.ex04;

import java.util.stream.Stream;

public class Ex04 {
	public static long getMinSeed() {
		final long a = 11L;
		final long v = 246154705703781L;
		final long n = (long) Math.pow(2, 48);
		final long m = 25214903917L;

		long seed = Stream
				.iterate(0L, s -> Long.remainderUnsigned((s - a) * v, n))
				.limit(1000000).filter(s -> s != 0).min((x, y) -> {
					return Long.compare(x ^ m, y ^ m);
				}).get();
		return seed ^ m;
	}
}
