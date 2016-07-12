package pl.tomaja.atbackup.io.facade;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * @author Adam Tomaja
 */
public class RealIOTest extends AbstractIOTest {

	@Test
	public void fileShouldBeRemoved() throws IOException {
		// Given
		IOFacade oi = createIo();
		File file = File.createTempFile("shajdhasd", "asdiasd");

		// When
		oi.deleteQuietly(file);

		// Then
		assertThat(file.exists()).isFalse();
	}

	@Test
	public void fileShouldBeCopied() throws IOException {
		// Given
		String sourceContent = "helloWorld";
		IOFacade io = createIo();
		File source = writeToTempFile(sourceContent);
		File target = writeToTempFile("otherContent");

		// When
		io.copyFile(source, target);
		String targetContent = FileUtils.readFileToString(target);

		// Then
		assertThat(targetContent).isEqualTo(sourceContent);
	}

	private RealIO createIo() {
		return new RealIO();
	}
}
