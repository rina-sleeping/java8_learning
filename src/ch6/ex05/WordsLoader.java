package ch6.ex05;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WordsLoader {
	ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<String, Set<File>>();

	public Collection<Set<File>> load(String[] files) {
		Thread[] threads = new Thread[files.length];
		int i = 0;
		for (String path : files) {
			threads[i] = new Thread(() -> {
				try {
					String contents;
					contents = new String(Files.readAllBytes(Paths.get(path)),
							StandardCharsets.UTF_8);
					List<String> words = Arrays.asList(contents
							.split("[\\P{L}]+"));
					for (String word : words) {
						Set<File> init = new HashSet<File>();
						init.add(new File(path));
						map.merge(word, init, (existingValue, newValue) -> {
							newValue.forEach(e -> existingValue.add(e));
							return existingValue;
						});
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			i++;
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return map.values();
	}
}
