package pl.tomaja.atbackup.io.facade;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * @author Adam Tomaja
 */
public class ReadOnlyIOTest extends AbstractIOTest {

	@Test
	public void fileShouldntBeRemoved() throws IOException {
		// Given
		IOFacade io = createIo();
		File file = File.createTempFile("asdasd", "Asdasd");

		// When
		io.deleteQuietly(file);

		// Then
		assertThat(file.exists()).isTrue();
	}

	@Test
	public void fileShouldntBeCopied() throws IOException {
		// Given
		IOFacade io = createIo();
		File source = writeToTempFile("someString");
		File target = writeToTempFile("targetSource");

		// When
		io.copyFile(source, target);
		String targetContent = readFromFile(target);

		// Then
		assertThat(targetContent).isEqualTo("targetSource");
	}

	private ReadOnlyIO createIo() {
		return new ReadOnlyIO();
	}
}
