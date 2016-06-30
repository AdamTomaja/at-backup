package pl.tomaja.atbackup.mode;

import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.impl.RemoveOrphans;

/**
 * Created by Adam Tomaja
 */
public class ModeTaskFactory {

	public Task createTaskFromMode(String mode) {
		return new RemoveOrphans();
	}
}
