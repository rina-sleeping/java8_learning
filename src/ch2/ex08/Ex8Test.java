package ch2.ex08;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.Test;

public class Ex8Test {

	@Test
	public void test() {
		Stream<Integer> result = Ex8.zip(Stream.of(1, 3, 5),
				Stream.of(2, 4, 6, 7));
		assertArrayEquals(Stream.of(1, 2, 3, 4, 5, 6).toArray(),
				result.toArray());

		result = Ex8.zip(Stream.of(2, 4, 6, 7), Stream.of(1, 3, 5));
		assertArrayEquals(Stream.of(2, 1, 4, 3, 6, 5).toArray(),
				result.toArray());

		result = Ex8.zip(Stream.of(1, 3, 5), Stream.empty());
		assertArrayEquals(Stream.empty().toArray(), result.toArray());

		result = Ex8.zip(Stream.empty(), Stream.of(1, 3, 5));
		assertArrayEquals(Stream.empty().toArray(), result.toArray());

		Stream<Double> result2 = Ex8.zip(Stream.of(1.2, 3.0, 5.0),
				Stream.generate(Math::random));
		assertEquals(6, result2.count());
	}

	@Test
	public void test2() {
		Stream<Integer> result = Ex8.zip2(Stream.of(1, 3, 5),
				Stream.of(2, 4, 6, 7));
		assertArrayEquals(Stream.of(1, 2, 3, 4, 5, 6).toArray(),
				result.toArray());

		result = Ex8.zip2(Stream.of(2, 4, 6, 7), Stream.of(1, 3, 5));
		assertArrayEquals(Stream.of(2, 1, 4, 3, 6, 5).toArray(),
				result.toArray());

		result = Ex8.zip2(Stream.of(1, 3, 5), Stream.empty());
		assertArrayEquals(Stream.empty().toArray(), result.toArray());

		result = Ex8.zip2(Stream.empty(), Stream.of(1, 3, 5));
		assertArrayEquals(Stream.empty().toArray(), result.toArray());

		Stream<Double> result2 = Ex8.zip2(Stream.of(1.2, 3.0, 5.0),
				Stream.generate(Math::random));
		assertEquals(6, result2.count());

		result2 = Ex8.zip2(Stream.generate(Math::random),
				Stream.generate(Math::random));
		assertEquals(-1, result2.spliterator().getExactSizeIfKnown());
	}
}
