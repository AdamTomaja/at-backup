package pl.tomaja.atbackup.task;

import pl.tomaja.atbackup.TaskParams;
import pl.tomaja.atbackup.task.impl.CopyNewOrModified;
import pl.tomaja.atbackup.task.impl.ExecuteCommandsAndTask;
import pl.tomaja.atbackup.task.impl.IntervalTask;

/**
 * Created by Adam Tomaja on 2016-03-12.
 */
public class TaskFactory {

    public Task create(TaskParams params) {
        Task task;

        if (params.getConfig().isPresent()) {
            task = new ExecuteCommandsAndTask(new CopyNewOrModified());
        } else {
            task = new CopyNewOrModified();
        }

        if (params.getLoopInterval().isPresent()) {
            task = new IntervalTask(params.getLoopInterval().get(), task);
        }

        return task;
    }
}
