package pl.tomaja.atbackup.task.impl;

import java.io.File;

import pl.tomaja.atbackup.io.IOHolder;
import pl.tomaja.atbackup.io.facade.IOFacade;
import pl.tomaja.atbackup.params.TaskParams;

/**
 * Created by Adam Tomaja
 */
public class AbstractTask {
	
	protected IOFacade io = IOHolder.get();
	
	protected void checkParams(TaskParams params) {
        checkDir(params.getSource());
        checkDir(params.getTarget());
    }

    protected void checkDir(File file) {
        if(!(io.exists(file) && io.isDirectory(file))) {
            throw new RuntimeException(String.format("%s does not exist or is not a directory", file));
        }
    }
}
