package pl.tomaja.atbackup.io.facade;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import pl.tomaja.atbackup.io.IOHolder;

/**
 * @author Adam Tomaja
 */
public class IOHolderTest {

	@Test
	public void realIOShouldBeSet() {
		// Given
		String io = "real";
		
		// When
		IOHolder.setFromString(io);
		
		// Then
		assertThat(IOHolder.get().getClass()).isEqualTo(RealIO.class);
	}
	
	@Test
	public void readOnlyIOShouldBeSet() {
		// Given
		String io = "readonly";
		
		// When
		IOHolder.setFromString(io);
		
		// Then
		assertThat(IOHolder.get().getClass()).isEqualTo(ReadOnlyIO.class);
	}
	
	@Test
	public void theSameIOShouldBeSet() {
		// Given
		IOFacade io = new RealIO();
		
		// When
		IOHolder.set(io);
		
		// Then
		assertThat(IOHolder.get()).isSameAs(io);
	}
	
	@Test(expected = RuntimeException.class)
	public void exceptionShouldBeThrownIfUnknownIOName() {
		// Given
		String ioName = "asdkashkdj";
		
		// When
		IOHolder.setFromString(ioName);
	}
}
