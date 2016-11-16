package ch.ffhs.hdo.infrastructure.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

	Logger logger;

	public static Log getInstance(Class<?> componentClass) {

		return new Log(componentClass);
	}

	private Log(String name) {
		logger = Logger.getLogger(name);
	}

	private Log(Class<?> componentClass) {
		logger = Logger.getLogger(componentClass.getName());
	}

	public static Log getLogger(String name) {
		return new Log(name);
	}

	public void error(String message, Throwable t) {
		logger.log(Level.SEVERE, message, t);
	}

	public void debug(String message, Throwable t) {
		logger.log(Level.FINEST, message, t);
	}

	public void warning(String message, Throwable t) {
		logger.log(Level.WARNING, message, t);
	}
}
