package ch5.ex12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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

import org.reactfx.util.FxTimer;
import org.reactfx.util.Timer;

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
	private Timer timer = null;
	private Instant next;
	private final String NO_NEXT_TIME = "--Žž--•ª";
	private final String DATA_PATH = "src/ch5/ex12/schedules.txt";
	private final DateTimeFormatter formatter = DateTimeFormatter
			.ofPattern("yyyy/MM/dd HH:mm");
	private Logger log = Logger.getLogger("ch15/ex12/ScheduleTimer");

	public void initialize(URL url, ResourceBundle rb) {
		timeZone.setItems(FXCollections.observableArrayList(ZoneId
				.getAvailableZoneIds()));
		timeZone.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(property, oldValue, newValue) -> {
							zoneId = ZoneId.of(newValue);
							sche.setZoneId(zoneId);
							if (next != null) {
								nextTime.setText(formatter.format(next
										.atZone(zoneId)));
							}
						});
		timeZone.setValue(ZoneId.systemDefault().toString());
		zoneId = ZoneId.systemDefault();
		addTimeZone.setItems(FXCollections.observableArrayList(ZoneId
				.getAvailableZoneIds()));
		addTimeZone.setValue(ZoneId.systemDefault().toString());
		zoneId = ZoneId.systemDefault();
		add.setOnAction(this::addSchedule);
		del.setOnAction(this::delSchedule);
		save.setOnAction(this::saveSchedule);
		list.getSelectionModel().selectedItemProperty()
				.addListener((property, oldValue, newValue) -> {
					selectedList = newValue;
				});

		initSchedule();

		next = sche.nextFrom(ZonedDateTime.now(zoneId).plusHours(1)
				.toLocalDateTime());
		if (next != null) {
			nextTime.setText(formatter.format(next.atZone(zoneId)));
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
		updateScheduleList();
		sche.add(time, ZoneId.of(timeZone));

		setNextSchedule();
	}

	private void setNextSchedule() {
		ZonedDateTime now = ZonedDateTime.now(zoneId);
		next = sche.nextFrom(now.plusHours(1).toLocalDateTime());
		if (next == null) {
			nextTime.setText(NO_NEXT_TIME);
			return;
		}
		nextTime.setText(formatter.format(next.atZone(zoneId)));

		setTimer(Duration.between(
				now.toInstant(),
				ZonedDateTime.of(next.atZone(zoneId).toLocalDateTime()
						.minusHours(1), zoneId)).abs());

	}

	private void delSchedule(ActionEvent e) {
		if (selectedList == null) {
			return;
		}
		Matcher m = Pattern.compile("^(.+)\\sin\\s(.+)$").matcher(selectedList);

		if (!m.find()) {
			log.log(Level.INFO, selectedList);
			return;
		}

		eList.remove(selectedList);
		updateScheduleList();
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

	private void setTimer(Duration duration) {
		if (duration.isNegative()) {
			duration = duration.abs();
		}

		log.log(Level.INFO, "set Timer:" + duration.toMillis() + "ms");
		if (timer != null) {
			timer.stop();
		}
		timer = FxTimer.runLater(
				duration,
				() -> {
					setNextSchedule();
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
				});
	}

	private void saveSchedule(ActionEvent e) {
		try {
			PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(
					new File(DATA_PATH))));

			eList.forEach(s -> {
				w.println(s.toString());
			});

			w.close();
		} catch (IOException e1) {
			log.log(Level.SEVERE, e1.toString());

		}
	}

	private void initSchedule() {
		try {
			BufferedReader r = new BufferedReader(new FileReader(new File(
					DATA_PATH)));

			for (String line = r.readLine(); line != null; line = r.readLine()) {
				eList.add(line);

				Matcher m = Pattern.compile("^(.+)\\sin\\s(.+)$").matcher(line);
				if (!m.find()) {
					log.log(Level.WARNING, line);
					continue;
				}

				sche.add(parseTime(m.group(1)), ZoneId.of(m.group(2)));
			}
			r.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.toString());

		}

		updateScheduleList();
	}

	private void updateScheduleList() {
		list.setItems(eList.sorted((s1, s2) -> s1.compareTo(s2)));
	}
}
