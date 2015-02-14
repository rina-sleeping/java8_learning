package ch9.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Ex05 {
	public static void reverseWords(String path) {
		try {
			byte[] input = Files.readAllBytes(Paths.get(path));

			byte[] result = new byte[input.length];
			for (int i = 0; i < result.length; i++) {
				result[i] = input[result.length - i - 1];
			}

			// StringBuffer buffer = new StringBuffer(
			// new String(Files.readAllBytes(Paths.get(path)),
			// StandardCharsets.UTF_8));
			//
			// byte[] result = buffer.reverse().toString().getBytes();

			Files.write(Paths.get("./src/ch9/ex05/out.txt"), result,
					StandardOpenOption.CREATE);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
