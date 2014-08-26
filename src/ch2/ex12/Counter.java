package ch2.ex12;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Counter {
	public static AtomicInteger[] count(Stream<String> words) {
		AtomicInteger[] shortWords = new AtomicInteger[12];
		for (int i = 0; i < shortWords.length; i++) {
			shortWords[i] = new AtomicInteger();
		}
		words.parallel().forEach(s -> {
			if (s.length() < 12)
				shortWords[s.length()].getAndIncrement();
		});
		System.out.println(Arrays.toString(shortWords));
		return shortWords;
	}
}
