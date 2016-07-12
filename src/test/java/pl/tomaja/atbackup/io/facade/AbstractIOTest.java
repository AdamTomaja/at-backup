package pl.tomaja.atbackup.io.facade;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author Adam Tomaja
 */
public class AbstractIOTest {

	protected File writeToTempFile(String content) throws IOException {
		File file = File.createTempFile("asdaisj", "Gfdgdfg");
		FileUtils.writeStringToFile(file, content);
		return file;
	}

	protected String readFromFile(File file) throws IOException {
		return FileUtils.readFileToString(file);
	}
}
