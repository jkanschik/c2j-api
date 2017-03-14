package c2j.api.example;

import c2j.api.CobolProgram;
import c2j.api.dataStructures.AlphaNumericCobolRecord;
import c2j.api.dataStructures.DisplayRecord;
import c2j.api.dataStructures.Group;
import c2j.api.dataStructures.NumericCobolRecord;
import c2j.api.dataStructures.NumericRecord;

/**
 * Created by kanschje on 09.03.2017.
 */
public class Main extends CobolProgram {

	@DisplayRecord(pic = "X(10)", value = "INITIAL")
	private AlphaNumericCobolRecord feld1;

	@DisplayRecord(pic = "X(10)")
	private AlphaNumericCobolRecord feld2;

	@NumericRecord(pic = "9(10)", value="1234567890")
	private NumericCobolRecord feld3N;

	@Group
	private ExampleGroup someGroup;

	public static void main(String... args) {
		new Main().execute();
	}

	public void execute() {

		display(feld1);
		display(feld3N);
		display(someGroup.field1);
		display(someGroup.field2);
		display(someGroup.field3);
		display(someGroup.field3N);

		display("Feld1 ist " + (isNumeric(someGroup.field1) ? "" : "nicht") + " numerisch.");
		display("Feld3n ist " + (isNumeric(someGroup.field3N) ? "" : "nicht") + " numerisch.");

		move("1", someGroup.field1);
		display("Feld1 ist " + (isNumeric(someGroup.field1) ? "" : "nicht") + " numerisch.");

		move("X", someGroup.field3);
		display("Feld3n ist " + (isNumeric(someGroup.field3N) ? "" : "nicht") + " numerisch.");

		move("1", someGroup.field3);
		display(someGroup.field3N);

		add(2, someGroup.field3N);
		display(someGroup.field3N);

		display(feld3N);
		move("9", someGroup.field3);
		add(someGroup.field3N, feld3N);
		add(someGroup.field3N, feld3N);
		add(someGroup.field3N, feld3N);
		add(someGroup.field3N, feld3N);
		display(feld3N);

		move("1234567", someGroup);
		display(someGroup.field1);
		display(someGroup.field2);
		display(someGroup.field3);
		display(someGroup.field3N);

		move("Hallo Welt", feld1);
		display(feld1);

		// MOVE "Hallo" TO FELD1
		move("Test", feld1);
		display(feld1);

		// MOVE "Hallo" TO FELD1(3:)
		move("Hallo", feld1.getRefMod(3));
		display(feld1);

		// MOVE FELD1(5:2) TO FELD2
		move(feld1.getRefMod(5, 2), feld2);
		display(feld1);

		move(feld1, feld2);
		display(feld2);
	}
}
