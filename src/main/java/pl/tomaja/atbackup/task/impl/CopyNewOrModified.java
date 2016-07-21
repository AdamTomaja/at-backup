package pl.tomaja.atbackup.task.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import pl.tomaja.atbackup.events.CopyEvent;
import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

/**
 * @author Adam Tomaja
 */
public class CopyNewOrModified extends AbstractTask implements Task {

    private static final Logger LOGGER = Logger.getLogger(CopyNewOrModified.class);
    
    private int count;
    
    public TaskResult execute(TaskParams params) throws IOException {
        count = 0;

        TaskResult result = new TaskResult();

        handleParams(params);
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
                    LOGGER.info("Copying: " + currentPathFilename);
                    if(io.copyFile(childFile, targetFile)) {
                    	result.addEvent(new CopyEvent(currentPathFilename));
                    }
                }
            }

            count++;
            if(count % 5000 == 0) {
                LOGGER.info(String.format("%d files and directories analyzed", count));
            }
        }
    }
}
