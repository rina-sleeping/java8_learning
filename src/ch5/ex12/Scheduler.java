package ch5.ex12;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

class Scheduler {
	private ZoneId zoneId;
	private List<Schedule> schedules;

	private class Schedule {
		private Instant time;
		private ZoneId zoneId;

		Schedule(LocalDateTime time, ZoneId zoneId) {
			this.time = ZonedDateTime.of(time, zoneId).toInstant();
			this.zoneId = zoneId;
		}
	}

	Scheduler() {
		this.schedules = new ArrayList<Schedule>();
		this.zoneId = ZoneId.systemDefault();
	}

	void setZoneId(ZoneId zoneId) {
		this.zoneId = zoneId;
	}

	void add(LocalDateTime time, ZoneId zoneId) {
		schedules.add(new Schedule(time, zoneId));
	}

	void delete(LocalDateTime time, ZoneId zoneId) {
		schedules.removeIf(t -> t.time.equals(ZonedDateTime.of(time, zoneId)
				.toInstant()) && t.zoneId.equals(zoneId));
	}

	/**
	 * 
	 * @param fromDateTime
	 *            This LocalDateTime is treated with Scheduler's zoneId.
	 * @return Instant is the most nearest schedule's time in the scheduler.
	 *         Returned Instant is always after `from`. If scheduler has no
	 *         schedule, null is returned.
	 */
	Instant nextFrom(LocalDateTime fromDateTime) {
		if (this.schedules.size() == 0) {
			return null;
		}

		Instant from = ZonedDateTime.of(fromDateTime, this.zoneId).toInstant();
		Duration min = Duration.ZERO;
		Schedule next = null;
		for (Schedule s : this.schedules) {
			Duration d = Duration.between(from, s.time);
			if (d.isNegative()) {
				continue;
			}

			if (min == Duration.ZERO || d.minus(min).isNegative()) {
				min = d;
				next = s;
			}
		}

		if (next == null) {
			return null;
		}
		return next.time;
	}
}
