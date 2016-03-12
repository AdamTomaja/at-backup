package pl.tomaja.atbackup;

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
            LOOP_OPTION = "l";

    private final Options options = createOptions();
    private final CommandLineParser parser = new DefaultParser();

    private Options createOptions() {
        Options options = new Options();
        options.addOption(SOURCE_OPTION, true, "Source directory");
        options.addOption(TARGET_OPTION, true, "Target directory");
        options.addOption(CONFIG_OPTION, true, "Config file path");
        options.addOption(LOOP_OPTION, true, "Loop interval");
        return options;
    }

    public TaskParams parse(String[] args) throws ParseException {
        CommandLine cmd = parser.parse(options, args);

        if (!cmd.hasOption(SOURCE_OPTION)) {
            throw new RuntimeException("No source parameter!");
        }

        if (!cmd.hasOption(TARGET_OPTION)) {
            throw new RuntimeException("No target parameter!");
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

        return new TaskParams(source, target, config, interval);
    }
}
