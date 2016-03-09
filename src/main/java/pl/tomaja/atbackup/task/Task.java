package pl.tomaja.atbackup.task;

import pl.tomaja.atbackup.TaskParams;

import java.io.IOException;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public interface Task {

    TaskResult execute(TaskParams params) throws IOException;

}
