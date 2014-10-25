package ch4.ex02;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class ManyProperties {
	private String message;
	private StringProperty messageProperty;

	private String input;
	private StringProperty inputProperty;

	public final StringProperty messageProperty() {
		if (messageProperty == null) {
			if (inputProperty == null) {
				inputProperty();
			}
			messageProperty = new SimpleStringProperty(message) {
				{
					inputProperty.addListener(new InvalidationListener() {
						@Override
						public void invalidated(Observable o) {
							fireValueChangedEvent();
						}
					});
				}

				@Override
				public String get() {
					return getInput();
				}

			};
		}
		return messageProperty;
	}

	public final void setMessage(String newValue) {
		message = newValue;
		if (messageProperty != null) {
			messageProperty.set(message);
		}
	}

	public final String getMessage() {
		if (messageProperty != null) {
			return messageProperty.get();
		}
		return message;
	}

	public final StringProperty inputProperty() {
		if (inputProperty == null) {
			inputProperty = new SimpleStringProperty(input);
		}
		return inputProperty;
	}

	public final void setOnput(String newValue) {
		input = newValue;
		if (inputProperty != null) {
			inputProperty.set(input);
		}
	}

	public final String getInput() {
		if (inputProperty != null) {
			return inputProperty.get();
		}
		return input;
	}

}
