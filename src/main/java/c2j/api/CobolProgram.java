package c2j.api;

import c2j.api.dataStructures.AlphaNumericCobolRecord;

public class CobolProgram {

	private MoveStatements moveStatements = new MoveStatements();
	
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
