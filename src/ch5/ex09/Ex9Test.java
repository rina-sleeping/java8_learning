package ch5.ex09;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class Ex9Test {

	@Test
	public void test() {
		String[] result = Ex9.getTimeZoneOfNowWithin1Hour();
		LocalDateTime now = LocalDateTime.now();

		assertTrue(result.length != ZoneId.getAvailableZoneIds().size());

		for (String id : result) {
			int secs = ZonedDateTime.of(now, ZoneId.of(id)).getOffset()
					.getTotalSeconds();
			assertTrue(Math.abs(secs) < 60 * 60);
		}
	}
}
