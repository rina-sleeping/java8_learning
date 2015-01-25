package ch8.ex05;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ex05Test {

	@Test
	public void test() {

		long start, end;
		Ex05.countWordsByStream("./src/ch2/ex01/alice.txt");

		System.out.println("short list");

		start = System.nanoTime();
		long ans = Ex05.countWordsByStream("./src/ch2/ex01/alice.txt");
		end = System.nanoTime();
		System.out.printf("stream\t\t:%d [ns]\n", (end - start));

		start = System.nanoTime();
		long ans2 = Ex05.countWords("./src/ch2/ex01/alice.txt");
		end = System.nanoTime();
		System.out.printf("stream\t\t:%d [ns]\n", (end - start));

		assertEquals(ans, ans2);

		System.out.println("long list");

		start = System.nanoTime();
		ans = Ex05.countWordsByStream("./src/ch2/ex03/alice4.txt");
		end = System.nanoTime();
		System.out.printf("stream\t\t:%d [ns]\n", (end - start));

		start = System.nanoTime();
		ans2 = Ex05.countWords("./src/ch2/ex03/alice4.txt");
		end = System.nanoTime();
		System.out.printf("stream\t\t:%d [ns]\n", (end - start));

		assertEquals(ans, ans2);
	}
}
