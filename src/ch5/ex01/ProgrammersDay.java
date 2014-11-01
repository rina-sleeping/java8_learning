package ch5.ex01;

import java.time.LocalDate;

public class ProgrammersDay {
	public static LocalDate get(int year) {
		return LocalDate.of(year, 1, 1).isLeapYear() ? LocalDate
				.of(year, 9, 12) : LocalDate.of(year, 9, 13);
	}
}
