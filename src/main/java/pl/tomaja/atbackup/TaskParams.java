package pl.tomaja.atbackup;

import pl.tomaja.atbackup.config.Config;

import java.io.File;
import java.util.Optional;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public class TaskParams {

    private final File source;

    private final File target;

    private final Optional<Config> config;

    private final Optional<Long> loopInterval;

    public TaskParams(File source, File target, Optional<Config> config, Optional<Long> loopInterval) {
        this.source = source;
        this.target = target;
        this.config = config;
        this.loopInterval = loopInterval;
    }

    public File getTarget() {
        return target;
    }

    public File getSource() {
        return source;
    }

    public Optional<Config> getConfig() {
        return config;
    }

    public Optional<Long> getLoopInterval() {
        return loopInterval;
    }

    @Override
    public String toString() {
        return "TaskParams{" +
                "source=" + source +
                ", target=" + target +
                '}';
    }
}
