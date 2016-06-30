package pl.tomaja.atbackup.task.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import pl.tomaja.atbackup.events.CopyEvent;
import pl.tomaja.atbackup.io.IOFacade;
import pl.tomaja.atbackup.io.IOHolder;
import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public class CopyNewOrModified implements Task {

    private static final Logger LOGGER = Logger.getLogger(CopyNewOrModified.class);

    private final IOFacade io = IOHolder.get();
    
    private int count;
    
    public TaskResult execute(TaskParams params) throws IOException {
        count = 0;

        TaskResult result = new TaskResult();

        checkParams(params);
        doDirectory(params, result, "");

        return result;
    }

    private void doDirectory(TaskParams params, TaskResult result, String current) throws IOException {
        File currentSource = new File(params.getSource(), current);
        LOGGER.debug("Current source: " + currentSource);

        String[] childs = io.list(currentSource);
        if(childs == null) {
            return;
        }

        for(String child : childs) {
            File childFile = new File(currentSource, child);
            LOGGER.debug("Current child: " + childFile);
            String currentPathFilename = current + File.separator + child;
            if(io.isDirectory(childFile)) {
                doDirectory(params, result, currentPathFilename);
            } else {
                File targetFile = new File(params.getTarget(), currentPathFilename);
                LOGGER.debug("Target file: " + targetFile);

                if(!io.exists(targetFile) || io.lastModified(targetFile) < io.lastModified(childFile)) {
                    LOGGER.info("Copying; " + currentPathFilename);
                    io.copyFile(childFile, targetFile);
                    result.addEvent(new CopyEvent(currentPathFilename));
                }
            }

            count++;
            if(count % 5000 == 0) {
                LOGGER.info(String.format("%d files and directories analyzed", count));
            }
        }
    }

    private void checkParams(TaskParams params) {
        checkDir(params.getSource());
        checkDir(params.getTarget());
    }

    private void checkDir(File file) {
        if(!(io.exists(file) && io.isDirectory(file))) {
            throw new RuntimeException(String.format("%s does not exist or is not a directory", file));
        }
    }
}
