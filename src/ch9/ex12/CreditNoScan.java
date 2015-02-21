package ch9.ex12;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CreditNoScan extends Applet implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TextField input;
	private TextField output;
	private Label errorMsg;

	@Override
	public void init() {
		setSize(400, 200);
		setLayout(new GridLayout(6, 1));
		add(new Label("Input root directory path to search:"));
		input = (TextField) add(new TextField(
				"C:/Users/Rina/workspace_luna/java8_learning/src/ch9/ex11/test1",
				20));

		add(new Label("Input directory path to save result.txt:"));
		output = (TextField) add(new TextField(
				"C:/Users/Rina/workspace_luna/java8_learning/src/ch9/ex12", 20));

		Button start = (Button) add(new Button("Scan start"));
		start.addActionListener(this);

		errorMsg = (Label) add(new Label());

	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {

	}

	@Override
	public void paint(Graphics g) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		System.out.println(input.getText());

		try {
			searchCreditNum(input.getText(), output.getText());
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
			errorMsg.setText(e1.toString());
		}
	}

	public static void searchCreditNum(String searchRootPath, String outputPath)
			throws IOException, InterruptedException {
		Path result = Paths.get(outputPath + "/result.txt");
		Path tmp = Paths.get(outputPath + "/tmp.txt");

		ProcessBuilder builder = new ProcessBuilder(
				"findstr",
				"/s",
				"/r",
				"[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]",
				searchRootPath + "/*");

		builder.redirectOutput(tmp.toFile());
		Process process = builder.start();
		process.waitFor();

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

	public static void searchCreditNumForLinux(String searchRootPath,
			String outputPath) throws IOException, InterruptedException {
		Path result = Paths.get(outputPath + "/result.txt");

		ProcessBuilder builder = new ProcessBuilder("grep", "-r",
				searchRootPath, "^[^0-9]*\\d{4}-\\d{4}-\\d{4}-\\d{4}[^0-9]*$");

		builder.redirectOutput(result.toFile());

		Process process = builder.start();
		process.waitFor();
	}

}
