package ch9.ex07;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Ex07 {
	public static void parseWebPage(String urlStr) throws IOException {

		URL url = new URL(urlStr);

		Files.copy(url.openStream(), Paths.get("./src/ch9/ex07/out.txt"),
				StandardCopyOption.REPLACE_EXISTING);

	}
}
