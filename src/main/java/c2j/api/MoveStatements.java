package c2j.api;

import c2j.api.dataStructures.AlphaNumericCobolRecord;
import c2j.api.dataStructures.CobolRecord;

/**
 * Created by kanschje on 09.03.2017.
 */
public class MoveStatements {

	public void move(AlphaNumericCobolRecord from, AlphaNumericCobolRecord to) {
		to.write(from.getValue());
	}

	public void move(String from, CobolRecord to) {
		to.set(from);
	}

}
