package c2j.api.dataStructures;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import c2j.api.utils.EncodingUtils;

public class GroupTest {
	
	public class GroupA extends Group {
		public AlphaNumericCobolRecord field1 = new AlphaNumericCobolRecordImpl("X", "A");
		public AlphaNumericCobolRecord field2 = new AlphaNumericCobolRecordImpl("X(5)", "BBBBB");
		public AlphaNumericCobolRecord field3 = new AlphaNumericCobolRecordImpl("X", "C");

		public GroupA() {
			init();
		}
	}

	@Test
	public void testSize() {
		GroupA group = new GroupA();
		assertThat(group.getSize(), is(7));
	}

	@Test
	public void testValue() {
		GroupA group = new GroupA();
		byte[] value = group.getValue();
		assertThat(value.length, is(7));
		assertThat(EncodingUtils.encodeString(value), is("ABBBBBC"));
	}

	@Test
	public void testWrite() {
		GroupA group = new GroupA();
		byte[] bytes = EncodingUtils.decodeString("1234567");
		group.write(bytes);
		assertThat(group.field1.toString(), is("1"));
		assertThat(group.field2.toString(), is("23456"));
		assertThat(group.field3.toString(), is("7"));
	}

}
