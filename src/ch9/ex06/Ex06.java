package ch9.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Ex06 {
	public static void reverseLines(String path) throws IOException {
		List<String> input = Files.readAllLines(Paths.get(path));

		StringBuffer result = new StringBuffer();
		for (int i = input.size() - 1; i >= 0; i--) {
			result.append(input.get(i));
			result.append("\n");
		}

		Files.write(Paths.get("./src/ch9/ex06/out.txt"), result.toString()
				.getBytes(), StandardOpenOption.CREATE);
	}
}
