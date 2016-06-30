package pl.tomaja.atbackup.task.impl;

import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

import java.io.IOException;

/**
 * Created by Adam Tomaja on 2016-03-12.
 */
public class ExecuteCommandsAndTask implements Task {

    private final Task task;

    public ExecuteCommandsAndTask(Task task) {
        this.task = task;
    }

    @Override
    public TaskResult execute(TaskParams params) throws IOException {
        TaskResult result = new TaskResult();
        Task beforeCommand = new ExecuteCommand(params.getConfig().get().getBeforeCommand());
        Task afterCommand = new ExecuteCommand(params.getConfig().get().getAfterCommand());

        result.addEvents(beforeCommand.execute(params));
        result.addEvents(task.execute(params));
        result.addEvents(afterCommand.execute(params));

        return result;
    }
}
