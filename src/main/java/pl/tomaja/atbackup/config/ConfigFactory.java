package pl.tomaja.atbackup.config;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Adam Tomaja on 2016-03-12.
 */
public class ConfigFactory {

    private static final Logger LOGGER = Logger.getLogger(ConfigFactory.class);

    private JAXBContext context;

    private Unmarshaller unmarshaller;

    public ConfigFactory() {
        try {
            context = JAXBContext.newInstance(Config.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Config load(File file) {
        try {
            Config config = (Config) unmarshaller.unmarshal(file);
            LOGGER.debug("Loaded config from: " + file);
            return config;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
