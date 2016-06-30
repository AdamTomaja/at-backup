package pl.tomaja.atbackup.events;

import java.io.File;

/**
 * Created by Adam Tomaja
 */
public class DeleteEvent extends Event {

	final String text;
	
	public DeleteEvent(File file) {
		text = file.toString();
	}
	
	@Override
	public String getText() {
		return text;
	}
}
