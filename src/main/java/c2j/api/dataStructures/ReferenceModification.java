package c2j.api.dataStructures;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import c2j.api.utils.EncodingUtils;

public class ReferenceModification implements AlphaNumericCobolRecord {
	private int size;
	private int offset;
	private AlphaNumericCobolRecordImpl parent;

	public ReferenceModification(AlphaNumericCobolRecordImpl parent, int offset) {
		this.parent = parent;
		this.offset = offset;
		this.size = parent.getSize() - offset;
	}

	public ReferenceModification(AlphaNumericCobolRecordImpl parent, int offset, int size) {
		this.parent = parent;
		this.offset = offset;
		this.size = size;
	}

	public void write(byte[] src) {
		parent.write(src, offset);
	}

	public void write(byte[] src, int position) {
		parent.write(src, offset + position);
	}

	public void set(String str) {
		if (str == null) {
			str = StringUtils.repeat(" ", getSize());
		}
		byte[] bytes = EncodingUtils.decodeString(str);
		write(bytes);
	}

	public byte[] getValue() {
		return Arrays.copyOfRange(parent.getValue(), offset, offset + size);
	}

	public int getSize() {
		return size;
	}

	@Override
	public ReferenceModification getRefMod(int from) {
		throw new UnsupportedOperationException("Recursive reference modifications are not supported!");
	}

	@Override
	public ReferenceModification getRefMod(int from, int size) {
		throw new UnsupportedOperationException("Recursive reference modifications are not supported!");
	}
}