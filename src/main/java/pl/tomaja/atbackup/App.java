package pl.tomaja.atbackup;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import pl.tomaja.atbackup.task.SynchronizationTask;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

import java.io.IOException;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);

    private static final ParametersParser parser = new ParametersParser();

    public static void main(String[] args) {

        try {
            TaskParams params = parser.parse(args);
            Task task = new SynchronizationTask();
            TaskResult result = task.execute(params);
            LOGGER.info("Task result: " + result);
        } catch (ParseException e) {
            LOGGER.error("Error during args parsing", e);
        } catch (IOException e) {
            LOGGER.error("IO error", e);
        }
    }
}
