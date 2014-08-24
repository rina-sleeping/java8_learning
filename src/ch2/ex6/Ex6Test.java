package ch2.ex6;

import static org.junit.Assert.assertArrayEquals;

import java.util.stream.Stream;

import org.junit.Test;

public class Ex6Test {

	@Test
	public void test() {
		String words = "abcde";

		Stream<Character> result = Ex6.characterStream(words);

		Stream<Character> expected = Stream.of('a', 'b', 'c', 'd', 'e');
		assertArrayEquals(expected.toArray(), result.toArray());

		String empty = "";

		result = Ex6.characterStream(empty);

		expected = Stream.of();
		assertArrayEquals(expected.toArray(), result.toArray());
	}
}
