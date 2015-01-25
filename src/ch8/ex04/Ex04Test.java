package ch8.ex04;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class Ex04Test {

	@Test
	public void test() {
		long seed = Ex04.getMinSeed();
		System.out.printf("min seed is %d\n", seed);
		Random generator = new Random(seed);
		for (int i = 0; i < 376050 - 1; i++) {
			generator.nextDouble();
		}
		assertTrue(generator.nextDouble() == 0);
	}

}
