package ch8.ex11;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Ex11 {
	public static void main(String[] args) {
		final String urlString = "";// where?
		final String username = "fred";
		final String password = "seacret";

		try {
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();

			Base64.Encoder encoder = Base64.getEncoder();
			String original = username + ":" + password;
			String encoded = encoder.encodeToString(original
					.getBytes(StandardCharsets.UTF_8));

			connection.setRequestProperty("Authoization", "Basic" + encoded);

			connection.connect();
			try (InputStream input = connection.getInputStream()) {
				System.out.println("read start:");
				while (true) {
					int ch = input.read();
					if (ch < 0) {
						break;
					}
					System.out.printf("%s", ch);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
