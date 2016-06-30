package pl.tomaja.atbackup.task;

import java.io.IOException;

import pl.tomaja.atbackup.params.TaskParams;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public interface Task {

    TaskResult execute(TaskParams params) throws IOException;

}
