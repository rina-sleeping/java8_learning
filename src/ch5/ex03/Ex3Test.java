package ch5.ex03;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class Ex3Test {

	@Test
	public void test() {
		LocalDate day = LocalDate.of(2014, 11, 2);
		LocalDate result = day.with(Ex3
				.next(w -> w.getDayOfWeek().getValue() < 6));
		assertEquals(LocalDate.of(2014, 11, 3), result);

		day = LocalDate.of(2014, 11, 1);
		result = day.with(Ex3.next(w -> w.getDayOfWeek().getValue() < 6));
		assertEquals(LocalDate.of(2014, 11, 3), result);

		day = LocalDate.of(2014, 10, 31);
		result = day.with(Ex3.next(w -> w.getDayOfWeek().getValue() < 6));
		assertEquals(LocalDate.of(2014, 11, 3), result);

		day = LocalDate.of(2014, 10, 30);
		result = day.with(Ex3.next(w -> w.getDayOfWeek().getValue() < 6));
		assertEquals(LocalDate.of(2014, 10, 31), result);
	}

}
