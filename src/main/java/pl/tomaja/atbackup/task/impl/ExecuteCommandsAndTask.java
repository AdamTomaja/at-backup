package pl.tomaja.atbackup.task.impl;

import pl.tomaja.atbackup.config.Config;
import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

import java.io.IOException;

/**
 * @author Adam Tomaja
 */
public class ExecuteCommandsAndTask implements Task {

	private final Task task;

	public ExecuteCommandsAndTask(Task task) {
		this.task = task;
	}

	@Override
	public TaskResult execute(TaskParams params) throws IOException {
		TaskResult result = new TaskResult();
		Config config = params.getConfig().get();

		Task beforeCommand = new ExecuteCommand(config.getBeforeCommand());
		Task afterCommand = new ExecuteCommand(config.getAfterCommand());

		result.addEvents(beforeCommand.execute(params));
		result.addEvents(task.execute(params));
		result.addEvents(afterCommand.execute(params));

		return result;
	}
}
