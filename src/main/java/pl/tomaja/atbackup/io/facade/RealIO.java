package pl.tomaja.atbackup.io.facade;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import pl.tomaja.atbackup.io.FileType;

/**
 * @author Adam Tomaja
 */
public class RealIO implements IOFacade {

	private static final Logger LOGGER = Logger.getLogger(RealIO.class);
	
	@Override
	public boolean copyFile(File srcFile, File destFile) throws IOException {
		LOGGER.debug(String.format("Copying %s -> %s", srcFile, destFile));
		FileUtils.copyFile(srcFile, destFile);
		return true;
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

	@Override
	public String[] list(File file) {
		LOGGER.debug(String.format("Listing %s", file));
		String[] list = file.list();
		return list == null ? new String[0] : list;
	}

	@Override
	public boolean deleteQuietly(File file) throws IOException {
		LOGGER.debug(String.format("Deleting quietly: %s", file));
		return FileUtils.deleteQuietly(file);
	}

	@Override
	public FileType type(File file) {
		LOGGER.debug(String.format("Checking type of %s", file));
		if(isDirectory(file)) {
			return FileType.DIRECTORY;
		} else {
			return FileType.FILE;
		}
	}
}
