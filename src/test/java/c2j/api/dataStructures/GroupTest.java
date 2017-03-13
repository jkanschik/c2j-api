package c2j.api.dataStructures;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import c2j.api.utils.EncodingUtils;

public class GroupTest {
	
	public class SomeGroup extends GroupImpl {
		@DisplayRecord(pic = "X", value = "A")
		public AlphaNumericCobolRecord field1;

		@DisplayRecord(pic = "X(5)", value = "BBBBB")
		public AlphaNumericCobolRecord field2;

		@NumericRecord(pic = "9", value = "7")
		public NumericCobolRecord field3;

		public SomeGroup() {
			init();
		}
	}

	@Test
	public void testSize() {
		SomeGroup group = new SomeGroup();
		assertThat(group.getSize(), is(7));
	}

	@Test
	public void testValue() {
		SomeGroup group = new SomeGroup();
		byte[] value = group.getValue();
		assertThat(value.length, is(7));
		assertThat(EncodingUtils.encodeString(value).substring(0,  6), is("ABBBBB"));
		assertThat(value[6], is((byte) 0xF7));
	}

	@Test
	public void testWrite() {
		SomeGroup group = new SomeGroup();
		byte[] bytes = EncodingUtils.decodeString("123456 ");
		bytes[6] = (byte) 0xF7;
		group.write(bytes);
		assertThat(group.field1.toString(), is("1"));
		assertThat(group.field2.toString(), is("23456"));
		assertThat(group.field3.toString(), is("7"));
	}

}
