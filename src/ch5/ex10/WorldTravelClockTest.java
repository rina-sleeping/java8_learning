package ch5.ex10;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

public class WorldTravelClockTest {

	@Test
	public void test() {
		LocalTime arrivalTime = WorldTravelClock.getArrivedTime(
				"America/Los_Angeles", "Europe/Berlin", LocalTime.of(3, 5),
				Duration.ofMinutes(650));
		assertEquals(LocalTime.of(22, 55), arrivalTime);
	}
}
