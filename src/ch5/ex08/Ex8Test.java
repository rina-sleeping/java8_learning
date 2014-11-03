package ch5.ex08;

import static org.junit.Assert.assertEquals;

import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.Test;

public class Ex8Test {

	@Test
	public void test() {
		ZoneOffset[] result = Ex8.getOffsetsOfNow();
		assertEquals(result.length, ZoneId.getAvailableZoneIds().size());

		for (ZoneOffset o : result) {
			System.out.println(o);
		}
	}
}
