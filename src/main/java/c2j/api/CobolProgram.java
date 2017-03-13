package c2j.api;

import c2j.api.dataStructures.AlphaNumericCobolRecord;
import c2j.api.dataStructures.AlphaNumericCobolRecordImpl;
import c2j.api.dataStructures.CobolRecord;
import c2j.api.dataStructures.DisplayRecord;
import c2j.api.dataStructures.FieldInitializer;

import java.lang.reflect.Field;

public class CobolProgram {

	private MoveStatements moveStatements = new MoveStatements();

	public CobolProgram() {
		new FieldInitializer().initializeFields(this);
	}


	public void move(AlphaNumericCobolRecord from, AlphaNumericCobolRecord to) {
		moveStatements.move(from,  to);
	}

	public void move(String from, AlphaNumericCobolRecord to) {
		moveStatements.move(from,  to);
	}

	public void display(AlphaNumericCobolRecord record) {
		System.out.println(record.toString());
	}

}
