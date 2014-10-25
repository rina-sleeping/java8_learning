package ch4.ex03;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class ManyProperties {
	private final String messageDefault = "initial";
	private StringProperty messageProperty;

	private final String inputDefault = "initial";
	private StringProperty inputProperty;

	public final StringProperty messageProperty() {
		if (messageProperty == null) {
			if (inputProperty == null) {
				inputProperty();
			}
			messageProperty = new SimpleStringProperty(messageDefault) {
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
		if (messageProperty != null) {
			if (newValue != messageDefault) {
				messageProperty.set(newValue);
			}
		}
	}

	public final String getMessage() {
		if (messageProperty != null) {
			return messageProperty.get();
		}
		return messageDefault;
	}

	public final StringProperty inputProperty() {
		if (inputProperty == null) {
			inputProperty = new SimpleStringProperty(inputDefault);
		}
		return inputProperty;
	}

	public final void setOnput(String newValue) {
		if (inputProperty != null) {
			if (newValue != inputDefault) {
				inputProperty.set(newValue);
			}
		}
	}

	public final String getInput() {
		if (inputProperty != null) {
			return inputProperty.get();
		}
		return inputDefault;
	}

}
