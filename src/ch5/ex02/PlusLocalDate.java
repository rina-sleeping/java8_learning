package ch5.ex02;

import java.time.LocalDate;

public class PlusLocalDate {
	public static LocalDate plusYear(LocalDate date, long year) {
		return date.plusYears(year);
	}
}
