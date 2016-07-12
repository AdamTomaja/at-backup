package pl.tomaja.atbackup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import pl.tomaja.atbackup.events.Event;
import pl.tomaja.atbackup.io.IOHolder;
import pl.tomaja.atbackup.params.ArgumentException;
import pl.tomaja.atbackup.params.ParametersParser;
import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.TaskFactory;
import pl.tomaja.atbackup.task.TaskResult;

/**
 * @author Adam Tomaja
 */
public class App {

	private static final Logger LOGGER = Logger.getLogger(App.class);

	private static final ParametersParser parser = new ParametersParser();

	public static void main(String[] args) {
		try {
			run(parser.parse(args));
			LOGGER.info("All jobs finished.");
		} catch (ParseException e) {
			LOGGER.error("Error during arguments parsing", e);
		} catch (ArgumentException e) {
			LOGGER.error("Invalid arguments provided: " + e.getMessage());
			parser.showHelp();
		}
	}

	public static void run(TaskParams params) {
		try {
			setIoImplementation(params);
			setLogLevel(params);
			TaskFactory taskFactory = new TaskFactory();
			LOGGER.info("Starting jobs...");
			TaskResult result = taskFactory.create(params).execute(params);
			showResult(result);
			LOGGER.info("All jobs finished.");
		} catch (IOException e) {
			LOGGER.error("IO error", e);
		}
	}

	private static void setIoImplementation(TaskParams params) {
		params.getIo().ifPresent(io -> IOHolder.setFromString(io));
	}

	private static void setLogLevel(TaskParams params) {
		params.getLogLevel().ifPresent(logLevel -> {
			LogManager.getRootLogger().setLevel(Level.toLevel(logLevel));
		});
	}

	private static Map<Class<?>, Integer> countEvents(TaskResult result) {
		Map<Class<?>, Integer> results = new HashMap<>();
		result.getEvents().forEach(ev -> {
			Class<? extends Event> eventClass = ev.getClass();
			Integer old = results.get(eventClass);
			if (old == null) {
				old = 0;
			}

			old++;
			results.put(eventClass, old);
		});
		return results;
	}

	private static void showResult(TaskResult result) {
		LOGGER.debug("All events listing:");
		result.getEvents().forEach(e -> {
			LOGGER.debug(String.format("%s: %s", e.getClass().getSimpleName(), e.getText()));
		});

		if (result.getEvents().isEmpty()) {
			LOGGER.info("No events recorded");
		} else {
			LOGGER.info("Events counts:");
			countEvents(result).entrySet().forEach(es -> {
				LOGGER.info(String.format("%s: %d", es.getKey().getSimpleName(), es.getValue()));
			});
		}
	}
}
