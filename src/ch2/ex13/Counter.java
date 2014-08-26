package ch2.ex13;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Counter {
	public static long[] count(Stream<String> words) {
		Map<Integer, Long> map = words
				.parallel()
				.filter(s -> s.length() < 12)
				.collect(
						Collectors.groupingBy(s -> s.length(),
								Collectors.counting()));

		System.out.println(map.toString());

		long[] shortWords = new long[12];

		for (int i = 0; i < 12; i++) {
			Long n = map.get(i);
			if (n != null)
				shortWords[i] =  (long) n;
		}

		return shortWords;
	}
}
