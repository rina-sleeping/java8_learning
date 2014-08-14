package ex7;

public class DoubleRunner {
	public static Runnable andThen(Runnable first, Runnable next) {
		return () -> {
			first.run();
			next.run();
		};
	}

	public static void main(String[] args) {
		andThen(() -> System.out.println("first"),
				() -> System.out.println("next")).run();
	}
}
