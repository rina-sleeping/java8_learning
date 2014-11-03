package ch5.ex07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class TimeIntervalTest {

	@Test
	public void testTimeInterval() {
		// prepare
		TimeInterval result;

		LocalDateTime[] times = new LocalDateTime[] {
				LocalDateTime.of(2014, 11, 02, 20, 00),
				LocalDateTime.of(2014, 11, 03, 20, 00),
				LocalDateTime.of(2014, 11, 03, 21, 00), };

		// action & check
		result = new TimeInterval(times[0], times[1]);
		assertEquals(result.getStart(), times[0]);
		assertEquals(result.getEnd(), times[1]);

		result = new TimeInterval(times[1], times[0]);
		assertEquals(result.getStart(), times[0]);
		assertEquals(result.getEnd(), times[1]);

		result = new TimeInterval(times[1], times[2]);
		assertEquals(result.getStart(), times[1]);
		assertEquals(result.getEnd(), times[2]);

		result = new TimeInterval(times[2], times[1]);
		assertEquals(result.getStart(), times[1]);
		assertEquals(result.getEnd(), times[2]);

		result = new TimeInterval(times[0], times[0]);
		assertEquals(result.getStart(), times[0]);
		assertEquals(result.getEnd(), times[0]);
	}

	@Test
	public void testIsOverlapped() {
		// prepare
		TimeInterval t1, t2;

		LocalDateTime[] times = new LocalDateTime[] {
				LocalDateTime.of(2014, 11, 02, 20, 00),
				LocalDateTime.of(2014, 11, 03, 20, 00),
				LocalDateTime.of(2014, 11, 03, 21, 00),
				LocalDateTime.of(2014, 11, 04, 21, 00), };

		// action & check
		t1 = new TimeInterval(times[0], times[1]);
		t2 = new TimeInterval(times[2], times[3]);
		assertFalse(t1.isOverlapped(t2));
		assertFalse(t2.isOverlapped(t1));

		t1 = new TimeInterval(times[0], times[1]);
		t2 = new TimeInterval(times[1], times[3]);
		assertFalse(t1.isOverlapped(t2));
		assertFalse(t2.isOverlapped(t1));

		t1 = new TimeInterval(times[0], times[2]);
		t2 = new TimeInterval(times[2], times[2]);
		assertFalse(t1.isOverlapped(t2));
		assertFalse(t2.isOverlapped(t1));

		t1 = new TimeInterval(times[0], times[2]);
		t2 = new TimeInterval(times[1], times[3]);
		assertTrue(t1.isOverlapped(t2));
		assertTrue(t2.isOverlapped(t1));

		t1 = new TimeInterval(times[0], times[1]);
		t2 = new TimeInterval(times[0], times[3]);
		assertTrue(t1.isOverlapped(t2));
		assertTrue(t2.isOverlapped(t1));

		t1 = new TimeInterval(times[0], times[2]);
		t2 = new TimeInterval(times[1], times[3]);
		assertTrue(t1.isOverlapped(t2));
		assertTrue(t2.isOverlapped(t1));

		t1 = new TimeInterval(times[0], times[2]);
		t2 = new TimeInterval(times[0], times[0]);
		assertTrue(t1.isOverlapped(t2));
		assertTrue(t2.isOverlapped(t1));

		t1 = new TimeInterval(times[0], times[0]);
		t2 = new TimeInterval(times[0], times[0]);
		assertTrue(t1.isOverlapped(t2));
		assertTrue(t2.isOverlapped(t1));
	}
}
