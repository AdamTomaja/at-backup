package pl.tomaja.atbackup.task.impl;

import org.junit.Test;
import pl.tomaja.atbackup.io.facade.TestIO;
import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.impl.AbstractTask;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Adam on 21.07.2016.
 */
public class AbstractTaskTest extends AbstractTask {

    @Test
    public void rootNamesShouldBeReplacedWithProperPaths() {
        // Given
        setIo(new TestIO());
        TaskParams params = new TaskParams();
        params.setSource(new File("@Daneasd"));
        params.setTarget(new File("@Backupvds"));

        // When
        handleParams(params);

        // Then
        assertThat(params.getSource().toString()).isEqualTo("Daneasd:::");
        assertThat(params.getTarget().toString()).isEqualTo("Backupvds:::");
    }
}
