package pl.tomaja;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);

    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("s", true, "Source directory");
        options.addOption("t", true, "Target directory");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
        } catch (ParseException e) {
            LOGGER.error("Error during args parsing", e);
        }
    }
}
