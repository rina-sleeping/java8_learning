package ch5.ex06;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.Test;

public class Friday13thTest {

	@Test
	public void test() {
		LocalDate[] result;

		result = Friday13th.cal(18);
		assertEquals(172, result.length);
		for (LocalDate date : result) {
			assertEquals(13, date.getDayOfMonth());
			assertEquals(DayOfWeek.FRIDAY, date.getDayOfWeek());
		}

		result = Friday13th.cal(19);
		assertEquals(173, result.length);// 174?
		for (LocalDate date : result) {
			assertEquals(13, date.getDayOfMonth());
			assertEquals(DayOfWeek.FRIDAY, date.getDayOfWeek());
		}

		result = Friday13th.cal(20);
		assertEquals(171, result.length);
		for (LocalDate date : result) {
			assertEquals(13, date.getDayOfMonth());
			assertEquals(DayOfWeek.FRIDAY, date.getDayOfWeek());
		}

		result = Friday13th.cal(21);
		assertEquals(172, result.length);
		for (LocalDate date : result) {
			assertEquals(13, date.getDayOfMonth());
			assertEquals(DayOfWeek.FRIDAY, date.getDayOfWeek());
		}

		result = Friday13th.cal(22);
		assertEquals(172, result.length);
		for (LocalDate date : result) {
			assertEquals(13, date.getDayOfMonth());
			assertEquals(DayOfWeek.FRIDAY, date.getDayOfWeek());
		}

		result = Friday13th.cal(-1);
		assertEquals(0, result.length);
	}
}
