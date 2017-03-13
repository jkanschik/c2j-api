package c2j.api.dataStructures;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import c2j.api.utils.EncodingUtils;
import org.apache.commons.lang3.StringUtils;

public class AlphaNumericCobolRecordImpl implements AlphaNumericCobolRecord {

	private String pictureClause;
	protected int size;

	private GroupImpl parent;
	private int parentOffset;
	private byte[] value;

	public AlphaNumericCobolRecordImpl(String picture) {
		this(picture, (String) null);
	}

	public AlphaNumericCobolRecordImpl(String picture, String str) {
		parsePicture(picture);
		this.value = new byte[this.size];
		this.setValue(str);
	}

	public AlphaNumericCobolRecordImpl(String picture, GroupImpl parent, int parentOffset) {
		this(picture, null, parent, parentOffset);
	}

	public AlphaNumericCobolRecordImpl(String picture, String value, GroupImpl parent, int parentOffset) {
		parsePicture(picture);
		this.value = null;
		this.parent = parent;
		this.parentOffset = parentOffset;
	}

	public void setValue(String str) {
		if (str == null) {
			str = StringUtils.repeat(" ", size);
		}
		byte[] bytes = EncodingUtils.decodeString(str);
		if (this.value != null) {
			System.arraycopy(bytes, 0, this.value, 0, bytes.length);
		} else {
			System.arraycopy(bytes, 0, this.parent.getValue(), parentOffset, bytes.length);
		}
	}

	private void parsePicture(String picture) {
		this.pictureClause = picture;
		if (Pattern.matches("X+", picture)) {
			this.size = picture.length();
		} else {
			Pattern p = Pattern.compile("X[(]([0-9]+)[)]");
			Matcher m = p.matcher(picture);
			if (m.find()) {
				this.size = Integer.parseInt(m.group(1));
			} else {
				throw new RuntimeException("Invalid Picture Clause " + pictureClause);
			}
		}
	}
	
	public int getSize() {
		return size;
	}

	public byte[] getValue() {
		if (this.value == null) {
			return Arrays.copyOfRange(this.parent.getValue(), this.parentOffset, this.size);
		} else {
			return Arrays.copyOf(this.value, this.size);
		}
	}

	public void write(byte[] src) {
		write(src, 0);
	}

	public void write(byte[] src, int offset) {
		if (this.value == null) {
			// TODO
		} else {
			System.arraycopy(src, 0, value, offset, Math.min(this.size, src.length));
		}
	}

	public ReferenceModification getRefMod(int from) {
		return new ReferenceModification(this, from);
	}

	public ReferenceModification getRefMod(int from, int size) {
		return new ReferenceModification(this, from, size);
	}

	@Override
	public String toString() {
		return EncodingUtils.encodeString(getValue());
	}

}
