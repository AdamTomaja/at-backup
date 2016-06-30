package pl.tomaja.atbackup.task.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

/**
 * Created by Adam Tomaja
 */
public class RemoveOrphans implements Task {

	private static final Logger LOGGER = Logger.getLogger(RemoveOrphans.class);
	
	@Override
	public TaskResult execute(TaskParams params) throws IOException {
		TaskResult result = new TaskResult();
		LOGGER.info("Removing Orphans...");
		return result;
	}
}
