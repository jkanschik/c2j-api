package c2j.api;

import static c2j.api.utils.EncodingUtils.decodeString;
import c2j.api.dataStructures.AlphaNumericCobolRecord;

/**
 * Created by kanschje on 09.03.2017.
 */
public class MoveStatements {

	public void move(AlphaNumericCobolRecord from, AlphaNumericCobolRecord to) {
		to.write(from.getValue());
	}

	public void move(String from, AlphaNumericCobolRecord to) {
		byte[] fromBytes = decodeString(from);
		to.write(fromBytes);
	}

}
