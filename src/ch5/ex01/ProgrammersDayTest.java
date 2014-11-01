package ch5.ex01;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class ProgrammersDayTest {

	@Test
	public void test() {
		assertEquals(LocalDate.of(2014, 9, 13), ProgrammersDay.get(2014));

		assertEquals(LocalDate.of(2000, 9, 12), ProgrammersDay.get(2000));
	}
}
