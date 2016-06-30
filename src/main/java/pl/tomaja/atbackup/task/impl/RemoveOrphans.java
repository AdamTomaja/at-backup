package pl.tomaja.atbackup.task.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import pl.tomaja.atbackup.events.DeleteEvent;
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
		doDirectory(params, result, "");
		return result;
	}
	
	private void doDirectory(TaskParams params, TaskResult result, String current) {
		for(String childString : io.list(new File(params.getTarget(), current))) {
			final String childRelativeName = current + File.separator + childString;
			final File sourceFile = new File(params.getSource(), childRelativeName);
			final File targetFile = new File(params.getTarget(), childRelativeName);
			if(!io.exists(sourceFile)) {
				io.deleteQuietly(targetFile);
				result.addEvent(new DeleteEvent(targetFile));
			} else {
				if(io.type(sourceFile) != io.type(targetFile)) {
					io.deleteQuietly(targetFile);
					result.addEvent(new DeleteEvent(targetFile));
				} else if(io.isDirectory(targetFile)) {
					doDirectory(params, result, childRelativeName);
				}
			}
		}
	}
}
