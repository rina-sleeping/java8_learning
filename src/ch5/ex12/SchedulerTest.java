package ch5.ex12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class SchedulerTest {

	@Test
	public void testNext() {
		// prepare
		Scheduler sche = new Scheduler();

		// test&check
		Instant next = sche.nextFrom(LocalDateTime.of(2014, 11, 8, 10, 0));
		assertNull(next);

		// prepare
		sche.add(LocalDateTime.of(2014, 11, 8, 10, 0),
				ZoneId.of("America/Los_Angeles"));

		sche.add(LocalDateTime.of(2014, 11, 8, 10, 0),
				ZoneId.of("Europe/Berlin"));

		// test&check
		next = sche.nextFrom(LocalDateTime.of(2014, 11, 12, 10, 0));
		assertNull(next);

		next = sche.nextFrom(LocalDateTime.of(2014, 11, 8, 10, 0));
		assertEquals(
				ZonedDateTime.of(LocalDateTime.of(2014, 11, 8, 18, 00),
						ZoneId.systemDefault()).toInstant(), next);

		// test&check
		sche.delete(LocalDateTime.of(2014, 11, 8, 10, 0),
				ZoneId.of("America/Los_Angeles"));
		sche.delete(LocalDateTime.of(2014, 11, 8, 10, 0),
				ZoneId.of("Europe/Berlin"));
		next = sche.nextFrom(LocalDateTime.of(2014, 11, 8, 10, 0));
		assertNull(next);

	}
}
