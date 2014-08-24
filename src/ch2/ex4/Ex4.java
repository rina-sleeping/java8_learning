package ch2.ex4;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex4 {

	public static void main(String[] args) {
		int[] vals = { 1, 4, 9, 16 };
		Stream.of(vals);// Stream<int[]>
		Stream.of(1, 4, 9, 16);// Stream<Integer>

		IntStream.of(vals);// IntStream
		Arrays.stream(vals);// IntStream
	}
}
