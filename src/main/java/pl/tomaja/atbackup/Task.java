package pl.tomaja.atbackup;

import java.io.IOException;

/**
 * Created by Adam Tomaja on 2016-03-09.
 */
public interface Task {

    void execute(TaskParams params) throws IOException;

}
