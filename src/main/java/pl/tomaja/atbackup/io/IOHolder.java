package pl.tomaja.atbackup.io;

import java.lang.reflect.InvocationTargetException;

import pl.tomaja.atbackup.io.facade.IOFacade;
import pl.tomaja.atbackup.io.facade.RealIO;

/**
 * Created by Adam Tomaja
 */
public class IOHolder {

	private static IOFacade io = new RealIO();
	
	public static IOFacade get() {
		return io;
	}
	
	public static void set(IOFacade io) {
		IOHolder.io = io;
	}
	
	public static void setFromString(String ioModeName) {
		try {
			IOModes mode = IOModes.valueOf(ioModeName.toUpperCase());
			set((IOFacade) mode.getClazz().getConstructor().newInstance());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Cannot find " + ioModeName + " io mode", e);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
}
