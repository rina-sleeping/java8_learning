package ch8.ex03;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GCDTest {

	@Test
	public void test() {
		long ans;
		ans = GCD.gcdByPercent(25, -5);
		assertEquals(5, ans);
		ans = GCD.gcdByPercent(25, -30);
		assertEquals(5, ans);

		ans = GCD.gcdByFloorMod(25, -5);
		assertEquals(5, ans);
		ans = GCD.gcdByFloorMod(25, -30);
		assertEquals(5, ans);

		ans = GCD.gcdByRem(25, -5);
		assertEquals(5, ans);
		ans = GCD.gcdByRem(25, -30);
		assertEquals(5, ans);
	}
}
