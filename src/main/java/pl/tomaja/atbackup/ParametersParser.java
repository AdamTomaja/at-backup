package pl.tomaja.atbackup;

import org.apache.commons.cli.*;

import java.io.File;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public class ParametersParser {

    public static final String SOURCE_OPTION = "s";
    public static final String TARGET_OPTION = "t";

    private final Options options = createOptions();
    private final CommandLineParser parser = new DefaultParser();

    private Options createOptions() {
        Options options = new Options();
        options.addOption(SOURCE_OPTION, true, "Source directory");
        options.addOption(TARGET_OPTION, true, "Target directory");
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
        return new TaskParams(source, target);
    }
}
