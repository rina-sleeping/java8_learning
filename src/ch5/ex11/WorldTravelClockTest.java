package ch5.ex11;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

public class WorldTravelClockTest {

	@Test
	public void test_getArrivedTime() {
		LocalTime arrivalTime = WorldTravelClock.getArrivedTime(
				"America/Los_Angeles", "Europe/Berlin", LocalTime.of(3, 5),
				Duration.ofMinutes(10 * 60 + 50));
		assertEquals(LocalTime.of(22, 55), arrivalTime);
	}

	@Test
	public void test_getFlightTime() {
		Duration flightTime = WorldTravelClock.getFlightTime("Europe/Berlin",
				"America/Los_Angeles", LocalTime.of(14, 5),
				LocalTime.of(16, 40));
		assertEquals(Duration.ofMinutes(11 * 60 + 35), flightTime);
	}
}
