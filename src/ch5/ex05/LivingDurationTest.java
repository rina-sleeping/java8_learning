package ch5.ex05;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class LivingDurationTest {

	@Test
	public void test() {
		for (long i = 0; i < 1000; i++) {
			assertEquals(i, LivingDuration.calc(LocalDate.now().minusDays(i)));
		}

		for (long i = 50000; i < 51000; i++) {
			assertEquals(i, LivingDuration.calc(LocalDate.now().minusDays(i)));
		}
	}
}
