package ch2.ex06;

import java.util.stream.Stream;

public class Ex6 {
	public static Stream<Character> characterStream(String s) {
		return Stream.iterate(0, n -> n + 1).limit(s.length()).map(s::charAt);
	}
}
