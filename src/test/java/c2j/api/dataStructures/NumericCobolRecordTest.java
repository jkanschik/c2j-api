package c2j.api.dataStructures;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class NumericCobolRecordTest {

	@Test
	public void test9() {
		NumericCobolRecord record = new NumericCobolRecord("9");
		assertThat(record.getSize(), is(1));
		assertThat(record.getScale(), is(0));
	}
	
	@Test
	public void test999() {
		NumericCobolRecord record = new NumericCobolRecord("999");
		assertThat(record.getSize(), is(3));
		assertThat(record.getScale(), is(0));
	}

	@Test
	public void test9_3() {
		NumericCobolRecord record = new NumericCobolRecord("9(3)");
		assertThat(record.getSize(), is(3));
		assertThat(record.getScale(), is(0));
	}
	
	@Test
	public void test999V99() {
		NumericCobolRecord record = new NumericCobolRecord("999V99");
		assertThat(record.getSize(), is(5));
		assertThat(record.getScale(), is(2));
	}
	
	@Test
	public void test9_3V99() {
		NumericCobolRecord record = new NumericCobolRecord("9(3)V99");
		assertThat(record.getSize(), is(5));
		assertThat(record.getScale(), is(2));
	}

	@Test
	public void test9_3V9_2() {
		NumericCobolRecord record = new NumericCobolRecord("9(3)V9(2)");
		assertThat(record.getSize(), is(5));
		assertThat(record.getScale(), is(2));
	}

	@Test
	public void test999V9_2() {
		NumericCobolRecord record = new NumericCobolRecord("999V9(2)");
		assertThat(record.getSize(), is(5));
		assertThat(record.getScale(), is(2));
	}

	@Test(expected = RuntimeException.class)
	public void testInvalidPicture() {
		new NumericCobolRecord("9(a)");
	}

}
