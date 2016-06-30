package pl.tomaja.atbackup.io;

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
}
