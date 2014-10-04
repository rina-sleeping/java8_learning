package ch3.ex21;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class Ex21Test {

	@Test
	public void test() {
		// prepare
		FutureTask<Integer> future = new FutureTask<Integer>(() -> 2);
		future.run();

		// test
		Future<String> result = Ex21.map(future, (i) -> Integer.toString(i));

		// check
		try {
			assertEquals(result.get(), "2");
		} catch (InterruptedException | ExecutionException e) {
			fail("exception occured:" + e);
		}
	}
}
