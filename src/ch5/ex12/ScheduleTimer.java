package ch5.ex12;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScheduleTimer extends Application implements Initializable {
	@FXML
	private ListView<String> list;
	private String selectedList;
	private ObservableList<String> eList = FXCollections.observableArrayList();
	@FXML
	private ChoiceBox<String> timeZone;
	private ZoneId zoneId;
	@FXML
	private ChoiceBox<String> addTimeZone;
	@FXML
	private TextField addTime;
	@FXML
	private Label nextTime;
	@FXML
	private Button add;
	@FXML
	private Button del;
	@FXML
	private Button save;

	private Scheduler sche = new Scheduler();
	private Timer timer = new Timer();
	private Logger log = Logger.getLogger("ch15/ex12/ScheduleTimer");

	public void initialize(URL url, ResourceBundle rb) {
		timeZone.setItems(FXCollections.observableArrayList(ZoneId
				.getAvailableZoneIds()));
		timeZone.getSelectionModel().selectedItemProperty()
				.addListener((property, oldValue, newValue) -> {
					zoneId = ZoneId.of(newValue);
					sche.setZoneId(zoneId);
				});
		timeZone.setValue(ZoneId.systemDefault().toString());
		zoneId = ZoneId.systemDefault();
		addTimeZone.setItems(FXCollections.observableArrayList(ZoneId
				.getAvailableZoneIds()));
		addTimeZone.setValue(ZoneId.systemDefault().toString());
		zoneId = ZoneId.systemDefault();
		add.setOnAction(this::addSchedule);
		del.setOnAction(this::delSchedule);
		list.getSelectionModel().selectedItemProperty()
				.addListener((property, oldValue, newValue) -> {
					selectedList = newValue;
				});

		// TODO data load and add to sche

		Instant next = sche.nextFrom(LocalDateTime.now());
		if (next != null) {
			nextTime.setText(next.toString());
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(
					"scheduler.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}

	}

	private void addSchedule(ActionEvent e) {
		String timeZone = addTimeZone.getValue();
		LocalDateTime time = parseTime(addTime.getText());
		if (timeZone == "null" || time == null) {
			return;
		}

		String tmp = addTime.getText() + " in " + timeZone;
		if (eList.contains(tmp)) {
			return;
		}

		eList.add(tmp);
		list.setItems(eList);
		sche.add(time, ZoneId.of(timeZone));

		Instant next = sche.nextFrom(LocalDateTime.now());
		if (next != null) {
			nextTime.setText(next.toString());
		}

		setTimer(next.atZone(zoneId).toLocalDateTime().minusHours(1));

	}

	private void delSchedule(ActionEvent e) {
		Matcher m = Pattern.compile("^(.+)\\sin\\s(.+)$").matcher(selectedList);

		if (!m.find()) {
			log.log(Level.INFO, selectedList);
			return;
		}

		eList.remove(selectedList);
		list.setItems(eList);
		sche.delete(parseTime(m.group(1)), ZoneId.of(m.group(2)));

	}

	/**
	 * 
	 * @param input
	 * @return if input is invalid format, return null.
	 */
	private LocalDateTime parseTime(String input) {
		Matcher m = Pattern.compile(
				"(\\d\\d\\d\\d)/(\\d\\d)/(\\d\\d)\\s(\\d\\d):(\\d\\d)")
				.matcher(input);

		if (!m.find()) {
			log.log(Level.INFO, input);
			return null;
		}

		LocalDateTime local = null;
		try {
			local = LocalDateTime.of(Integer.parseInt(m.group(1)),
					Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)),
					Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)));
		} catch (Exception e) {
			log.log(Level.INFO, e.toString());
		}

		return local;
	}

	private void save(ActionEvent e) {

	}

	private void setTimer(LocalDateTime setTime) {
		Duration duration = Duration.between(Instant.now(),
				ZonedDateTime.of(setTime, zoneId));
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					Parent root = FXMLLoader.load(getClass().getResource(
							"alarm.fxml"));
					Scene scene = new Scene(root);
					Stage alarmDialog = new Stage(StageStyle.UTILITY);
					alarmDialog.setScene(scene);
					alarmDialog.initModality(Modality.WINDOW_MODAL);
					alarmDialog.show();
				} catch (IOException ex) {
					ex.printStackTrace();
					System.exit(0);
				}
			}
		}, duration.toMillis());
	}
}
