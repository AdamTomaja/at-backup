package pl.tomaja.atbackup.task.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

/**
 * Created by Adam Tomaja
 */
public class RemoveOrphans extends AbstractTask implements Task {

	private static final Logger LOGGER = Logger.getLogger(RemoveOrphans.class);
	
	@Override
	public TaskResult execute(TaskParams params) throws IOException {
		TaskResult result = new TaskResult();
		LOGGER.info("Removing Orphans...");
		checkParams(params);
		doDirectory(result, "");
		return result;
	}
	
	private void doDirectory(TaskResult result, String current) {
		
	}
}
