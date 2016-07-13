package pl.tomaja.atbackup.io.facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author adam Tomaja
 */
public class InteractiveIO extends RealIO {

	private static final String POSITIVE_ANSWER = "y";
	
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public boolean copyFile(File srcFile, File destFile) throws IOException {
		if(ask(String.format("Do You want to copy %s into %s", srcFile, destFile))) {
			super.copyFile(srcFile, destFile);
			return true;
		}
		
		printCancelled();
		return false;
	}

	@Override
	public boolean deleteQuietly(File file) throws IOException {
		if(ask(String.format("Do You want to delete %s", file))) {
			return super.deleteQuietly(file);
		}
		
		printCancelled();
		return false;
	}
	
	public void printCancelled() {
		System.out.println("Operation cancelled");
	}
	
	private boolean ask(String question) throws IOException {
		System.out.print(question + "? y/n");
		String line = reader.readLine();
		return checkAnswer(line);
	}

	boolean checkAnswer(String line) {
		if(line != null && line.toLowerCase().equals(POSITIVE_ANSWER)) {
			return true;
		}
		
		return false;
	}
}
