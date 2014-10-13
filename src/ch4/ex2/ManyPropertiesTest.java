package ch4.ex2;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

import java.io.UncheckedIOException;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class ManyPropertiesTest extends GuiTest {

	private ManyProperties properties;

	@Override
	public Parent getRootNode() throws UncheckedIOException {
		properties = new ManyProperties();

		VBox root = new VBox();
		String initial = "Hello, JavaFX!";

		Label message = new Label(initial);
		message.setFont(new Font(100));
		message.setId("msg");

		TextField input = new TextField(initial);
		input.setId("input");

		properties.inputProperty().bindBidirectional(input.textProperty());
		properties.messageProperty().bind(properties.inputProperty());
		message.textProperty().bind(properties.messageProperty());
		root.getChildren().addAll(message, input);
		return root;
	}

	@Test
	public void test() {
		verifyThat("#msg", hasText("Hello, JavaFX!"));

		// prepare
		type("c");

		verifyThat("#msg", hasText("c"));
	}
}
