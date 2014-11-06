package ch5.ex10;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class WorldTravelClock {

	private static LocalDate tmp = LocalDate.of(2014, 11, 6);

	/**
	 * 
	 * @param departure
	 *            zone id of departure place
	 * @param arrival
	 *            zone id of arrival place
	 * @param departureTime
	 * @param flightTime
	 * @return arrival time
	 */
	public static LocalTime getArrivedTime(String departure, String arrival,
			LocalTime departureTime, Duration flightTime) {
		return ZonedDateTime.of(tmp, departureTime, ZoneId.of(departure))
				.toInstant().plus(flightTime).atZone(ZoneId.of(arrival))
				.toLocalTime();
	}
}
