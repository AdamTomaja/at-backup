package pl.tomaja.atbackup;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import pl.tomaja.atbackup.task.TaskFactory;
import pl.tomaja.atbackup.task.impl.CopyNewOrModified;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;
import pl.tomaja.atbackup.task.impl.ExecuteCommandsAndTask;
import pl.tomaja.atbackup.task.impl.IntervalTask;

import java.io.IOException;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);

    private static final ParametersParser parser = new ParametersParser();

    public static void main(String[] args) {

        try {
            TaskParams params = parser.parse(args);
            TaskFactory taskFactory = new TaskFactory();
            TaskResult result = taskFactory.create(params).execute(params);
            showResult(result);
        } catch (ParseException e) {
            LOGGER.error("Error during args parsing", e);
        } catch (IOException e) {
            LOGGER.error("IO error", e);
        }
    }

    private static void showResult(TaskResult result) {
        LOGGER.debug("Task results: ");
        result.getEvents().forEach(e -> {
            LOGGER.debug(String.format("%s: %s", e.getClass().getSimpleName(), e.getText()));
        });
    }
}
