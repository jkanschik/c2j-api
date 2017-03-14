package c2j.api;

import c2j.api.dataStructures.AlphaNumericCobolRecord;
import c2j.api.dataStructures.CobolRecord;
import c2j.api.dataStructures.FieldInitializer;
import c2j.api.dataStructures.NumericCobolRecord;

import java.math.BigDecimal;

public class CobolProgram {

	private MoveStatements moveStatements = new MoveStatements();
	private ComputationalStatements computationalStatements = new ComputationalStatements();

	public CobolProgram() {
		new FieldInitializer().initializeFields(this);
	}

	public void move(AlphaNumericCobolRecord from, AlphaNumericCobolRecord to) {
		moveStatements.move(from,  to);
	}

	public void move(String from, CobolRecord to) {
		moveStatements.move(from,  to);
	}

	public boolean isNumeric(CobolRecord record) {
		return computationalStatements.isNumeric(record);
	}

	public void add(long value, NumericCobolRecord to) {
		computationalStatements.add(value, to);
	}

	public void add(BigDecimal value, NumericCobolRecord to) {
		computationalStatements.add(value, to);
	}

	public void add(NumericCobolRecord value, NumericCobolRecord to) {
		computationalStatements.add(value, to);
	}

	public void display(CobolRecord record) {
		display(record.toString());
	}

	public void display(String str) {
		System.out.println(str);
	}

}
