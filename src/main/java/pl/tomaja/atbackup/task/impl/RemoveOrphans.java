package pl.tomaja.atbackup.task.impl;

import org.apache.log4j.Logger;
import pl.tomaja.atbackup.events.DeleteEvent;
import pl.tomaja.atbackup.io.FileType;
import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

import java.io.File;
import java.io.IOException;

/**
 * @author Adam Tomaja
 */
public class RemoveOrphans extends AbstractTask implements Task {

    private static final Logger LOGGER = Logger.getLogger(RemoveOrphans.class);

    @Override
    public TaskResult execute(TaskParams params) throws IOException {
        TaskResult result = new TaskResult();
        LOGGER.info("Removing Orphans...");
        handleParams(params);
        doDirectory(params, result, "");
        return result;
    }

    private void doDirectory(TaskParams params, TaskResult result, String currentRelative) throws IOException {
        for (String childString : io.list(new File(params.getTarget(), currentRelative))) {
            final String childRelativeName = currentRelative + File.separator + childString;
            final File sourceFile = new File(params.getSource(), childRelativeName);
            final File targetFile = new File(params.getTarget(), childRelativeName);
            final FileType targetType = io.type(targetFile);

            if (shouldBeDeleted(sourceFile, targetType)) {
                try {
                    LOGGER.info("Removing: " + targetFile);
                    delete(result, targetFile);
                } catch (IOException e) {
                    LOGGER.error("Failed to remove", e);
                }
            } else if (targetType == FileType.DIRECTORY) {
                doDirectory(params, result, childRelativeName);
            }
        }
    }

    private boolean shouldBeDeleted(final File sourceFile, final FileType targetType) {
        return !io.exists(sourceFile) || !io.type(sourceFile).equals(targetType);
    }

    private void delete(TaskResult result, final File targetFile) throws IOException {
        if (io.deleteQuietly(targetFile)) {
            result.addEvent(new DeleteEvent(targetFile));
        }
    }
}
