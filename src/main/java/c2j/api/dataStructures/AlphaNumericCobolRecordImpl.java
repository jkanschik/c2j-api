package c2j.api.dataStructures;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import c2j.api.utils.EncodingUtils;

public class AlphaNumericCobolRecordImpl implements AlphaNumericCobolRecord {

	private String pictureClause;
	protected byte[] value;
	protected int size;

	public AlphaNumericCobolRecordImpl(String picture) {
		this(picture, null);
	}

	public AlphaNumericCobolRecordImpl(String picture, String value) {
		parsePicture(picture);
		this.value = new byte[this.size];
		if (value != null) {
			byte[] bytes = EncodingUtils.decodeString(value);
			System.arraycopy(bytes, 0, this.value, 0, bytes.length);
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
		return value;
	}

	public void write(byte[] src) {
		write(src, 0);
	}

	public void write(byte[] src, int offset) {
		System.arraycopy(src, 0, value, offset, Math.min(this.size, src.length));
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
