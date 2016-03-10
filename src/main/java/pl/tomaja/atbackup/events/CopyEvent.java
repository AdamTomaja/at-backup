package pl.tomaja.atbackup.events;

/**
 * Created by Adam Tomaja on 2016-03-10.
 */
public class CopyEvent extends Event {

    private final String path;

    public CopyEvent(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String getText() {
        return path;
    }

    @Override
    public String toString() {
        return "CopyEvent{" +
                "path='" + path + '\'' +
                '}';
    }
}
