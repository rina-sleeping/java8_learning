package ch5.ex07;

import java.time.LocalDateTime;

public class TimeInterval {
	private LocalDateTime start;
	private LocalDateTime end;
	private boolean isPoint;

	/**
	 * 2‚Â‚Ìˆø”‚Ì“ú‚Ì‹æŠÔ‚ğ•\‚·TimeInterval‚ğì¬‚·‚éB 2‚Â‚Ìˆø”‚Ì‡”Ô‚Í–â‚í‚È‚¢B
	 * 
	 * @param first
	 * @param second
	 */
	TimeInterval(LocalDateTime first, LocalDateTime second) {
		if (first.isEqual(second)) {
			isPoint = true;
			start = first;
			end = first;
		} else {
			isPoint = false;
			if (first.isBefore(second)) {
				start = first;
				end = second;
			} else {
				start = second;
				end = first;
			}
		}
	}

	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public boolean isOverlapped(TimeInterval compared) {
		if (isPoint && compared.isPoint) {
			return start.isEqual(compared.start);
		}

		if (isPoint || compared.isPoint) {
			LocalDateTime point;
			TimeInterval interval;
			if (compared.isPoint) {
				point = compared.start;
				interval = this;
			} else {
				point = start;
				interval = compared;
			}

			if (point.isBefore(interval.start) || point.isAfter(interval.end)
					|| point.isEqual(interval.end)) {
				return false;
			}
			return true;
		}

		if (compared.start.isAfter(end) || compared.start.isEqual(end)) {
			return false;
		}

		if (compared.end.isBefore(start) || compared.end.isEqual(start)) {
			return false;
		}

		return true;
	}
}
