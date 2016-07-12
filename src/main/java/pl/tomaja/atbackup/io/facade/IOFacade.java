package pl.tomaja.atbackup.io.facade;

import java.io.File;
import java.io.IOException;

import pl.tomaja.atbackup.io.FileType;

/**
 * Created by Adam Tomaja
 */
public interface IOFacade {
	
	void copyFile(File srcFile, File destFile) throws IOException;
	
	boolean exists(File file);
	
	boolean isDirectory(File file);
	
	long lastModified(File file);
	
	String[] list(File file);
	
	boolean deleteQuietly(File file);
	
	FileType type(File file);
}
