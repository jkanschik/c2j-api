package c2j.api;

import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;
import org.junit.Test;

import c2j.api.dataStructures.AlphaNumericCobolRecord;
import c2j.api.dataStructures.AlphaNumericCobolRecordImpl;

public class MoveStatementsTest {

	private MoveStatements moveStatements = new MoveStatements();
	
	@Test
	public void testStringToDisplay() {
		AlphaNumericCobolRecord record = new AlphaNumericCobolRecordImpl("X(5)", "     ");
		moveStatements.move("Hello", record);
		assertThat(record.toString(), is("Hello"));
	}

	@Test
	public void testStringTooLong() {
		AlphaNumericCobolRecord record = new AlphaNumericCobolRecordImpl("X");
		moveStatements.move("Hello", record);
		assertThat(record.toString(), is("H"));
	}

}
