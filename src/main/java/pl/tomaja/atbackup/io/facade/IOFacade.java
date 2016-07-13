package pl.tomaja.atbackup.io.facade;

import java.io.File;
import java.io.IOException;

import pl.tomaja.atbackup.io.FileType;

/**
 * @author Adam Tomaja
 */
public interface IOFacade {
	
	boolean copyFile(File srcFile, File destFile) throws IOException;
	
	boolean exists(File file);
	
	boolean isDirectory(File file);
	
	long lastModified(File file);
	
	String[] list(File file);
	
	boolean deleteQuietly(File file) throws IOException;
	
	FileType type(File file);
}
