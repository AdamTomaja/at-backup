package pl.tomaja.atbackup.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Adam Tomaja
 */
@XmlRootElement
public class Config {

    @XmlElement
    private String beforeCommand;

    @XmlElement
    private String afterCommand;

    public String getAfterCommand() {
        return afterCommand;
    }

    public String getBeforeCommand() {
        return beforeCommand;
    }
}
