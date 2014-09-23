package ch2.ex03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Ex3 {

	public static long count(Stream<String> words) {
		return words.filter(s -> s.length() > 12).count();
	}

	public static long countParallel(Stream<String> words) {
		return words.parallel().filter(s -> s.length() > 12).count();
	}

	public static void main(String[] args) {
		try {
			String contents = new String(Files.readAllBytes(Paths
					.get("./src/ch2/ex1/alice.txt")), StandardCharsets.UTF_8);
			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			long start, end;

			System.out.println("==== alice.txt contains 3598 lines.");
			start = System.nanoTime();
			Ex3.count(words.stream());
			end = System.nanoTime();
			System.out.println("stream\t\t:" + (end - start));

			start = System.nanoTime();
			Ex3.countParallel(words.stream());
			end = System.nanoTime();
			System.out.println("streamParallel\t:" + (end - start));

			contents = new String(Files.readAllBytes(Paths
					.get("./src/ch2/ex3/alice4.txt")), StandardCharsets.UTF_8);
			words = Arrays.asList(contents.split("[\\P{L}]+"));

			System.out.println("==== alice4.txt contains 14391 lines.");
			start = System.nanoTime();
			Ex3.count(words.stream());
			end = System.nanoTime();
			System.out.println("stream\t\t:" + (end - start));

			start = System.nanoTime();
			Ex3.countParallel(words.stream());
			end = System.nanoTime();
			System.out.println("streamParallel\t:" + (end - start));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
