package ch4.ex1;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

import java.io.UncheckedIOException;

import javafx.scene.Parent;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class HelloWorldTest extends GuiTest {

	private HelloWorld app;

	@Override
	public Parent getRootNode() throws UncheckedIOException {
		app = new HelloWorld();
		return app.getRoot();
	}

	@Test
	public void initialTest() {
		verifyThat("#input", hasText("Hello, JavaFX!"));
		verifyThat("#msg", hasText("Hello, JavaFX!"));
	}

	@Test
	public void inputTest() {
		type("a");

		verifyThat("#input", hasText("a"));
		verifyThat("#msg", hasText("a"));

	}
}
