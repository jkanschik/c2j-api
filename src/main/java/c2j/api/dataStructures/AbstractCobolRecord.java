package c2j.api.dataStructures;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import c2j.api.utils.EncodingUtils;

public abstract class AbstractCobolRecord implements CobolRecord {

	// for top level fields:
	protected byte[] value;
	// for members of groups:
	private GroupImpl parent;
	private int parentOffset;
	// for redefinitions:
	private CobolRecord redefinitionReference;
	
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
		if (parent != null) {
			return Arrays.copyOfRange(parent.getValue(), parentOffset, parentOffset + size);
		} else if (redefinitionReference != null) {
			return Arrays.copyOf(redefinitionReference.getValue(), redefinitionReference.getSize());
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
		if (parent != null) {
			parent.write(src, parentOffset + offset, length);
		} else if (redefinitionReference != null) {
			redefinitionReference.write(src, 0);
		} else {
			System.arraycopy(src, 0, value, offset, Math.min(length, Math.min(size - offset, src.length)));
		}
	}
	
	public void setParentReference(GroupImpl parent, int parentOffset) {
		this.parent = parent;
		this.parentOffset = parentOffset;
	}

	public void setRedefinitionReference(CobolRecord redefinitionReference) {
		this.redefinitionReference = redefinitionReference;
	}

	public void set(String str) {
		if (str == null) {
			str = StringUtils.repeat(" ", getSize());
		}
		byte[] bytes = EncodingUtils.decodeString(str);
		write(bytes);
	}

}
