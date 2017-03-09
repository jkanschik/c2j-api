package c2j.api.dataStructures;

public class NumericCobolRecord extends AbstractCobolRecord {
	private String value;
	private String picture;

	public NumericCobolRecord(String picture) {
		this.picture = picture;
	}

	public NumericCobolRecord(String picture, AlphaNumericCobolRecordImpl redefineReference) {
		this.picture = picture;
	}

	public NumericCobolRecord(String picture, long value) {
		this.picture = picture;
	}
}
