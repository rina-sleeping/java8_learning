package ch2.ex01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex1 {

	public static int countWordsByForLoop() throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("./src/ch2/ex1/alice.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		int count = 0;
		for (String w : words) {
			if (w.length() > 12)
				count++;
		}

		return count;
	}

	public static int countWords() throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("./src/ch2/ex1/alice.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		// 単一カウンターを用いるとincrement毎に排他制御が必要なため、個々の結果を合算する

		Counter counter = new Counter();
		final int GROUP_NUM = 4;
		List<Thread> threads = new ArrayList<Thread>(GROUP_NUM);

		for (int i = 0; i < GROUP_NUM; i++) {
			List<String> list = words.subList(words.size() / 4 * i,
					words.size() / 4 * (i + 1));

			threads.add(new Thread(() -> {
				int count = 0;
				for (String w : list) {
					if (w.length() > 12)
						count++;
				}
				counter.add(count);
			}, Integer.toString(i)));
		}

		for (Thread t : threads)
			t.start();

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return counter.get();
	}
}
