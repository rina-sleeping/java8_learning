package ch9.ex11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Ex11Test {

	@Test
	public void test() {
		// prepare
		List<String> expected = new ArrayList<String>();
		expected.add("1111-2222-3333-4444");
		expected.add("1111-2222-3333-5553");
		expected.add("4444-5555-1234-9876");
		expected.add("8888-0009-4432-5567");
		expected.add("0000-0000-0000-0000");

		try {
			// test
			Ex11.searchCreditNum("./src/ch9/ex11/test1");

			// verify
			List<String> result = Files.readAllLines(Paths
					.get("./src/ch9/ex11/result.txt"));

			for (String r : result) {
				boolean found = false;
				for (String e : expected) {
					if (r.contains(e)) {
						expected.remove(e);
						found = true;
						break;
					}
				}
				if (!found) {
					fail("unexpected:" + r);
				}
			}
			assertEquals(0, expected.size());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			fail("exception occurred");
		}
	}
}
