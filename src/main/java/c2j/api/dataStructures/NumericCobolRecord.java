package c2j.api.dataStructures;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import c2j.api.utils.EncodingUtils;

public class NumericCobolRecord extends AbstractCobolRecord {

	private int scale;
	private int precision;
	
	public NumericCobolRecord(String picture) {
		parsePicture(picture);
	}

	public NumericCobolRecord(String picture, CobolRecord redefineReference) {
		parsePicture(picture);
	}

	public NumericCobolRecord(String picture, String value) {
		parsePicture(picture);
		this.value = new byte[this.size];
		set(value);
	}
	
	public int getScale() {
		return scale;
	}
	
	public BigDecimal get() {
		byte[] bytes = getValue();
		StringBuffer sb = new StringBuffer(this.size);
		for (byte b : bytes) {
			sb.append(b & 0x0F);
		}
		BigDecimal result = new BigDecimal(sb.toString());
		result.setScale(scale);
		return result;
	}
	
	public void set(String value) {
		set(new BigDecimal(value));
	}
	
	public void set(BigDecimal value) {
		String str = value.scaleByPowerOfTen(value.scale()).toPlainString();
		if (str.length() != this.size) {
			throw new RuntimeException("Length of string representation doesn't match size of record!");
		}
		byte[] bytes = EncodingUtils.decodeString(str);
		write(bytes);
	}
	
	private void parsePicture(String picture) {
		String[] splitBySign = picture.split("V");
		
		if (splitBySign.length == 1) {
			this.scale = 0;
			this.precision = this.scale + getOccurences(splitBySign[0]);
		} else {
			this.scale = getOccurences(splitBySign[1]);
			this.precision = this.scale + getOccurences(splitBySign[0]);
		}
		
		this.size = this.precision;
	}

	private int getOccurences(String pictureFragment) {
		if (Pattern.matches("9+", pictureFragment)) {
			return pictureFragment.length();
		} else {
			Pattern p = Pattern.compile("9[(]([0-9]+)[)]");
			Matcher m = p.matcher(pictureFragment);
			if (m.find()) {
				return Integer.parseInt(m.group(1));
			} else {
				throw new RuntimeException();
			}
		}
	}
	
	@Override
	public String toString() {
		return get().toPlainString();
	}
}
