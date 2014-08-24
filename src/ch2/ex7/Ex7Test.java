package ch2.ex7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

public class Ex7Test {

	@Test
	public void test() {

		boolean result;
		Stream<Integer> data = Stream.of(1, 2, 4);
		result = Ex7.isFinite(data);
		assertTrue(result);
		assertEquals(3, data.count());

		result = Ex7.isFinite(Stream.generate(Math::random));
		assertFalse(result);
	}
}
