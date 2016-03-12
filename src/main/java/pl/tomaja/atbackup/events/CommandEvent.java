package pl.tomaja.atbackup.events;

/**
 * Created by Adam Tomaja on 2016-03-12.
 */
public class CommandEvent extends Event {

    private final int exitValue;

    private final String result;

    public CommandEvent(int exitValue, String result) {
        this.exitValue = exitValue;
        this.result = result;
    }

    public int getExitValue() {
        return exitValue;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String getText() {
        return "Command result: " + result;
    }
}
