package ch8.ex02;

public class Ex02 {

	public static void main(String[] args) {
		boolean ok = false;
		try {
			Math.negateExact(Integer.MIN_VALUE);
		} catch (Exception e) {
			ok = true;
			System.out.println(e);
		}

		if (!ok) {
			System.out.println("NG: Exception not occurred");
		} else {
			System.out.println("OK");
		}

		ok = false;
		try {
			Math.negateExact(Long.MIN_VALUE);
		} catch (Exception e) {
			ok = true;
			System.out.println(e);
		}

		if (!ok) {
			System.out.println("NG: Exception not occurred");
		} else {
			System.out.println("OK");
		}
	}
}
