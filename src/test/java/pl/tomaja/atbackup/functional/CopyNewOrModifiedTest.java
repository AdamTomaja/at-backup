package pl.tomaja.atbackup.functional;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.AbstractFileAssert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.tomaja.atbackup.params.TaskParams;
import pl.tomaja.atbackup.task.Task;
import pl.tomaja.atbackup.task.impl.CopyNewOrModified;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Adam on 21.07.2016.
 */
public class CopyNewOrModifiedTest {

    private static Path tempDir;

    @BeforeClass
    public static void init() throws IOException {
        tempDir = Files.createTempDirectory("at-backup-test", new FileAttribute[0]);
    }

    @Test
    public void filesShouldBeCopied() throws IOException {
        // Given
        File file = new File("file1");
        File file2 = new File("dir" + File.separator + "file2");
        File sourceDir = Files.createTempDirectory(tempDir, "source", new FileAttribute[0]).toFile();
        File targetDir = Files.createTempDirectory(tempDir, "target", new FileAttribute[0]).toFile();
        TaskParams params = new TaskParams();

        params.setSource(sourceDir);
        params.setTarget(targetDir);

        Task task = new CopyNewOrModified();

        // When
        FileUtils.touch(new File(sourceDir, file.toString()));
        FileUtils.touch(new File(sourceDir, file2.toString()));
        task.execute(params);

        // Then
        assertThatFiles(sourceDir, file).exists();
        assertThatFiles(sourceDir, file2).exists();
        assertThatFiles(targetDir, file).exists();
        assertThatFiles(targetDir, file2).exists();

    }

    private AbstractFileAssert<?> assertThatFiles(File dir, File file) {
        return assertThat(new File(dir, file.toString()));
    }

    @AfterClass
    public static void destroy() {
        FileUtils.deleteQuietly(tempDir.toFile());
    }
}
