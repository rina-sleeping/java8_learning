package ch8.ex15;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Grepper {
	private String target;
	private Path path;

	Grepper(String target, String path) {
		this.target = target;
		this.path = Paths.get(path);
	}

	List<String> grep() {
		if (Files.isDirectory(path)) {
			try (Stream<Path> entries = Files.list(path)) {
				List<String> ret = new LinkedList<String>();
				entries.forEach(p -> {
					ret.addAll(grepLines(p));
				});
				return ret;
			} catch (IOException e) {
				e.printStackTrace();
				return Collections.emptyList();
			}
		} else {
			return grepLines(path);
		}
	}

	private List<String> grepLines(Path filePath) {
		if (!Files.isRegularFile(filePath)) {
			return Collections.emptyList();
		}
		try (Stream<String> lines = Files.lines(filePath)) {
			return lines.filter(Pattern.compile(target).asPredicate()).collect(
					Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UncheckedIOException e) {
			// not readable file, let's skip
		}
		return Collections.emptyList();
	}
}
