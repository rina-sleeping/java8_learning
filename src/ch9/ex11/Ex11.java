package ch9.ex11;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Ex11 {
	public static void searchCreditNum(String searchRootPath)
			throws IOException, InterruptedException {
		Path result = Paths.get("./src/ch9/ex11/result.txt");
		Path tmp = Paths.get("./src/ch9/ex11/tmp.txt");

		// for linux
		// ProcessBuilder builder = new ProcessBuilder("grep", "-r",
		// searchRootPath, "^[^0-9]*\d{4}-\d{4}-\d{4}-\d{4}[^0-9]*$");

		ProcessBuilder builder = new ProcessBuilder(
				"findstr",
				"/s",
				"/r",
				"[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]",
				searchRootPath + "/*");

		// for linux
		// builder.redirectOutput(result.toFile());

		builder.redirectOutput(tmp.toFile());
		Process process = builder.start();
		process.waitFor();

		// following is only for windows

		List<String> lines = Files.readAllLines(tmp);

		if (Files.exists(result)) {
			Files.delete(result);
		}

		try (Writer writer = Files.newBufferedWriter(result,
				StandardOpenOption.CREATE)) {

			for (String l : lines) {
				if (l.matches(".*\\d{4}+-\\d{4}+-\\d{4}+-\\d{4}[\\D$]*")) {
					writer.append(l);
					writer.append("\n");
				}
			}
		}

		Files.delete(tmp);
	}
}
