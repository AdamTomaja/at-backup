package pl.tomaja.atbackup.io;

import pl.tomaja.atbackup.io.facade.ReadOnlyIO;
import pl.tomaja.atbackup.io.facade.RealIO;

/**
 * @author Adam Tomaja
 */
public enum IOModes {

	REAL(RealIO.class), READONLY(ReadOnlyIO.class);

	private final Class<?> clazz;

	private IOModes(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}
}
