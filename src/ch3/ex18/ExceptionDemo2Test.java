package ch3.ex18;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

import org.junit.Test;

public class ExceptionDemo2Test {

	@Test
	public void test() {

		// exception check
		boolean exceptionOccurred = false;
		String path = "/etc/passwd";
		try {
			new String(Files.readAllBytes(Paths.get(path)),
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			exceptionOccurred = true;
		}
		assertTrue(exceptionOccurred);

		// test
		Function<String, String> s = null;
		try {
			s = ExceptionDemo2.unchecked((p) -> new String(Files
					.readAllBytes(Paths.get(p)), StandardCharsets.UTF_8));
		} catch (Exception e) {
			fail("exception was thrown");
		}

		exceptionOccurred = false;
		try {
			s.apply(path);
		} catch (Exception e) {
			exceptionOccurred = true;
		}
		assertTrue(exceptionOccurred);
	}
}
