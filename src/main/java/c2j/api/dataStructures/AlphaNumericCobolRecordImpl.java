package c2j.api.dataStructures;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import c2j.api.utils.EncodingUtils;

public class AlphaNumericCobolRecordImpl extends AbstractCobolRecord implements AlphaNumericCobolRecord {

	private String pictureClause;

	protected AlphaNumericCobolRecordImpl(String picture) {
		parsePicture(picture);
	}

	public AlphaNumericCobolRecordImpl(String picture, String str) {
		parsePicture(picture);
		this.value = new byte[this.size];
		this.set(str);
	}

	public void set(String str) {
		if (str == null) {
			str = StringUtils.repeat(" ", getSize());
		}
		byte[] bytes = EncodingUtils.decodeString(str);
		write(bytes);
	}
	
	public String get() {
		return toString();
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
