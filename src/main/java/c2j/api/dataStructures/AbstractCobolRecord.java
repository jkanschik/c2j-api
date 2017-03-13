package c2j.api.dataStructures;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import c2j.api.utils.EncodingUtils;

public abstract class AbstractCobolRecord implements CobolRecord {

	protected byte[] value;
	private GroupImpl parent;
	private int parentOffset;
	
	protected int size;
	
	@Override
	public int getSize() {
		return size;
	}
	
	public GroupImpl getParent() {
		return parent;
	}
	
	@Override
	public byte[] getValue() {
		if (value == null) {
			return Arrays.copyOfRange(parent.getValue(), parentOffset, parentOffset + size);
		} else {
			return Arrays.copyOf(value, size);
		}
	}
	
	public void write(byte[] src) {
		write(src, 0);
	}

	public void write(byte[] src, int offset) {
		write(src, offset, Math.min(size - offset, src.length));
	}
	
	protected void write(byte[] src, int offset, int length) {
		if (value == null) {
			parent.write(src, parentOffset + offset, length);
		} else {
			System.arraycopy(src, 0, value, offset, Math.min(length, Math.min(size - offset, src.length)));
		}
	}
	
	public void setParentReference(GroupImpl parent, int parentOffset) {
		this.parent = parent;
		this.parentOffset = parentOffset;
	}

	public void set(String str) {
		if (str == null) {
			str = StringUtils.repeat(" ", getSize());
		}
		byte[] bytes = EncodingUtils.decodeString(str);
		write(bytes);
	}

}
