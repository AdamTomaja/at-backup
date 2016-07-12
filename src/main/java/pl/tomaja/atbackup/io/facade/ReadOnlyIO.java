package pl.tomaja.atbackup.io.facade;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Created by Adam Tomaja
 */
public class ReadOnlyIO extends RealIO {

	private static final Logger LOGGER = Logger.getLogger(ReadOnlyIO.class);
	
	@Override
	public void copyFile(File srcFile, File destFile) throws IOException {
		LOGGER.debug(String.format("Copying %s -> %s", srcFile, destFile));
	}

	@Override
	public boolean deleteQuietly(File file) {
		LOGGER.debug(String.format("Deleting quietly: %s", file));
		return true;
	}
}
