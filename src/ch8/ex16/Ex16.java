package ch8.ex16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex16 {
	public static List<Address> analyzeData(String path) {
		Path p = Paths.get(path);
		if (Files.isDirectory(p)) {
			return Collections.emptyList();
		}
		Pattern pattern = Pattern
				.compile("(?<zipCode>\\d{5}|\\d{9}),\\s*(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2})\\s*");

		try (Stream<String> lines = Files.lines(p)) {
			return lines
					.filter(pattern.asPredicate())
					.map(l -> {
						Matcher matcher = pattern.matcher(l);
						matcher.matches();
						return new Address(matcher.group("city"), matcher
								.group("state"), matcher.group("zipCode"));
					}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
