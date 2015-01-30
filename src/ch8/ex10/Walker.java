package ch8.ex10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Walker {
	public static Path[] search(String path, String... target)
			throws IOException {
		try (Stream<Path> entries = Files.walk(Paths.get(path))) {
			return entries.filter(p -> {
				if (!p.toString().endsWith("java")) {
					return false;
				}
				try (Stream<String> lines = Files.lines(p)) {
					return lines.anyMatch(line -> {
						for (String t : target) {
							if (line.contains(t)) {
								return true;
							}
						}
						return false;
					});
				} catch (IOException e) {
					return false;
				}
			}).toArray(Path[]::new);
		}
	}
}
