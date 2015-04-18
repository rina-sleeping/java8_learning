package ex;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class ScheduleTimerTest extends GuiTest {

	@Test
	public void test() {
		verifyThat("#nextTime", hasText("--Žž--•ª"));
		click("#addTime");
		type("2020/11/09 12:00");
		click("#add");

		verifyThat("#nextTime", hasText("2020/11/09 12:00"));

	}

	@Override
	protected Parent getRootNode() {
		try {
			return FXMLLoader.load(getClass().getResource("scheduler.fxml"));
		} catch (IOException e) {
			return null;
		}
	}

}
