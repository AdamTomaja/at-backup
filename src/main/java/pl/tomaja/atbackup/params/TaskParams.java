package pl.tomaja.atbackup.params;

import java.io.File;
import java.util.Optional;

import pl.tomaja.atbackup.config.Config;

/**
 * @author Adam Tomaja
 */
public class TaskParams {

    private final File source;

    private final File target;

    private final Optional<Config> config;

    private final Optional<Long> loopInterval;
    
    private final Optional<String> logLevel;
    
    private final Optional<String> mode;
    
    private final Optional<String> io;

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
