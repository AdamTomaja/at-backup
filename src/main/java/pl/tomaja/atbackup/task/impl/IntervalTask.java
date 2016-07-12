package pl.tomaja.atbackup.task.impl;

import org.apache.log4j.Logger;

import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

import java.io.IOException;

/**
 * @author Adam Tomaja
 */
public class IntervalTask implements Task {

    private static final Logger LOGGER = Logger.getLogger(IntervalTask.class);

    private final long interval;

    private final Task task;

    public IntervalTask(long interval, Task task) {
        this.interval = interval;
        this.task = task;
    }

    @Override
    public TaskResult execute(TaskParams params) throws IOException {
        while(true) {
            try {
                task.execute(params);
                LOGGER.debug("Waiting " + interval + " miliseconds");
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
