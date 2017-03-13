package c2j.api.dataStructures;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AlphaNumericCobolRecordTest {

	@Test
	public void testX() {
		AlphaNumericCobolRecord record = new AlphaNumericCobolRecordImpl("X");
		assertThat(record.getSize(), is(1));
	}
	
	@Test
	public void testXXX() {
		AlphaNumericCobolRecord record = new AlphaNumericCobolRecordImpl("XXX");
		assertThat(record.getSize(), is(3));
	}
	
	@Test
	public void testX10() {
		AlphaNumericCobolRecord record = new AlphaNumericCobolRecordImpl("X(10)");
		assertThat(record.getSize(), is(10));
	}

	@Test(expected = RuntimeException.class)
	public void testInvalidPicture() {
		new AlphaNumericCobolRecordImpl("X(a)");
	}

}
