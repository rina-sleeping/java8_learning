package ch6.ex11;

import static org.junit.Assert.fail;

import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Ex11 {
	public static <T> CompletableFuture<T> repeat(Supplier<T> action,
			Predicate<T> until) {
		return CompletableFuture.supplyAsync(action).thenComposeAsync(
				(T t) -> {
					return until.test(t) ? CompletableFuture.completedFuture(t)
							: repeat(action, until);
				});
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Ex11.<PasswordAuthentication> repeat(
				() -> {
					System.out.print("User name:");
					String name = in.nextLine();
					System.out.print("Password:");
					String password = in.nextLine();
					return new PasswordAuthentication(name, password
							.toCharArray());
				},
				s -> {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						fail("sleep fail");
					}
					boolean ret = Arrays.equals(s.getPassword(),
							"secret".toCharArray());
					if (ret) {
						in.close();
					}
					return ret;
				});

		ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
	}
}
