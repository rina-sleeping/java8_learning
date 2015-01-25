package ch8.ex03;

public class GCD {
	// %Ç™ä»íPÇæÇ∆évÇ§ÅB
	public static long gcdByPercent(long a, long b) {
		if (b == 0) {
			return Math.abs(a);
		}
		return gcdByPercent(b, a % b);
	}

	public static long gcdByFloorMod(long a, long b) {
		if (b == 0) {
			return Math.abs(a);
		}

		return gcdByFloorMod(b, Math.floorMod(a, b));
	}

	public static long gcdByRem(long a, long b) {
		if (b == 0) {
			return Math.abs(a);
		}

		return gcdByRem(b, rem(a, b));
	}

	private static long rem(long x, long y) {
		return Math.abs(x % y);
	}
}
