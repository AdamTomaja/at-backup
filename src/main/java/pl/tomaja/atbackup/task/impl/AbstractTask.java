package pl.tomaja.atbackup.task.impl;

import java.io.File;
import java.util.Objects;

import pl.tomaja.atbackup.io.IOHolder;
import pl.tomaja.atbackup.io.facade.IOFacade;
import pl.tomaja.atbackup.params.TaskParams;

/**
 * @author Adam Tomaja
 */
public class AbstractTask {

    public static final String ROOT_NAME_PREFIX = "@";

    protected IOFacade io = IOHolder.get();
	
	protected void handleParams(TaskParams params) {
	    if(isRootName(params.getSource())) {
            params.setSource(findRoot(getRootName(params.getSource())));
        }

        if(isRootName(params.getTarget())) {
            params.setTarget(findRoot(getRootName(params.getTarget())));
        }

        checkDir(params.getSource());
        checkDir(params.getTarget());
    }

    protected void setIo(IOFacade io) {
        this.io = io;
    }

    protected boolean isRootName(File file) {
        return file.toString().startsWith(ROOT_NAME_PREFIX);
    }

    protected String getRootName(File file) {
        return file.toString().substring(1);
    }

    protected File findRoot(String name) {
        File root = io.findRootByName(name);
        Objects.requireNonNull(root, "Cannot find root: " + name);
        return root;
    }

    protected void checkDir(File file) {
        if(!(io.exists(file) && io.isDirectory(file))) {
            throw new RuntimeException(String.format("%s does not exist or is not a directory", file));
        }
    }
}
