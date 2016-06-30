package pl.tomaja.atbackup.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Created by Adam Tomaja
 */
public class RealIO implements IOFacade {

	private static final Logger LOGGER = Logger.getLogger(RealIO.class);
	
	@Override
	public void copyFile(File srcFile, File destFile) throws IOException {
		LOGGER.debug(String.format("Copying %s -> %s", srcFile, destFile));
		FileUtils.copyFile(srcFile, destFile);
	}

	@Override
	public boolean exists(File file) {
		LOGGER.debug(String.format("Checking %s exists", file));
		return file.exists();
	}

	@Override
	public boolean isDirectory(File file) {
		LOGGER.debug(String.format("Checking %s is dir", file));
		return file.isDirectory();
	}

	@Override
	public long lastModified(File file) {
		LOGGER.debug(String.format("Checking last modified of: %s", file));
		return file.lastModified();
	}
}