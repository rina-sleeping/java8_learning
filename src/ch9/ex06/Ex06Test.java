package ch9.ex06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class Ex06Test {

	@Test
	public void test() {
		// prepare
		String inputFile = "./src/ch9/ex06/input.txt";
		try {
			String inputData = "abcdefg\nhijk\nlmn";
			Files.write(Paths.get(inputFile), inputData.getBytes(),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			fail("cannot create test data");
		}

		// test
		Ex06.reverseLines(inputFile);

		// verify
		try {
			byte[] result = Files.readAllBytes(Paths
					.get("./src/ch9/ex06/out.txt"));
			byte[] expected = "lmn\nhijk\nabcdefg".getBytes();
			for (int i = 0; i < expected.length; i++) {
				assertEquals(expected[i], result[i]);
			}
		} catch (IOException e) {
			fail("cannot read result data");
		}
	}

}
