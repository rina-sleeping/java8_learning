package ch8.ex15;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class Ex15Test {

	@Test
	public void test() {
		Grepper grep = new Grepper("java", "src/ch8/ex15");
		List<String> result = grep.grep();

		assertEquals(19, result.size());
	}

	@Test
	public void test2() {
		Grepper grep = new Grepper("java", "src/ch8/ex15/Ex15.java");
		List<String> result = grep.grep();
		assertEquals(2, result.size());
	}

	@Test
	public void test3() {
		Grepper grep = new Grepper("j.va", "src/ch8/ex15/Ex15.java");
		List<String> result = grep.grep();
		assertEquals(2, result.size());
	}

	@Test
	public void test4() {
		Grepper grep = new Grepper("[\\[]", "src/ch8/ex15/Ex15.java");
		List<String> result = grep.grep();
		assertEquals(2, result.size());
	}

	@Test
	public void testAllLines() {
		Grepper grep = new Grepper(".*", "src/ch8/ex15/Ex15.java");
		List<String> result = grep.grep();
		assertEquals(18, result.size());
	}
}
