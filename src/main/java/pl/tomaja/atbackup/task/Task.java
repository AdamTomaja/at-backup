package pl.tomaja.atbackup.task;

import java.io.IOException;

import pl.tomaja.atbackup.params.TaskParams;

/**
 * @author Adam Tomaja
 */
public interface Task {

    TaskResult execute(TaskParams params) throws IOException;

}
