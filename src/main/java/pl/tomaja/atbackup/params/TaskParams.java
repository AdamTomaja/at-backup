package pl.tomaja.atbackup.params;

import pl.tomaja.atbackup.config.Config;

import java.io.File;
import java.util.Optional;

/**
 * @author Adam Tomaja
 */
public class TaskParams {

    private File source;

    private File target;

    private Optional<Config> config;

    private Optional<Long> loopInterval;

    private Optional<String> logLevel;

    private Optional<String> mode;

    private Optional<String> io;

    public TaskParams(File source,
                      File target,
                      Optional<Config> config,
                      Optional<Long> loopInterval,
                      Optional<String> logLevel,
                      Optional<String> mode,
                      Optional<String> io) {
        this.source = source;
        this.target = target;
        this.config = config;
        this.loopInterval = loopInterval;
        this.logLevel = logLevel;
        this.mode = mode;
        this.io = io;
    }

    public TaskParams() {
    }

    public void setSource(File source) {
        this.source = source;
    }

    public void setTarget(File target) {
        this.target = target;
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

    public Optional<String> getLogLevel() {
        return logLevel;
    }

    public Optional<String> getMode() {
        return mode;
    }

    public Optional<String> getIo() {
        return io;
    }

    @Override
    public String toString() {
        return "TaskParams [source=" + source + ", target=" + target + ", config=" + config + ", loopInterval="
                + loopInterval + ", logLevel=" + logLevel + ", mode=" + mode + "]";
    }
}
