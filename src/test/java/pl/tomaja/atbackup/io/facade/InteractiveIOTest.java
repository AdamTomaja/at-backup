package pl.tomaja.atbackup.io.facade;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

 

/**
 * @author adam Tomaja
 */
public class InteractiveIOTest {

	@Test
	public void smallYShouldBePositive() {
		// Given
		InteractiveIO io = new InteractiveIO();
		String answer = "y";
		
		// When
		boolean result = io.checkAnswer(answer);
		
		// Then
		assertThat(result).isTrue();
	}
	
	@Test
	public void bigYShouldBePositive() {
		// Given
		InteractiveIO io = new InteractiveIO();
		String answer = "Y";
		
		// When
		boolean result = io.checkAnswer(answer);
		
		// Then
		assertThat(result).isTrue();
	}
	
	@Test
	public void emptyStringShouldBeNegative() {
		// Given
		InteractiveIO io = new InteractiveIO();
		String answer = "";
		
		// When
		boolean result = io.checkAnswer(answer);
		
		// Then
		assertThat(result).isFalse();
	}
	
	@Test
	public void anythingElseShouldBeNegative() {
		// Given
		InteractiveIO io = new InteractiveIO();
		String answer = "asd";
		
		// When
		boolean result = io.checkAnswer(answer);
		
		// Then
		assertThat(result).isFalse();
	}
}
