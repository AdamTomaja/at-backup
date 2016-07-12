package pl.tomaja.atbackup.task.impl;

import pl.tomaja.atbackup.events.CommandEvent;
import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.TaskResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Adam Tomaja
 */
public class ExecuteCommand implements Task {

    private final String command;

    public ExecuteCommand(String command) {
         this.command = command;
    }

    @Override
    public TaskResult execute(TaskParams params) throws IOException {
        TaskResult result = new TaskResult();
        CommandEvent event = execute(command);
        if(event.getExitValue() != 0) {
            throw new RuntimeException("Proces returned non zero value! " + event.getResult());
        }
        return result;
    }

    private CommandEvent execute(String command) {
        try {
            final Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            final StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            reader.close();
            return new CommandEvent(p.exitValue(), sb.toString());
        }  catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
