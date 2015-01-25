package ch8.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Ex05 {

	public static long countWords(String path) {
		try {
			String contents;
			contents = new String(Files.readAllBytes(Paths.get(path)),
					StandardCharsets.UTF_8);
			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			AtomicLong count = new AtomicLong();
			words.forEach(w -> {
				if (w.length() > 12) {
					count.incrementAndGet();
				}
			});
			return count.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static long countWordsByStream(String path) {
		try {
			String contents;
			contents = new String(Files.readAllBytes(Paths.get(path)),
					StandardCharsets.UTF_8);

			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
			return words.stream().filter(w -> w.length() > 12).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
