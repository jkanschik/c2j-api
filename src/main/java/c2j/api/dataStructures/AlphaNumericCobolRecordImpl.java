package c2j.api.dataStructures;

import c2j.api.utils.EncodingUtils;

public class AlphaNumericCobolRecordImpl implements AlphaNumericCobolRecord {

	protected byte[] value;
	protected int size;

	public AlphaNumericCobolRecordImpl(String picture) {
		this(picture, null);
	}

	public AlphaNumericCobolRecordImpl(String picture, String value) {
		this.size = 20;
		this.value = new byte[this.size];
		if (value != null) {
			byte[] bytes = EncodingUtils.decodeString(value);
			System.arraycopy(bytes, 0, this.value, 0, bytes.length);
		}
	}

	public int getSize() {
		return size;
	}

	public byte[] getValue() {
		return value;
	}

	public void write(byte[] src) {
		write(src, 0);
	}

	public void write(byte[] src, int offset) {
		System.arraycopy(src, 0, value, offset, src.length);
	}

	public ReferenceModification getRefMod(int from) {
		return new ReferenceModification(this, from);
	}

	public ReferenceModification getRefMod(int from, int size) {
		return new ReferenceModification(this, from, size);
	}

	@Override
	public String toString() {
		return EncodingUtils.encodeString(this.value);
	}

}
