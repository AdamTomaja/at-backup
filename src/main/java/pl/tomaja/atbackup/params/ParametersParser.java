package pl.tomaja.atbackup.params;

import org.apache.commons.cli.*;
import pl.tomaja.atbackup.config.Config;
import pl.tomaja.atbackup.config.ConfigFactory;

import java.io.File;
import java.util.Optional;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public class ParametersParser {

    public static final String SOURCE_OPTION = "s",
            TARGET_OPTION = "t",
            CONFIG_OPTION = "c",
            LOOP_OPTION = "l",
            MODE_OPTION = "mode",
    		LOG_LEVEL_OPTION = "log";

    private final Options options = createOptions();
    private final CommandLineParser parser = new DefaultParser();

    private Options createOptions() {
        Options options = new Options();
        options.addOption(SOURCE_OPTION, true, "Source directory");
        options.addOption(TARGET_OPTION, true, "Target directory");
        options.addOption(CONFIG_OPTION, true, "Config file path - optional");
        options.addOption(LOOP_OPTION, true, "Loop interval - optional");
        options.addOption(LOG_LEVEL_OPTION, true, "Log level - optional");
        options.addOption(MODE_OPTION, true, "Synchronization mode - optional");
        return options;
    }
    
    public void showHelp() {
    	HelpFormatter formatter = new HelpFormatter();
    	formatter.printHelp("java -jar at-backup.jar", options);
    }

    public TaskParams parse(String[] args) throws ParseException {
        CommandLine cmd = parser.parse(options, args);

        if (!cmd.hasOption(SOURCE_OPTION)) {
            throw new ArgumentException("No source parameter!");
        }

        if (!cmd.hasOption(TARGET_OPTION)) {
            throw new ArgumentException("No target parameter!");
        }

        File source = new File(cmd.getOptionValue(SOURCE_OPTION));
        File target = new File(cmd.getOptionValue(TARGET_OPTION));

        Optional<Config> config = Optional.empty();

        if(cmd.hasOption(CONFIG_OPTION)) {
            ConfigFactory configFactory = new ConfigFactory();
            config = Optional.of(configFactory.load(new File(cmd.getOptionValue(CONFIG_OPTION))));
        }

        Optional<Long> interval = Optional.empty();

        if(cmd.hasOption(LOOP_OPTION)) {
            interval = Optional.of(Long.parseLong(cmd.getOptionValue(LOOP_OPTION)));
        }

        Optional<String> logLevel = Optional.ofNullable(cmd.getOptionValue(LOG_LEVEL_OPTION));
        
        Optional<String> mode = Optional.ofNullable(cmd.getOptionValue(MODE_OPTION));
        
		return new TaskParams(source, target, config, interval, logLevel, mode);
    }
}
