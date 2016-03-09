package pl.tomaja.atbackup;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public class SynchronizationTask implements Task {

    private static final Logger LOGGER = Logger.getLogger(SynchronizationTask.class);

    public void execute(TaskParams params) throws IOException {
        checkParams(params);
        doDirectory(params, "");
    }

    private void doDirectory(TaskParams params, String current) throws IOException {
        File currentSource = new File(params.getSource(), current);
        LOGGER.debug("Current source: " + currentSource);

        String[] childs = currentSource.list();
        for(String child : childs) {
            File childFile = new File(currentSource, child);
            LOGGER.debug("Current child: " + childFile);
            String currentPathFilename = current + File.separator + child;
            if(childFile.isDirectory()) {
                doDirectory(params, currentPathFilename);
            } else {
                File targetFile = new File(params.getTarget(), currentPathFilename);
                LOGGER.debug("Target file: " + targetFile);

                if(!targetFile.exists() || targetFile.lastModified() < childFile.lastModified()) {
                    LOGGER.debug("Copying; " + currentPathFilename);
                    FileUtils.copyFile(childFile, targetFile);
                }
            }
        }
    }

    private void checkParams(TaskParams params) {
        checkDir(params.getSource());
        checkDir(params.getTarget());
    }

    private void checkDir(File file) {
        if(!(file.exists() && file.isDirectory())) {
            throw new RuntimeException(String.format("%s does not exist or is not a directory", file));
        }
    }
}
