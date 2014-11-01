package ch4.ex10;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

import java.util.concurrent.TimeUnit;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class BrowserTest extends GuiTest {
	private Browser browser;

	@Override
	protected Parent getRootNode() {
		browser = new Browser();
		return browser.getRoot();
	}

	@Test
	public void test() {
		type("http://yahoo.co.jp\n");
		push(KeyCode.ENTER);
		verifyThat("#url", hasText("http://yahoo.co.jp"));
		sleep(5, TimeUnit.SECONDS);

		click("#url");
		push(KeyCode.CONTROL, KeyCode.A);
		push(KeyCode.DELETE);
		type("http://google.co.jp\n");
		verifyThat("#url", hasText("http://google.co.jp"));
		sleep(1, TimeUnit.SECONDS);

		click("#back");
		verifyThat("#url", hasText("http://www.yahoo.co.jp/"));
		sleep(1, TimeUnit.SECONDS);
	}
}
