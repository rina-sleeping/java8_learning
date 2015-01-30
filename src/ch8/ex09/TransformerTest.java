package ch8.ex09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.Test;

public class TransformerTest {

	@Test
	public void test() {
		final String path = "./src/ch8/ex09/test.txt";

		try {
			try (Scanner in = new Scanner(Paths.get(path))) {
				Transformer test = new Transformer(in);

				Stream<String> result = test.words();
				assertEquals(6, result.count());
			}

			try (Scanner in = new Scanner(Paths.get(path))) {
				Transformer test = new Transformer(in);

				Stream<String> result = test.lines();
				assertEquals(8, result.count());
			}

			try (Scanner in = new Scanner(Paths.get(path))) {
				Transformer test = new Transformer(in);

				Stream<Integer> result = test.toInt();

				assertEquals(2, result.count());
			}

			try (Scanner in = new Scanner(Paths.get(path))) {
				Transformer test = new Transformer(in);

				Stream<Double> result = test.toDouble();
				assertEquals(4, result.count());
			}
		} catch (IOException e) {
			fail(e.toString());
		}
	}
}
