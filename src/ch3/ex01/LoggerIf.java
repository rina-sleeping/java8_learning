package ch3.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerIf extends Logger {

	protected LoggerIf(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}

	public void logIf(Level level, BooleanSupplier condition,
			Supplier<String> msg) {

		if (this.getLevel() == null
				|| level.intValue() < this.getLevel().intValue())
			return;

		if (condition.getAsBoolean()) {
			super.log(level, msg.get());
		}
	}
}
