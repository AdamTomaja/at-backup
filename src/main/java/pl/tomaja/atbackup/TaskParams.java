package pl.tomaja.atbackup;

import java.io.File;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public class TaskParams {

    private final File source;

    private final File target;

    public TaskParams(File source, File target) {
        this.source = source;
        this.target = target;
    }

    public File getTarget() {
        return target;
    }

    public File getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "TaskParams{" +
                "source=" + source +
                ", target=" + target +
                '}';
    }
}
