package pl.tomaja.atbackup.task;

import pl.tomaja.atbackup.mode.ModeTaskFactory;
import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.impl.CopyNewOrModified;
import pl.tomaja.atbackup.task.impl.ExecuteCommandsAndTask;
import pl.tomaja.atbackup.task.impl.IntervalTask;

/**
 * Created by Adam Tomaja on 2016-03-12.
 */
public class TaskFactory {

	private final ModeTaskFactory modeTaskFactory = new ModeTaskFactory();
	
    public Task create(TaskParams params) {
        Task task;

        if(!params.getMode().isPresent()) {
	        if (params.getConfig().isPresent()) {
	            task = new ExecuteCommandsAndTask(new CopyNewOrModified());
	        } else {
	            task = new CopyNewOrModified();
	        }
        } else {
        	task = modeTaskFactory.createTaskFromMode(params.getMode().get());
        }

        if (params.getLoopInterval().isPresent()) {
            task = new IntervalTask(params.getLoopInterval().get(), task);
        }

        return task;
    }
}
