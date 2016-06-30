package pl.tomaja.atbackup.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by Adam Tomaja
 */
public interface IOFacade {
	
	void copyFile(File srcFile, File destFile) throws IOException;
	
	boolean exists(File file);
	
	boolean isDirectory(File file);
	
	long lastModified(File file);
}
