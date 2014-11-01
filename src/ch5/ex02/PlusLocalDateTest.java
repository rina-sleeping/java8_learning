package ch5.ex02;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class PlusLocalDateTest {

	@Test
	public void test() {
		LocalDate date = LocalDate.of(2000, 2, 29);

		assertEquals(LocalDate.of(2001, 2, 28), PlusLocalDate.plusYear(date, 1));
		assertEquals(LocalDate.of(2004, 2, 29), PlusLocalDate.plusYear(date, 4));
	}

}
